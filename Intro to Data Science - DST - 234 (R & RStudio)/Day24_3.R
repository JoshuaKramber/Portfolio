Functional Programming with Great Lakes Data
AUTHOR
John Zobitz

Introduction
This activity will have you applying techniques in functional programming by analyzing a dataset of the ice cover on the Great Lakes. There has been a long term trend towards less cover on the Great Lakes, attributable to climate change.

In each code block in the upper right hand corner I have a clipboard that you can copy and paste the code into R as you go.

First let’s load up packages we will need to complete this activity, which require tidyverse, broom, mosiac, and zoo. (You may need to install the last two by typing at the console install.packages("PACKAGE_NAME"), where PACKAGE_NAME is the name of the package.)

library(tidyverse)
library(mosaic)
library(broom)
library(zoo)

Let’s pull in our data into our workspace. Thankfully, all the data can be provided in a spreadsheet from the Great Lakes Environmental Research Laboratory. A long term dataset for each of the Great Lakes is provided, as a csv file. We can just use the command read.csv to pull them in and to examine the data:
  
  superior <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/sup.txt", sep = "")

As you can see, there are a lot of columns to these data. It looks like each column is a year, but let’s understand the data by making some simple plots:
  
  plot(superior$X1973)

This is a timeseries of the percentage of the lake covered in ice for a given “ice year” (which runs from November 14 to June 5 of the following year.) Leap years (February 29) are also included, but they are just listed as NA if it is not a leap year.

Since we have a timeseries of ice cover in a given year, we look at the long term trend of the maximum ice cover on Lake Superior. To start, let’s calculate the max for 1973:
  
  max(superior$X1973, na.rm = TRUE)

All right, we are in business. But how should we automate this to compute the maximum ice cover for each year? We want to apply the function max across all of the columns, so perhaps use the map_df function, first removing some of the columns we don’t need:
  
  max_ice_cover <- superior |>
  map_df(max, na.rm = TRUE)

Excellent! To smooth out the year to year variability, we can use a five year centered rolling mean using the zoo::rollmean function, but first we should gather our data so we have a nice data frame:
  
  max_ice_cover_smoothed <- max_ice_cover |>
  pivot_longer(cols = everything(), names_to = "year", values_to = "ice_cover", names_prefix = "X") |>
  mutate(year = as.numeric(year)) |> # Convert year to a numeric value
  zoo::rollmean(k = 5) |> # The k=5 signifies how many places we take in our rolling mean
  as_tibble() # Make this into a data frame

Notice that in order to do the rolling mean, we had to convert our new column year from a character to a numeric value.

Finally, we can do a linear fit and also report the summary from that fit:
  
  lm(ice_cover ~ year, data = max_ice_cover_smoothed) |>
  summary()

max_ice_cover_smoothed |>
  ggplot(aes(x = year, y = ice_cover)) +
  geom_point() +
  geom_smooth(method = "lm")

Since we previously learned about broom, let’s display the results from that fit in a neat way:
  
  lm(ice_cover ~ year, data = max_ice_cover_smoothed) |>
  broom::tidy()

It looks like the long-term trend is for the maximum ice cover to decrease, at about a rate of .74% per year, or 7.4% per decade. (The initial slope has units of % / year, so multiplying that by 10 gives 7.4% / decade)

Many models
Notice how we used map_df in to iterate to find the maximum amount of ice cover each year. We could also find other summary statistics:
  
  Let’s calculate this for 1973:
  
  median(superior$X1973, na.rm = TRUE)
mean(superior$X1973, na.rm = TRUE)

Rather than computing these statistics one at a time, we can use the command mosaic::favstats, which will do them all at once:
  
  mosaic::favstats(superior$X1973)

Let’s start to iterate this process like we did above. To be clear, I need to do the following:
  
  Apply favstats to get the summary statistics for each year.
Do a five year rolling mean of the data.
Fit a linear model to the timeseries of each summary statistic.
Finally, report out the coefficient from the slope of the linear regression with it’s p-value.
Whew! This sounds like a lot of work, but thankfully we can do this with functional programming and the map functions.

We will use map_dfr to get the summary statistics, and also adding in a column for the year:
  
  superior_stats <- superior |>
  map_dfr(mosaic::fav_stats) |>
  mutate(year = names(superior))

Notice how by using map_dfr we took each of the outputs of fav_stats then made it into a column. We did this because superior_stats is a list structure, with the items in the list separated by year. We need to fix this by binding the rows together by year.

One other thing - let’s take a look at the year variable we created:
  
  glimpse(superior_stats$year)

Notice how the entries are preceded by an “X” and this variable is a character string. We need to use our string look arounds to convert this to a numeric:
  
  superior_stats$year |> 
  str_extract(pattern = "(?<=X)[:digit:]+") |> 
  as.numeric()

Here is how this series of code works:
  
  superior_stats$year pulls out the year column from our data frame.
str_extract(pattern = "(?<=X)[:digit:]+") uses the lookaround option to search for multiple digits ([:digit:] with +) preceded by the character X (represented by (?<=X) ).
as.numeric() converts the vector to a numeric variable.
Now let’s put all of the above into a modified series of pipes:
  
  superior_stats_all <- superior |>
  map_dfr(mosaic::fav_stats) |>
  mutate(year = names(superior),
         year = str_extract(year, pattern = "(?<=X)[:digit:]+"),
         year = as.numeric(year)) |> # Years covered by our data
  relocate(year) # puts the year to the top of the data frame

