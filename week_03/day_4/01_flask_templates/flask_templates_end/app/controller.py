from flask import render_template, request
from app import app
from app.models.todo_list import tasks, add_new_task
from app.models.task import *

@app.route('/')
def index():
    return render_template('index.html', title='Home', tasks=tasks)

@app.route('/add-task', methods=['POST'])
def add_task():
  taskTitle = request.form['title']
  taskDesc = request.form['description']
  newTask = Task(title=taskTitle, description=taskDesc, done=False)
  add_new_task(newTask)

  return render_template('index.html', title='Home', tasks=tasks)
