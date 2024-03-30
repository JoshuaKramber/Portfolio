### DST 234, Day 5

## load up the associated libraries
library(mdsr)
library(tidyverse)

### Part 1: Facetting plots
## Facetting plots
ggplot(data = mpg) +
  geom_point(aes(x = displ, y = hwy)) +
  facet_grid(drv ~ cyl)

# Compare to:
ggplot(data = mpg) +
  geom_point(aes(x = displ, y = hwy)) +
  facet_grid(cyl ~ drv)


# Questions from: https://r4ds.had.co.nz/data-visualisation.html#exercises-2

# Q1: What do the empty cells in plot with facet_grid(drv ~ cyl) mean? How do they relate to this plot?
ggplot(data = mpg) +
  geom_point(mapping = aes(x = drv, y = cyl))

# Q2: What plots does the following code make? What does . do?
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(drv ~ .)

ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(. ~ cyl)

### Part 2: Data Intake --> NEED TO SAVE THIS FILE LOCALLY FIRST
memorization_data_csv <- read_csv('memorizaton_data.csv')

# read_csv()  --> tidyverse function to import files  PREFERRED
# read.csv()  --> baseR function
# read.csv2()

# For Excel data, need to have the readxl library installed
memorization_data_excel <- readxl::read_xlsx('memorization_data.xlsx')

### Creating ggplot "sentences"
# Compare:
ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy)) +
  facet_grid(cyl~drv)

# First the plot to a variable allows you to easily add different features.
p1 <- ggplot(data = mpg) +
  geom_point(mapping = aes(x = displ, y = hwy))

p1 + facet_grid(cyl ~ drv)


