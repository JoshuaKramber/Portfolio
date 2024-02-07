import random
roll = [1,1,1,1,1,0]
def roll_dice():
    rolls = [0,0,0,0,0]
    #This while loop creates random ints for the roll and then appends them to the list of rolls
    for i in range(5):
        rolls[i] = random.randint(1,6)
    return rolls

def count_values(roll):
    count = [0,0,0,0,0,0]
    roll = roll_dice()
    #counts to see how many times each int is in the list roll, then appends that number to the count list
    for x in range(1,7):
        count[x-1]=roll.count(x)
    return count

def upper_score(count,value):
    upper = 0
    #sets a value equal to itself times however many appearences it has in the counts list
    upper = count[value-1]*value
    return upper

def three_of_kind(roll, count):
    for x in range(len(count)):
        #checks to see if there are any values in count that appear 3 or more times
        if ((count[x]==3) or (count[x]==4) or (count[x]==5)):
            #returns the sum of the entire roll
            return sum(roll)
    return 0


def four_of_kind(roll, count):
    for x in range(len(count)):
        #checks to see if there are any values in count that appear 4 or more times
        if ((count[x]==4) or (count[x]==5)):
            #returns the sum of the entire roll
            return sum(roll)
    return 0

def full_house(count):
    for x in range(len(count)):
        for y in range(x,len(count)):
            if count[x]==3 and count[y]==2 and count[x]!=count[y]:
                return 25
            elif count[y]==3 and count[x]==2 and count[x]!=count[y]:
                return 25
    return 0


def small_straight(roll):
    r = []
    for x in range(len(roll)):
        r.append(roll[x])
    r = sorted(r)
    for x in r:
        if r.count(x) > 1:
            r.remove(x)
    if ((r.count(1)==1) and (r.count(2)==1) and (r.count(3)==1) and (r.count(4)==1)):
        return 30
    elif ((r.count(2)==1) and (r.count(3)==1) and (r.count(4)==1) and (r.count(5)==1)):
        return 30
    elif ((r.count(3)==1) and (r.count(4)==1) and (r.count(5)==1) and (r.count(6)==1)):
        return 30
    return 0

        

def large_straight(roll):
    r = []
    for x in range(len(roll)):
        r.append(roll[x])
    r = sorted(r)
    for x in r:
        if r.count(x) > 1:
            r.remove(x)
    if ((r.count(1)==1) and (r.count(2)==1) and (r.count(3)==1) and (r.count(4)==1) and (r.count(5)==1)):
        return 40
    elif ((r.count(2)==1) and (r.count(3)==1) and (r.count(4)==1) and (r.count(5)==1) and (r.count(6)==1)):
        return 40
    return 0


def yahtzee(count):
    for x in range(len(count)):
        if count[x]==5:
            return 50
    return 0



def chance(roll):
    s = 0
    for x in range(len(roll)):
        s += roll[x]
    return s




