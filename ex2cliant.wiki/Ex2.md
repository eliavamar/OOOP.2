this class creats the thread of thiss game, it Initializes the arena, thee agents, the pokeemons, and starts the game.<br>
each game can last 30-60 seconds.<br>
first, the openinig frame asks the user for his ID and the level that he wants to play (there are 24 levels), and the server starts the game according to those details.<br>
dodring the game, there is a frame that opens and show what happens in the game visiually.
while the game is runing, the goal is to collect as many points as possible, and the score consists several components, higher value and number of movees little as possible.

the opening window<br>
![image](https://user-images.githubusercontent.com/74323809/102724030-16279880-4315-11eb-8bf1-5b9d258f8675.png)<br>
the implimentation method of thread.<br>
while runing, the function send the ID and the scenario_num to the server, load the graph and call the init function that set the arena and the frame.<br>
![image](https://user-images.githubusercontent.com/74323809/102724123-e9c04c00-4315-11eb-8ad2-d21edb3d74f3.png)<br>
move agent is the center of this project, this method move each agent to its next destination aand if there is a pokeemon on the current edge of the agent the agentss eats the pokeemons.
it uses NextNode method to choose it next destination.
if the agent is too colse to the pokemon, the function tells the agent to sleep until the right moment and then use 'move' again to eaat the pokemon.<br>
![image](https://user-images.githubusercontent.com/74323809/102724284-38bab100-4317-11eb-88c5-ccfae5d12d48.png)<br>
this method cchooses the next node to each agent according to thee list of the shortest path between the agent and the src of the pokemon's edge.<br>
![image](https://user-images.githubusercontent.com/74323809/102724325-74557b00-4317-11eb-95ea-09971b5797bd.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102724329-7f101000-4317-11eb-9caf-8019fa938fb8.png)<br>
this method Initializing the game>> the arena, frame, pokeemons and agents.<br>
![image](https://user-images.githubusercontent.com/74323809/102724363-b7afe980-4317-11eb-8d73-50c4b941b6a4.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102724366-be3e6100-4317-11eb-99d8-45dd2f01534d.png)<br>

the results of this game:<br>
![image](https://user-images.githubusercontent.com/74323809/102724479-75d37300-4318-11eb-84b0-30e15ce8b594.png)<br>

