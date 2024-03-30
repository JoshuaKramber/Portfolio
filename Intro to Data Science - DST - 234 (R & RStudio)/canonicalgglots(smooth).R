library(tidyverse)
glimpse(diamonds)
?diamonds

graph3 <- ggplot(head(diamonds,n=100),aes(x=carat,y=price,color=color)) + geom_smooth(method='lm') + facet_wrap(clarity ~ .)
graph3

graph2 <- ggplot(head(diamonds,n=100),aes(x=carat,y=price,color=color)) + geom_smooth(method='lm')
graph2

graph1 <- ggplot(head(diamonds,n=100),aes(x=carat,y=price)) + geom_point()
graph1