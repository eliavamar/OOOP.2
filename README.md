<h1>ex2  README</h1>
@authers Eliav Liav Aamar and Tehila Abadi</p>

<h1>Pokemon Game</h1>
<p align="center">
<img src = "https://sm.ign.com/t/ign_il/screenshot/default/c89b6bc7-0673-4720-b761-bab17c7d53fa-xoq7fo_119x.1280.jpg" width ="900" height="400">

<br>
__File list for api pakage__<br>
NodeData>>				node_info implementation (private class)<br>
GeoLocation>>           geo_location implementation<br>
EdgeData>>              edge_data  implementation<br>
DWGraph_DS>>			weighted_graph implementation<br>
DWGraph_Algo>>			weighted_graph_algorithms implementation<br>

__tests:__<br>
NodeDataTest>>			junit test file for NodeData<br>
GeoLocationTest>>		junit test file for GeoLocation<br>
EdgeDataTest >>		    junit test file for EdgeData</p>
WGraph_DSTestMine>>		junit test file for WGraph_DS and ndoe_info1</p>
WGraph_AlgoTestMine>>	junit test file for WGraph_Algo</p>

*File list for gameCllient pakagee*<br>
util:
Gframe              the frame for the game
panel				the panel for the fraame
PanelOpening		the entering window to the game
Point3D				represents a 3D point
Range				represents a simple 1D range of shape
Range2D				represents 2Drange 
Range2Range			represents a simple world 2 frame conversion

Arena>>				represents a multi Agents Arena which move on a grap
CL_Agent>>			represents the agent on the graph
CL_pokemon>>		represents a pokemon on the graph
Ex2_java>>			this class initiate and run the Pokemon's game
README >>			this file


<h1>Introduction api</h1>
_NodeData class_</p>
Implements the interface of node_data.
represents the node (vertex) and all its properties in adirected weighted undirected graph.

<h1>DWGraph_DS</h1>
Implements the interface of directed_weighted_graph.
Represents directed wighted  graph and all its properties.

<h1>DWGraph_Alg</h1>
Implements the interface of dw_graph_algorithms.
Represents the algorithems on a directed weighted graph.

<h1>GeoLocation</h1>
Implements the interface of geo_location.
Represents the  geo location of an object on directed weighted graph.

<h1>EdgeData</h1>
Implements the interface of edge_data.
Represents a weighted edge on a directed weighted graph.

<h1>Functions implemets api</h1>
NodeData class:
This object contains a unique key, Int tag, String info, double weight and a GeoLocation.
node_info1 is a private class of WGrpah_DS.

<h1>EdgeData class:</h1>
this object contains two ints, representing the surce node and the dest node of this edge. another int tag, double weight, and a string info.

<h1>GeoLocation class:</h1>
this object contains three private doubles, x, y, z, representing the location on the graph

<h1>DWGraph_DS class:</h1>
This class contains the hashmap that represents a graph and all its nodes, and another hashmapthat contains within it another Ashmap that represents all the edges that are in the graph

__public void addNode:__</p>
add the givien node to the graph and init a neighbors list for its neighbors

__public void connect:__</p>
his method connect between two given nodes with a given weight. If the nodes already connected the function updated the weight of the edge.

__public Collection<node_data> getV():__</p>
returns a shallow copy of collection contains all the nodes in the graph
returns an empty collection if there isn't any.

__public Collection<edge_data> getE:__</p>
returns a shallow copy of collection contains all the edges comming out from this node.
returns an empty collection if there isn't any.

__public node_data removeNode:__</p>
removes the given node and all the edges associates to it.

__public edge_data removeEdge:__</p>
removes the given edge from the graph

__DWGraph_Algo:__</p>
 This class implement an interface that represents a Directed (positive) Weighted Graph Theory Algorithms including:
 0. clone(); (copy)
 1. init(graph);
 2. isConnected(); // strongly (all ordered pais connected)
 3. double shortestPathDist(int src, int dest);
 4. List<node_data> shortestPath(int src, int dest);
 5. Save(file); // JSON file
 6. Load(file); // JSON file

__public directed_weighted_graph copy():__</p>
This method creats a new identical graph to the current graph.
creates a new graph, and builds new nodes identical to the nodes from the old graph,
 connects any two adjacent nodes from the old graph and returns the new graph

__public boolean isConnected():__</p>
This mehod returns true if and only if this graph is strongly connected.
This function starts from a random node on the graph and goes through all its neighbors to see if there is a way from it to all the nodes in the graph.
 after that, the function changes the direction of all the edges of the graph and checks whether it is still possible to reach all thenode of the graph from this vertex 
if we failed to perform one of the steps the function will return false.

