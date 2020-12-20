the agent represents the users tool in this game, the goal is to catch as many pokemons as possibles, and to end the game with the highest value that's possible, with the lowest number of 'move's.
before the game started, each agent gets an edge to start at, if there are a few Components connectivity there is going to be at list one agent for each Component, starting with the biggest one.

this method creats a new agent and initilizes it graph,current pokemon to chase after, geo location, and set it value to be 0.<br>
![image](https://user-images.githubusercontent.com/74323809/102723148-d958a300-430e-11eb-8cd9-38bf79afd116.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102723258-74ea1380-430f-11eb-96b1-95fe378c9d92.png)<br>
this method get jason string with new values for the agent fields, and initilizes the agent with the new values.<br>
![image](https://user-images.githubusercontent.com/74323809/102723316-a4008500-430f-11eb-9c96-3bf2d597cb7b.png)<br>
this method difineds the neext node this agent goes to.<br>
![image](https://user-images.githubusercontent.com/74323809/102723341-ca262500-430f-11eb-804f-aaab227b7ab7.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102723381-28530800-4310-11eb-8fc2-7ab88447de25.png)<br>
tihs method returns the current geo location of this agent<br>
![image](https://user-images.githubusercontent.com/74323809/102723412-64866880-4310-11eb-90aa-70797246e985.png)<br>
this method returns the current value of this agent. the value calcuate according to the value of the pokemons this agent ate.<br>
![image](https://user-images.githubusercontent.com/74323809/102723419-7405b180-4310-11eb-9dd6-11e82f0ae309.png)<br>
this method returns the next node this agent is going to.<br>
![image](https://user-images.githubusercontent.com/74323809/102723450-9d264200-4310-11eb-8891-48dfdadbddec.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102723467-c050f180-4310-11eb-851c-d69d95bb76ad.png)<br>
This method calculates with the help of mathematical calculations the time it will take for the agent to get from its location to the end of the edge.<br>
 If there is a Pokemon on the edge, the function will calculate the time from the agent to the Pokemon.<br>
![image](https://user-images.githubusercontent.com/74323809/102723486-eb3b4580-4310-11eb-84f0-8c624867cf97.png)<br>







