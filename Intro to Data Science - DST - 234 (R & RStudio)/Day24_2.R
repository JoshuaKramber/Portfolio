library(tidyverse)
library(mosaic)
library(broom)
library(zoo)

superior <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/sup.txt", sep = "")

plot(superior$X1973)
max(superior$X1973, na.rm = TRUE)

max_ice_cover <- superior |>
  map_df(max, na.rm = TRUE)

max_ice_cover_smoothed <- max_ice_cover |>
  pivot_longer(cols = everything(), names_to = "year", values_to = "ice_cover", names_prefix = "X") |>
  mutate(year = as.numeric(year)) |> # Convert year to a numeric value
  zoo::rollmean(k = 5) |> # The k=5 signifies how many places we take in our rolling mean
  as_tibble() # Make this into a data frame

lm(ice_cover ~ year, data = max_ice_cover_smoothed) |>
  summary()

max_ice_cover_smoothed |>
  ggplot(aes(x = year, y = ice_cover)) +
  geom_point() +
  geom_smooth(method = "lm")

lm(ice_cover ~ year, data = max_ice_cover_smoothed) |>
  broom::tidy()

median(superior$X1973, na.rm = TRUE)
mean(superior$X1973, na.rm = TRUE)

mosaic::favstats(superior$X1973)

superior_stats <- superior |>
  map_dfr(mosaic::fav_stats) |>
  mutate(year = names(superior))

glimpse(superior_stats$year)

superior_stats$year |> 
  str_extract(pattern = "(?<=X)[:digit:]+") |> 
  as.numeric()

superior_stats_all <- superior |>
  map_dfr(mosaic::fav_stats) |>
  mutate(year = names(superior),
         year = str_extract(year, pattern = "(?<=X)[:digit:]+"),
         year = as.numeric(year)) |> # Years covered by our data
  relocate(year) # puts the year to the top of the data frame

glimpse(superior_stats_all)

superior_stats_smoothed <- superior_stats_all |>
  map_df(zoo::rollmean, k = 5) # Smooth out to a 5 year rolling mean

superior_nested <- superior_stats_smoothed |>
  select(-n, -missing) |>
  pivot_longer(cols = c("min":"sd"), names_to = "fav_stat", values_to = "value") |>
  group_by(fav_stat) |>
  nest()

superior_nested |>
  mutate(lm_fit = map(data, ~ lm(value ~ year, data = .x))) # The .x acts as a place holder

superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  )

superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  ) |>
  select(fav_stat, lm_summary) |>
  unnest(cols = c(lm_summary))

superior_nested |>
  mutate(
    lm_fit = map(data, ~ lm(value ~ year, data = .x)),
    lm_summary = map(lm_fit, ~ broom::tidy(.x))
  ) |>
  select(fav_stat, lm_summary) |>
  unnest(cols = c(lm_summary)) |>
  filter(term == "year")

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

fit_lakes(superior)

superior <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/sup.txt", sep = "")
michigan <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/mic.txt", sep = "")
huron <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/hur.txt", sep = "")
erie <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/eri.txt", sep = "")
ontario <- read.csv("https://www.glerl.noaa.gov/data/ice/glicd/daily/ont.txt", sep = "")

great_lakes <- list(
  superior = superior,
  michigan = michigan,
  huron = huron,
  erie = erie,
  ontario = ontario
)

lake_results <- great_lakes |>
  map(fit_lakes) |>
  bind_rows(.id = "lake")

lake_results |>
  ggplot(aes(x = lake, y = -(estimate * 10))) +
  geom_bar(stat = "identity") +
  facet_grid(~fav_stat) +
  ylab("Loss rate (% ice / decade)")