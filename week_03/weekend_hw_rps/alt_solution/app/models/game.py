from app.models.player import *

class Game:

    def play_game(self, player1, player2):
        
        if player1.choice.capitalize() == player2.choice.capitalize():
            return 'Draw'
        elif player1.choice.capitalize() == 'Rock' and player2.choice.capitalize() == 'Scissors':
            return 'Player 1 Wins, Rock beats Scissors'
        elif player1.choice.capitalize() == 'Scissors' and player2.choice.capitalize() == 'Paper':
            return 'Player 1 Wins, Scissots beats Paper'
        elif player1.choice.capitalize() == 'Paper' and player2.choice.capitalize() == 'Rock':
            return 'Player 1 Wins, Paper beats Rock'
        elif player1.choice.capitalize() == 'Rock' and player2.choice.capitalize() == 'Paper':
            return 'Player 2 Wins, Paper beats Rock'
        elif player1.choice.capitalize() == 'Scissors' and player2.choice.capitalize() == 'Rock':
            return 'Player 2 Wins, Rock beats Paper'
        elif player1.choice.capitalize() == 'Paper' and player2.choice.capitalize() == 'Scissors':
            return 'Player 2 Wins, Scissors beats Paper'


        

