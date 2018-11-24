import random
import sys

print("Winning Rules of the Rock paper scissor game as follows: \n"
                                +"Rock vs paper->paper wins \n"
                                + "Rock vs scissor->Rock wins \n"
                                +"paper vs scissor->scissor wins \n")

def play():
    p_choice= input('What do you choose? \n Enter choice \n Rock \n Paper \n Scissor \n').capitalize()
    choice = {1:'Rock', 2:'Paper', 3 : 'Scissors'}
    cpu_choice = choice[random.randint(1,3)]

    if p_choice == cpu_choice:
        return print('Tie')
    if compare(p_choice, cpu_choice):
        return print('You Win!')
    else:
        return print('You Lose!')

def compare(player_choice, cpu_choice):
    results = {('Paper', 'Rock'): True,
               ('Paper', 'Scissors') : False,
               ('Rock', 'Paper'):False,
               ('Rock','Scissors'): True,
               ('Scissors', 'Paper'):True,
               ('Scissors','Rock'): False }
    return results[(player_choice,cpu_choice)]

def game_start():
    begin = input('would you like to play Rock, Paper and Scissors? \n').capitalize()
    while begin != "Yes":
        if begin == "No":
            print('Game Over')
            sys.exit()
        else:
            print('Please try again')
            begin = input('would you like to play Rock, Paper and Scissors? \n').capitalize()

    play()

    while True:
        begin = input('Play again? \n').capitalize()
        while begin != 'Yes':
            if begin == 'No':
                print('Game Over')
                sys.exit()
            else:
                print('Please try again')
                begin= input('Play again? \n').capitalize()
        play()

game_start()        