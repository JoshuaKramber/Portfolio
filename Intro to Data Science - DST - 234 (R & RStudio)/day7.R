
# Day 7: Introduction to Annotations

library(tidyverse)
library(ggthemes)  # You may need to install this package first


# PART 1: THEMES & ANNOTATIONS


# Task 1: Use the data frame mpg (which represents the data from fuel efficiency of different cars) to generate a scatterplot with the following aesthetics:
# - x axis: engine size (displacement)
# - y axis: highway miles per gallon
# - Color: class (or type) of car.

# For your plot also do a smoothed fit, w/o showing the error

### --> FILL IN THE CODE WITH YOUR SOLUTION
p1 <- ggplot(data = mpg, aes(x=displ,y=hwy)) + geom_point(aes(color=class)) + geom_smooth(se=FALSE)
  # Show the plot
  p1 + theme(legend.position = "left")

# Task 2: Modify the plot theme (try out a few different themes, also try some from ggthemes)
p1 + ### FILL IN HERE


# Task 3: Adding Labels
# Title, subtitle, and caption add additional information:
p1 +
  labs(
    title = "Fuel efficiency generally decreases with engine size",
    subtitle = "Two seaters (sports cars) are an exception because of their light weight",
    caption = "Data from fueleconomy.gov"
  )

# You can use labs() to add a label to any defined aesthetic:
p1 +
  labs(
    x = "Engine displacement (L)",
    y = "Highway fuel economy (mpg)",
    colour = "Car type"
  )

# NOTE: if you want to NOT label something, use NULL:
p1 +
  labs(
    x = NULL,
    y = "Highway fuel economy (mpg)",
    colour = NULL
  )



# Task 4: Changing the placement of legends:
p1 + theme(legend.position = "bottom")

# Your turn: change the placement of the legend to the top, right, and left
### --> FILL IN THE CODE WITH YOUR SOLUTION


# To remove the legend, it depends on if you want to remove:
# (1) Legend with an aesthetic
p1 + scale_color_discrete(guide=FALSE)

# (2) All legends
p1 + theme(legend.position = "none")

