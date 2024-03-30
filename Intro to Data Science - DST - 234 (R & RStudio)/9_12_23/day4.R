### DST 234
### Day 4: Introduction to R

### Loading packages  (each R session)
library(tidyverse)


### Comments  (start the line with a hashtag)

### Variables
x <- 5

# Convention: name your variables in a meaningful way
time_to_respire <- 10  # Snake case
timeToRespire <- 20 # Uppercase for different words

### Vectors
is_plot <- c(TRUE,FALSE)
x <- c(5,10)

### Data frames
my_first_data_frame <- data.frame(x = c("So","proud"),
                                  y = c(5,3),
                                  z = c(TRUE,FALSE)
                                  )

# Or to use tidyverse syntax:
my_first_data_frame_two <- tibble(x = c("So","proud"),
                                  y = c(5,3),
                                  z = c(TRUE,FALSE)
)

glimpse(mpg)

### Sequences & Arrays

# Start by asking for help:

?seq

?array

goal_1 <-    # Fill in here

goal_2 <-    # Fill in here

goal_3 <-    # Fill in here


### FYI: Assignment operators ( <-- vs = )

is_plot <- c(TRUE,FALSE)  # Preferred  ***
is_plot = c(TRUE,FALSE)  # <- and = are the same

c(TRUE,FALSE) -> is_another_plot  # NOT as common
