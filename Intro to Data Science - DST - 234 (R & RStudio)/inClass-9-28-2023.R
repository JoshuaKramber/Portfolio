library(tidyverse)
library(mdsr)


df1 <- Violations |> 
  filter(boro == "MANHATTAN") |>
  group_by(zipcode) |>
  summarize(n_inspections=n(),
            median_score = median(score, na.rm = TRUE)) |>
  filter(n_inspections >= 50)

  
ggplot(data = df1,aes(x=median_score,y=n_inspections)) + geom_point()

