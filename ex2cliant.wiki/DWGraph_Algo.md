DWGraph_Algo:
This class implement an interface that represents a Directed (positive) Weighted Graph Theory Algorithms.<br>
![image](https://user-images.githubusercontent.com/74323809/102721475-a957d280-4303-11eb-88e6-bc3cb40663a0.png)<br>
this method creats a new graph identical to the origin one.first we created a new graph and then creats new Identical nodes and then creats each EdgeData and connect the src and dest nodes with the weight of the origin edge. each graph stands for its own. deep copy.<br>
![image](https://user-images.githubusercontent.com/74323809/102721539-1c614900-4304-11eb-951e-ea7869f63a63.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102721554-3ef36200-4304-11eb-96c9-b8c5a7caa0ec.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102721614-8974de80-4304-11eb-9582-ed7c0e3489f1.png)<br>
This method returns true if and only if this graph is strongly connected.
a strongly connected graph is a graph that you can reach each node on the the graph, and each node on the graph can get to any other node.<br>
![image](https://user-images.githubusercontent.com/74323809/102721681-086a1700-4305-11eb-9a86-ddfbdc0cfd15.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102721694-246db880-4305-11eb-9550-703a3bc1843e.png)<br>
this method returns the length of the shortest path between src to dest.
by using Dijkstra's algorithm, we search for the shortest path between the two given nodes.<br>
![image](https://user-images.githubusercontent.com/74323809/102721803-bb3a7500-4305-11eb-8d43-16bfe4d8fb69.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102721892-37cd5380-4306-11eb-9228-2d85642ad8f7.png)<br>
this method changes the direction of each EdgeData in the graph<br>
![image](https://user-images.githubusercontent.com/74323809/102721931-77943b00-4306-11eb-8bee-53351a5f8ad0.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102722016-ec677500-4306-11eb-938f-1e955d8c303c.png)<br>
this method returns a  list of all the nodes in the shortest path between the two given nodes.<br>
returns an empty collection if there is no path between them.<br>
![image](https://user-images.githubusercontent.com/74323809/102722040-20429a80-4307-11eb-9d99-8f1e3bc03244.png)<br>
![image](https://user-images.githubusercontent.com/74323809/102722121-aced5880-4307-11eb-9fe3-84a674ef9af2.png)<br>
public boolean load2(String file)<br>
This method load a graph to this graph algorithm from the given json string.<br>

public boolean load(String file):<br>
This method load a graph to this graph algorithm from the given file name.<br>












