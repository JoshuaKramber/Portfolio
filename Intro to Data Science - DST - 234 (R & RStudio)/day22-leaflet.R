# DST 234: Day 22 - Making interactive maps


# Review through this list - you may need to install some packages
library(tidyverse)
library(leaflet) # Making dynamic maps





# Task 1: Make an interactive map from a data frame

# Define data:
kuopio_data <- tibble(lat = 62.8980, lon = 27.6782)

# A very basic interactive map of Kuopio
kuopio_data |>
  leaflet() |>
  addTiles() |>  # Puts in the geographic map
  addMarkers(lng = ~lon, lat = ~lat)

# YOUR TURN: Look up the latitude and longitude of an interesting place to you.
# Display that map.


# Task 1b
# Let's add some more data for context:
uef_data <- tibble(lat = c(62.8980,62.6010),
                   lon = c(27.6782,29.7636),
                   place = c("Kuopio","Joensuu"),
                   place_color = c("blue","red"))


# Dynamic map with cool popups
uef_data |>
leaflet() |>
  addTiles() |>
  addMarkers(lng = ~lon,
             lat = ~lat) |>
  addPopups(lng = ~lon,
            lat = ~lat,
            popup = ~place)

# Or you can do a city with a dot, and have the popup come up with the
uef_data |>
leaflet() |>
  addTiles() |>
  addCircles(lng = ~lon,
             lat = ~lat,
             popup=~place,
             radius = 5000,
             color = ~place_color)  # Radius specified in meters!


uef_data |>
  leaflet() |>
  addTiles() |>
  addCircleMarkers(lng = ~lon,
             lat = ~lat,
             popup=~place,
             radius = 5)  # Radius specified in pixels!



# Task 1c: adding even more context:
# The popup is an html code:
map2 <- uef_data |>
  leaflet() |>
  addTiles() |>
  addCircleMarkers(lng = ~lon,
             lat = ~lat,
             popup=~paste("Name: ", place, "</br> UEF campus"),
             radius = 5)

# Print the map to your screen by running this code
map2

# We can set the view - define the longitude, latitude, and zoom level
map2 |>
  setView(lng=28,lat = 62, zoom=5)

# Zoom goes from 1 (world) to 18 (local)



