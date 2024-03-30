# These libraries need to be loaded
library(tidyverse)
library(maps)
library(mapdata)

#Country = Colombia

world <- map_data("world")

world <- world |> filter(region == "Colombia")
# View the names of the data frame:
glimpse(world)

# View the names of the cities:
glimpse(world.cities)

cities <- world.cities |> filter(country.etc == "Colombia")

ggplot() +
  geom_polygon(data = world, aes(x=long, y = lat,group=group), fill = "white", color = "blue",alpha = 0.3) + geom_text(data = cities, aes(x=long,y=lat,color=pop,label = name)) + coord_fixed(1) +
  labs(title = "Chicken Alfredo Pasta")
