# play Yahtzee

from yahtzee import *
def make_scorecard():
    #Create a blank scorecard with None for the value of each.
    scorecard = {}
    # This needs to be modified to a dictionary with None for the value.
    score = {'Ones': None, 'Twos':None, 'Threes': None, 'Fours': None, 'Fives': None, 'Sixes': None, '3 of Kind': None, '4 of Kind': None, 'Full House': None, 'Sm Straight': None, 'Lg Straight': None, 'Yahtzee': None, 'Chance': None}
    return score

def get_score(option,roll,count):
    #Given an option, return the score that would be earned from the roll.
    if option == 'Ones':
        return upper_score(count,1)
    if option == 'Twos':
        return upper_score(count,2)
    if option == 'Threes':
        return upper_score(count,3)
    if option == 'Fours':
        return upper_score(count,4)
    if option == 'Fives':
        return upper_score(count,5)
    if option == 'Sixes':
        return upper_score(count,6)
    if option == '3 of Kind':
        return three_of_kind(roll,count)
    if option == '4 of Kind':
        return four_of_kind(roll,count)
    if option == 'Full House':
        return full_house(count)
    if option == 'Sm Straight':
        return small_straight(roll)
    if option == 'Lg Straight':
        return large_straight(roll)
    if option == 'Yahtzee':
        return yahtzee(count)
    if option == 'Chance':
        return chance(roll)

def select_score(scorecard, roll):
    '''
    Display to the player both their current scorecard and score options. Note that if there is a score recorded in a scoring category, then that is NOT a viable option (only when it has a value of "None" can the player choose that option).

    Get input from the user to select a scoring category (ask until they enter a viable one). For example, the player might have the option to score a roll as 6 for 'Twos' or 14 for 'Three of a Kind' and the player chooses 'Twos'. Or perhaps the player rolled a 4 of a kind, but that was already filled in, so they might have to choose 3 of a kind.

    PARAMETERS
    the player's scorecard and the current roll of the die

    Return
    BOTH the selected score option and the score to add to the card. For example, return "3 of a Kind", 20
    '''
    #sets a value equal to the count of the rolls
    count = count_values(roll)
    print(scorecard)
    print(count)
    #sets a variable equal to a user input
    option = input('Please select your scoring option')
    #checks to see if the user input is valid
    while scorecard[option]!=None:
        option = input('Invalid selection, Please select your scoring option')
    #adds the valid roll score to the players scoreboard
    scorecard[option] = get_score(option,roll,count)
    
    # An example of printing a possible score
    # Modify this so that you are iterating over the dictionary
    # to print all potential scores. Notice how the dictionary key
    # can be used.
    print('{: >12}:{:>3}'.format(option,get_score(option,roll,count)))


def initialize_game():
    '''
    Get information from the users and set up the game for each person.
    - First, determine how many people are playing.
    - Ask each person their name.
    - Create a score card for each person (call the function).
    - Add each scorecard to the dictionary "players", keyed by name.

    Return the "players" dictionary
    '''
    #creates a blank dictionary for all of the players
    players = {}
    #gets an input equal to the number of players
    num_players = int(input('How many people are playing the game?'))
    #asks the user to input all of the players names, then it puts them in the dictionary
    for x in range(num_players):
        player_name = input('Please enter the player name')
        players[player_name] = make_scorecard() 
    
    return players

def determine_winner(players):
    '''
    Add the score for each player and determine who has the highest score. Print the winner and congratulate them.

    PARAMETER: players dictionary
    '''
    #sets a variable equal to the player with the highest score
    top_player = max(players, key=players.get)
    #prints out the players name with the highest score then ends the program
    print('AND THE WINNER IS............   {}'.format(top_player))
    pass
        


def play_game():
    '''
    Initialize the game (call the function).
    Have each player roll 13 times, updating their score after each roll.
    - Tell the players whose turn it is.
    - Have the player push a button to roll the dice
    - Have the player select one of the scoring options (call the function).
    - Update their scorecard
    Determine who won and report the results (call the function)
    '''
    #starts the game by calling the initial game function
    players = initialize_game()
    #creates a loop that will simulate the 13 turns each player gets
    for r in range(13):
        #creates a loop that will allow the players to roll and then select their score
        for player,scorecard in players.items():
            input('{}, Press enter to roll'.format(player))
            roll = roll_dice()
            select_score(scorecard,roll)
    determine_winner(players)
    
    pass
if __name__ == "__main__":
    play_game()


