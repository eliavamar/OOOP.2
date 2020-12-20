CL_pokemon:<br>
this class represent a pokemon on the graph.<br>
contains edge_data _edge, double _value, int _type, Point3D _pos, double min_dist, int min_ro.<br>
reprezents the fruit in the game.<br>
during the game, the server put the pokemons according to insider algorithem that the user does not know.<br>
eaach pokemon has a value, the higher the value is, the better it is to catch it for the player.<br>
the type represents the location of the pokemon' if the type is negative the pokemon stends on an edge that its src bigger then the dest,
and if the type is positive, the pokemon stands on edge that its src key is smaller then the dest value.<br>
 
this method creats a new pokemon ant initilizes its values<br>
![image](https://user-images.githubusercontent.com/74323809/102722499-27b77300-430a-11eb-9874-5639d87dc015.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102722570-9a285300-430a-11eb-8236-defbf8b31670.png)<br>
this method returns the edge this pokemon is on.
doring the game the agents need to use 'move' method close enough to the pokemon to 'eat' it and collect its value.<br>
![image](https://user-images.githubusercontent.com/74323809/102722585-af9d7d00-430a-11eb-8b1e-6588c22306cc.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102722716-a6f97680-430b-11eb-95bd-18b7f87c0a29.png)<br>
this method returns the geo location of this pokemon.<br>
![image](https://user-images.githubusercontent.com/74323809/102722861-bc22d500-430c-11eb-921c-9cb11b4828a3.png)<br>