__public double shortestPathDist(int src, int dest):__</p>
This method calculates the shortest path between two given nodes in a directed weighted graph
We used a Dijkstra's algorithm, we initialized the values of all nodes to be -1.
We started with the node src and from there moved on to all its neighbors so that the weight of each node is updated to the value of the previous node and the edge beteen them weight .
Thus for each node we chose the neighbor with the lowest value until we went through all the nodes of the graph.
The weight value of the dest is the minimum distance between the two nodes.

__public directed_weighted_graph switchGraph():__</p>
Auxiliary function to shortestPath and isconnected methods, creats a new directed weighted graph with opposite direction for each edge.
returns the new graph.

__public List<node_data> shortestPath(int src, int dest):__</p>
this method returns a  list of all the nodes in the shortest path between the two given nodes.
this method first uses shortestPathDist method to calculate the minimum distance between the two given nodes
then we go to the dest node and uses switchGraph to change the direction of each edge in the graph and then goes back to the src node. we added each node at the rode to the list.
returns the list.

__public List<List<node_data>> searchComponents()__</p>
This function finds all the Components connectivity in a graph and builds a list of lists of NodeData  where each list represents a Components connectivity  in the graph. 
The function goes over each node in the graph and calls the auxiliary function searchComponent
And this function returns the Components connectivity
If the node already belongs to another Components connectivity the function does not call the auxiliary function.

__private List<node_data> searchComponent(node_data node):__</p>
This function receives a single node and checks Components connectivity by checking which node it reaches in the graph then it turnsdirection of each edge in the graph using switchGraph method,
 and checks again to which nodes it can get. then makes cutting between these two groups of nodes and returns a list Of the cut

__public boolean save(String file):__</p>
This method saves this weighted graph to the given file name

__public boolean load2(String file)__</p>
This method load a graph to this graph algorithm from the given json string.

__public boolean load(String file):__</p>
This method load a graph to this graph algorithm from the given file name.

<h1>Functions implemets gameClient</h1>
<h1>Gframe class:</h1>
the graphics view of the game. contains contains panel panel. uses super(); methods.
the game updates the Gframe in every move of the game.

<h1>panel class:</h1>
the panel of Gframe. contains private Arena _ar and private Range2Range _w2f.  
creats a panel thats draw the graph of the server and the given pokemons with their values.
added the agent accordding to ex2.java methodes, and show the agent values.
also has a timer that shows how much time has left to the game.

<h1>PanelOpening class:</h1>
a frame for the oppening window of the game. gets an ID and Scenario num from the user and starts the game according to these details.
after the user logged in, this method creats a new thread for the game and starts it.

<h1>Point3D class:</h1>
this method is a constructor for build a new point
contains a copy constructor.

__public Point3D(String s):__</p>
creats a Point3D from a string andd biukd a new poin

__public double distance:__</p>
calculate the distance between two given points, by using math function.
				
<h1>Range class:</h1>
represents a simple 1D range of shape
this method calculate the range of a shape 

__public boolean isEmpty():____</p>
return false iff the shape is empty, the min is bigger then the max.

__public double getPortion(double d):__</p>
this method calculate the d portion and return it

__public double fromPortion(double p):__</p>
this method portion from p and return it
				
__Range2D	class__</p>
This class represents a 2D Range, composed from two 1D Ranges.

__Range2Range class:__</p>
This class represents a simple world 2 frame conversion

__public geo_location world2frame(geo_location p)__</p>
this method calculate the location in the window by using the geolocation of the object.

<h1>Arena class:</h1>
This class represents a multi Agents Arena which move on a graph
contains:
double EPS1, double EPS2 float time, directed_weighted_graph _gg, List<CL_Agent> _agents, List<CL_Pokemon> _pokemons,
List<String> _info, Point3D MIN = new Point3D(0, 100, 0), Point3D MAX = new Point3D(0, 100, 0);

__public float getTime():__</p>
this method returns the time that left to the game, used for the frame.

__public void setPokemons(List<CL_Pokemon> f)__</p>
this method set a new pokemon list to the arena

 __public void setAgents(List<CL_Agent> f)__</p>
this method set a new agent list to this arena

__public void setGraph(directed_weighted_graph g)__</p>
this method set a new graph to this arena

__public List<CL_Agent> getAgents()__</p>
this method return the agent list of this arena

__public static List<CL_Agent> getAgents(String aa, directed_weighted_graph gg)__</p>
this method get a json string and the graph game and return the graph agent list

__public static ArrayList<CL_Pokemon> json2Pokemons(String fs)__</p>
this method get an json string and conver it to pokemon list of the game
this method does not up data the edges of the Pokemon's

__public static void updateEdge(CL_Pokemon fr, directed_weighted_graph g)__</p>
this method get a pokemon and the graph game and up data the edge of the pokemon
by using isOnEdge method

