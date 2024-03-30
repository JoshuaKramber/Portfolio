library(tidyverse)
library(sf)     # R wrapper around GDAL/OGR
library(leaflet)    # for fortifying shapefiles
library(leaflet.extras)   # For leaflet heatmaps


mn_1937 <- st_read(dsn = "C:/Users/kramb/Documents/DST 234 Files/MNMinneapolis1937/", layer = "cartodb-query")

glimpse(mn_1937)

# Now make the leaflet map!
mn_1937 |> 
  leaflet() |> 
  addTiles() |> 
  addPolygons(weight = 1,
              color = "white",
              fillColor = ~holc_grade,
              fillOpacity = 0.3,
              popup = ~holc_id)

# Define your factors of the different colors
neighbor_pal <-colorFactor(palette = "Purples", 
                           domain = c("A","B","C","D")
)

# Now we add in the code
mn_map <- mn_1937 |> 
  leaflet() |> 
  addTiles() |> 
  addPolygons(weight = 1,
              color = "white",
              fillColor = ~neighbor_pal(holc_grade),
              fillOpacity = 0.6,
              popup = ~holc_id) |>
  addLegend(pal = neighbor_pal,
            values = ~holc_grade,
            opacity = 0.6) #|>
  #addProviderTiles(providers$Stadia.StamenToner)

mn_map


# Read in the parks data
mn_parks <- st_read(dsn = "C:/Users/kramb/Documents/DST 234 Files/Parks-shp/", layer = "Parks")

# Helpful just to see what the data frame contains
glimpse(mn_parks)


# Now make the leaflet map!
mn_map |>
  addPolylines(data = mn_parks,
               weight = 2,
               color = "purple",
               fillColor = 'purple',
               opacity = 0.5)

tows <- read.csv(file = "Snow_Emergency_Victory1_Tows_2023.csv")

glimpse(tows)

tows |>
  leaflet() |> 
  addTiles() |>
  addWebGLHeatmap(lng=~Longitude, lat=~Latitude, size = 1000,opacity = .5)

glimpse(tows)
totTows = tows |> 
  group_by(Neighborho) |> 
  summarize(N=n()) |> 
  arrange(desc(N)) |> 
  na.omit()

# I've already prepared the shapefile in our folder - remember to transform it to google coordinates

nbhd_shp <- st_read(dsn = "~/DST 234 Files/day21-snowEmergency/Neighborhoods", layer = "Neighborhoods")

glimpse(nbhd_shp)

tows_merged <- nbhd_shp |>
  left_join(totTows,by=c("BDNAME"="Neighborho"))

max_tows <- max(totTows$N) # We need this to specify the largest value in our paletted.
tow_palette <- colorNumeric(palette ="YlGnBu",
                            domain = c(0,max_tows))  # We need to define the domain as a range of values.

tows_merged |>
  leaflet() |>
  addTiles() |> 
  addPolygons(weight=1,
              fillOpacity = 0.7,
              color='blue',
              fillColor=~tow_palette(N),
              popup=~paste(BDNAME, "</br>",N)
  )


