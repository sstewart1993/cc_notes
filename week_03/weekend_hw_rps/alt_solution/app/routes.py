from app import app
from flask import render_template, request
from app.models.player import *
from app.models.game import *

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/rock')
def rock():
    return render_template('choice2.html', choice="rock")

@app.route('/paper')
def paper():
    return render_template('choice2.html', choice="paper")

@app.route('/scissors')
def scissors():
    return render_template('choice2.html', choice="scissors")

@app.route('/<choice1>/<choice2>')
def play(choice1, choice2):
    player1 = Player("Michael", choice1)
    player2 = Player("Steve", choice2)
    game = Game()
    result = game.play_game(player1, player2)
    return render_template('result.html', result = result)