__private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest):__</p>
this method get Location of: pokemon,src edge,dest edge.

__private static Range2D GraphRange(directed_weighted_graph g):__</p>
this method go all over the graph and take its node and convert it
to range2D object.
that make max and min of axis y as the highest and the lowest vertices accordingly
and make the max and the min of axis x as the most right and most left vertices accordingly

__public static Range2Range w2f(directed_weighted_graph g, Range2D frame):__</p>
this method adapt the world's coordinates to the wanted frame's coordinates

<h1>CL_Agent class</h1>:
this objeect contains  EPS, int _count, int _seed, long disTime, int _id, geo_location _pos, double _speed, edge_data _curr_edge, node_data _curr_node,
directed_weighted_graph _gg,  CL_Pokemon _curr_fruit, long _sg_dt, double _value.
runs over the edges of the graph of the server for the given level and try to catch the pokemons.
first, we choose for each agent the next destination and then by using the algorithems in api, we can get the shortest path to the dest node.
then we choose the neext edge, and by the method move the agent can get to the other node' and can catch pokemon.
each pokemon has a value, and this is the "money" of the agent.


__public void update(String json):__</p>
this method get a json with new info and updata the:info,speed,pos,dest,src,value. of the agent

__public boolean setNextNode(int dest):__</p>
this method set a new dest to this agent///////////////////////////////

__public geo_location getLocation():__</p>
 this method return the position of this agent

__public int getNextNode() :__</p>
this method return the destination of the agent

__public CL_Pokemon get_curr_fruit():__</p>
this method returns the corrent pokemon this agent runs after.

__public void set_curr_fruit(CL_Pokemon curr_fruit):__</p>
this method set new pokemon target to this agent

__public void set_SDT(long ddtt):__</p>
This method calculates with the help of mathematical calculations the time it will take for the agent to get from its location to the end of the edge.
 If there is a Pokemon on the edge, the function will calculate the time from the agent to the Pokemon.

<h1>CL_pokemon:</h1>
this class represent a pokemon on the graph.
contains edge_data _edge, double _value, int _type, Point3D _pos, double min_dist, int min_ro.
reprezents the fruit in the game.

__public static CL_Pokemon init_from_json(String json):__</p>
this method get an json object the represent a list of pokemons
and convert it to pokemon list

__public edge_data get_edge():__</p>
this method return the edge of the pokemon

__public Point3D getLocation():__</p>
this method return the location of this pokemon on the graph

__public int getType():__</p>
this method return the type of the pokemon
if the type<0 this pokemon is on edge where the src>dest
if the type>0this pokemon is on edge where the src<dest

__public boolean equals(CL_Pokemon pokemon):__</p>
this method compare between two Pokemon's and return boolean value

<h1>Ex2_java</h1>
this class initiate and run the Pokemon's game.
contains: 
Gframe _win,
Arena _ar,
HashMap<Object, List<node_data>> pokemonList,
DWGraph_Algo gg,
HashMap<Integer, CL_Pokemon> currFruit,
PanelOpening panelOpening.

__public static void main(String[] a):__</p>
this method runs the opening frame for the user to add its ID and the level number.

__public void run():__</p>
this method Initializes the game by calling the init function and as long as the game continues the function will call the move_agent method where the continuation of the agents' route will be determined.

__private static void moveAgants(game_service game)__</p>
Updates the list of Pokemon, the list of agents, the ribs of all the agents, and moves the agents on the graph towards the next 
vertex on the way to the nearest Pokemon. After each move a repaint is performed that initializes the panel


__private static int nextNode(int src, CL_Agent ag, game_service game):__</p>
This method selects for each agent the shortest path to the Pokemon to which it goes, puts the path in a static pokemonlist and puts the Pokemon in the currentfruit list.
If the agent does not complete the route he will not be assigned a new route and two agents will not be able to run towards the same Pokemon.
Returns the destination vertex of the agent that called the function

__private void init(game_service game):__</p>
This method initializes the game, opens the Gframe and determines where to place the Pokemon on the graph if there is one
 Components connectivity it determines this using setOnGraph1, else, it will use setOnGraph2.

__public void setOnGraph1(game_service game, List<CL_Pokemon> pokemons, int numOfAgents, directed_weighted_graph g)__</p>
Gets a pokemonlist, the number of agents in the game, the graph and the game.server.
 Positions the agents next to the Pokemon with the greatest value and if there are more agents left to place, it will place them randomly on the graph.

__public void setOnGraph2(game_service game, int numOfAgent, List<List<node_data>> components)__</p>
This method accepts a number of agents, a list that contains the number of Components connectivity and disperses the agents in the  Components connectivity from the smallest to the largest. 
Note that the list of binding components that the function receives is sorted from large to small
