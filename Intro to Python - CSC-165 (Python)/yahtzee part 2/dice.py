def print_in_shell():
    print(u'\u2680',u'\u2681',u'\u2682', u'\u2683', u'\u2684', u'\u2685')

# Taken from this video... THANKS
# https://www.youtube.com/watch?v=8QdBZH1h5H8&ab_channel=Iknowpython

import random
from tkinter import *

root = Tk()
root.geometry("700x450")

# Create placeholders for the dice and the player's name
label = Label(root,font=("times",200))
name = Label(root,font=("times",50))

# roll 2 die and display
def roll():
    # for demonstration purposes, show a player's name
    players = ['Mia','Mohamed','Adam','Alyssa']
    # the die face from 1 to 6
    faces = ['\u2680','\u2681','\u2682','\u2683','\u2684','\u2685']
    # randomly select from die faces and add to the label
    die1 = random.choice(faces)
    die2 = random.choice(faces)
    label.config(text=f'{die1} {die2}')
    label.pack()
    # randomly select a player that rolled the dice
    name.config(text=f"{random.choice(players)}'s roll")
    name.pack()
    

# configure a button that rolls the dice every time it is pressed
button = Button(root,text="ROLL for YAHTZEE...",command=roll)
button.place(x=290,y=10)

# keep the window open and watching for a button press
root.mainloop()
