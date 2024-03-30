# DST 234, Day 20

# Making google and interactive maps

# Install these packages if you haven't already  (you may need to uncomment)
#install.packages(c("ggmap","leaflet"))


library(tidyverse)
library(ggmap)   # Making google maps
library(leaflet) # Making dynamic maps

# For both google maps and dynamic maps you need to be connected to the internet.

# When you use google to get place information, register your key.  (You need to be connected to the web.)
ggmap::register_google(key = "YOUR-KEY-GOES-HERE")


# Task 1: Use google to generate the latitude and longitude of a place.
augsburg <- "2211 Riverside Avenue, Minneapolis, 55454"
auggieUniv <- geocode(augsburg)  # Return the lat/long of the location


# Do a google search of your hometown
tower <- "Tower, MN, 55790"    # My hometown
myHome <- geocode(tower)  # Return the lat/long

# Task 2: Generate the distance between two points on the map.
mapdist(from=tower, to=augsburg, mode="driving")   # You can do driving, bicycling, or walking

# YOUR TURN: How far is it between your hometown and Augsburg?


# Task 3: Generate a route between two places
myRoute <- route(tower,augsburg,alternatives = TRUE)

# You may get more than one route
glimpse(myRoute)


# YOUR TURN: Get a route between your hometown and Augsburg

# Task 4: Let's make static and dynamic map, adding in markers

# Task 4a: static map from google
augsburgMap <- get_map(augsburg, zoom = 17, maptype = "roadmap")
ggmap(augsburgMap)

# There are different options for the zoom
# Let's make a map of the routes!
ggmap(augsburgMap) +
  geom_leg(aes(x = start_lon, y = start_lat, xend = end_lon, yend = end_lat,color=route),size = 2, data = myRoute)

### We weren't zoomed out enough.
augsburgMap2 <- get_map(augsburg, zoom = 6, maptype = "satellite")
ggmap(augsburgMap2)

# Now let's put this together:
ggmap(augsburgMap2) +
  geom_leg(aes(x = start_lon, y = start_lat, xend = end_lon, yend = end_lat,color=route),size = 2, data = myRoute)

# YOUR TURN: Make a static route map from your hometown to Augsburg

# Task 4c: dynamic map (uses leaflet package)  One advantage is that we've already geocoded the data
leaflet() |>
  addTiles() |>
  addMarkers(lng = ~lon, lat = ~lat, data=auggieUniv)


# Task 4d: now lets do a dynamic map with cool popups
leaflet() |>
  addTiles() |>
  addMarkers(lng = ~lon, lat = ~lat, data=auggieUniv) |>
  addPopups(lng = ~lon, lat = ~lat, data = auggieUniv, popup = "John's work") |>
  addMarkers(lng = ~lon, lat = ~lat, data=myHome) |>
  addPopups(lng = ~lon, lat = ~lat, data = myHome, popup = "John's hometown")

# You can also add routes, but it is a little less elegant:
# WHAT DO YOU NOTICE HERE ???
leaflet() |>
  addTiles() |>
  addMarkers(lng = ~lon, lat = ~lat, data=auggieUniv) |>
  addPopups(lng = ~lon, lat = ~lat, data = auggieUniv, popup = "John's work") |>
  addMarkers(lng = ~lon, lat = ~lat, data=myHome) |>
  addPopups(lng = ~lon, lat = ~lat, data = myHome, popup = "John's hometown") |>
  addPolylines(data = myRoute,lat = ~start_lat, lng = ~start_lon)




### Alternatives to google maps (if you don't have access to google's API)

us <- c(left = -125, bottom = 25.75, right = -67, top = 49)
get_stamenmap(us, zoom = 5, maptype = "toner-lite") |> ggmap()

### You can read more here: https://github.com/dkahle/ggmap