glimpse(superior_stats_all)

Take a look at the superior_stats_all data frame. To do a five year rolling average we want to apply the same function (rollmean) to each column of our matrix. map_df to the rescue!
  
  superior_stats_smoothed <- superior_stats_all |>
  map_df(zoo::rollmean, k = 5) # Smooth out to a 5 year rolling mean

Now, we need to apply a linear model where we want to apply a linear fit of the variable year against the other columns. There are some extra columns in our data frame, which we will just remove to start.

superior_nested <- superior_stats_smoothed |>
  select(-n, -missing) |>
  pivot_longer(cols = c("min":"sd"), names_to = "fav_stat", values_to = "value") |>
  group_by(fav_stat) |>
  nest()

Notice how we have a nested data frame. (Try View(superior_nested) to explore it.) We are going to apply a linear model on each of the nested lists in our data frame, having tidy values:
  
  superior_nested |>
  mutate(lm_fit = map(data, ~ lm(value ~ year, data = .x))) # The .x acts as a place holder

There is a lot going on in this pipe, but it is worth studying, perhaps bit by bit:
  
  With the ~ we are building an anonymous function to pass to our map function.
The .x when we do a linear fit works like a placeholder for the different columns being fit in the map function.
The command map applies the same function to each of the list elements.
We can even go a step further, but mutating another column with our (tidy) values from our fit:
  
  superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  )

Finally, if we want a tidy data frame of the regression coefficients and p values we can just unnest:
  
  superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  ) |>
  select(fav_stat, lm_summary) |>
  unnest(cols = c(lm_summary))

How are the slopes from these different measures? Let’s just filter on the coefficient multiplying the year:
  
  superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  ) |>
  select(fav_stat, lm_summary) |>
  unnest(cols = c(lm_summary)) |>
  filter(term == "year")

So by all measures, the ice cover is decreasing over time.

We can also represent this in one big pipe:
  
  superior |>
  map_dfr(mosaic::fav_stats) |>
  mutate(year = names(superior),
         year = str_extract(year, pattern = "(?<=X)[:digit:]+"),
         year = as.numeric(year)) |>
  map_df(zoo::rollmean, k = 5) |>
  select(-n, -missing) |>
  pivot_longer(cols = c("min":"sd"), names_to = "fav_stat", values_to = "value") |>
  group_by(fav_stat) |>
  nest() |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  ) |>
  select(fav_stat, lm_summary) |>
  unnest(cols = c(lm_summary)) |>
  filter(term == "year")

(I’ll even admit a long pipe like this can be hard to process - if you plan to go that route please comment things up!)

Even more models
By now we have a powerful approach - in that we can efficiently process a large dataset, apply a statistical model, and evaluate the significance.

We can extend this approach even more by building up a database of different lakes, such as:
  
  Automating the above process in a single function that will fit the data.
Combining data from all the Great Lakes in a list structure.
Applying our function to all of the elements in our list, and then recombining the data.
Let’s do this. The name of the function will be called fit_lakes which will do our multistep pipe process from above. Notice how I have the input defined as lake_data rather than superior. Let’s set things up so we have the input to our function of just ice cover data by year (meaning all other columns are already removed).

fit_lakes <- function(lake_data) {
  fit_data <- lake_data |>
    map_dfr(mosaic::fav_stats) |>
    mutate(year = names(lake_data),
           year = str_extract(year, pattern = "(?<=X)[:digit:]+"),
           year = as.numeric(year)) |>
    map_df(zoo::rollmean, k = 5) |>
    select(-n, -missing) |>
    pivot_longer(cols = c("min":"sd"), names_to = "fav_stat", values_to = "value") |>
    group_by(fav_stat) |>
    nest() |>
    mutate(
      lm_fit = map(data, ~ lm(value ~ year, data = .x)),
      lm_summary = map(lm_fit, ~ broom::tidy(.x))
    ) |>
    select(fav_stat, lm_summary) |>
    unnest(cols = c(lm_summary)) |>
    filter(term == "year")
  
  return(fit_data)
}

Does that give us what we want? Let’s try running this on the Lake Superior data:
  
  fit_lakes(superior)

Next, let’s assemble our dataset of Great Lakes. There are five Great Lakes, and the good news is that the dataset for the other four are similarly structured. Functional programming to the rescue!
  
  For the sake of repetitiveness I am including Lake Superior again:
  
  superior <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/sup.txt", sep = "")
michigan <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/mic.txt", sep = "")
huron <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/hur.txt", sep = "")
erie <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/eri.txt", sep = "")
ontario <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/ont.txt", sep = "")

Next what I will do is combine these lakes in a list:
  
  great_lakes <- list(
    superior = superior,
    michigan = michigan,
    huron = huron,
    erie = erie,
    ontario = ontario
  )

Now comes the awesomeness of combining these together:
  
  lake_results <- great_lakes |>
  map(fit_lakes) |>
  bind_rows(.id = "lake")

That is it! So for just thinking through the problem via functional programming we could efficiently process a layered dataset.

Finally let’s make a plot of our results. We’ll multiply by 10 so our fitted slopes (the rates) have units of % ice / decade):
  
  lake_results |>
  ggplot(aes(x = lake, y = -(estimate * 10))) +
  geom_bar(stat = "identity") +
  facet_grid(~fav_stat) +
  ylab("Loss rate (% ice / decade)")

This approach shows the power of incrementally building code, but then applying functional programming to efficiently process your data.