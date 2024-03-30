library(tidyverse)
library(ggplot2)

#using regular math
x <- seq(0,10,length.out=100)
y=sin(sqrt(x))
x
y

#using functions
sin_sqrt <- function(x){
  y <- sin(sqrt(x))
  return (y)
}

x2 <- seq(0,10,length.out=100)
y2 <- sin_sqrt(x2)

plotting_data <- tibble(x=seq(-1,1,length.out=50), y=x^2)

plotting_data |> ggplot(aes(x=x,y=y))+geom_line()


plotting_function <- function(n){
  if(n<0){
    stop("N<0, this is no bueno")
  }
  
  plotting_data <- tibble(x=seq(-1,1,length.out=50), y=x^n)
  
  plot <- plotting_data |> ggplot(aes(x=x,y=y))+geom_line()
  
  return (plot)
}

plotting_function(-1)


