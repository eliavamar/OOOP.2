package api;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * This class implement an interface that represents a Directed (positive) Weighted Graph Theory Algorithms including:
 * 0. clone(); (copy)
 * 1. init(graph);
 * 2. isConnected(); // strongly (all ordered pais connected)
 * 3. double shortestPathDist(int src, int dest);
 * 4. List of node dadta shortestPath(int src, int dest);
 * 5. Save(file); // JSON file
 * 6. Load(file); // JSON file
 * 7.searchComponents;
 * switchGraph
 */
public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graphAlgo;

    /**
     * this method is constructor for new DWGraph
     */
    public DWGraph_Algo() {
        graphAlgo = new DWGraph_DS();
    }

    /**
     * this method init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(directed_weighted_graph g) {
        graphAlgo = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return graphAlgo->the graph that we working on
     */
    @Override
    public directed_weighted_graph getGraph() {
        return graphAlgo;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return copy graph->new deep copy of this graph
     */
    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph copyGraph = new DWGraph_DS();
        Iterator<node_data> nodes = graphAlgo.getV().iterator();
        while (nodes.hasNext()) {
            node_data n = nodes.next();
            node_data nodeCopy = new NodeData(n);
            copyGraph.addNode(nodeCopy);
        }
        nodes = graphAlgo.getV().iterator();
        while (nodes.hasNext()) {
            node_data n = nodes.next();
            Iterator<edge_data> edgs = graphAlgo.getE(n.getKey()).iterator();
            while (edgs.hasNext()) {
                edge_data edge = edgs.next();
                int src, dest;
                double w;
                src = edge.getSrc();
                dest = edge.getDest();
                w = edge.getWeight();
                copyGraph.connect(src, dest, w);
            }


        }

        return copyGraph;
    }

    /**
     * this method returns true if and only if (iff) there is a valid path from each node to each other
     *
     * @return true if the graph is connected else return false
     */
    @Override
    public boolean isConnected() {
        if (graphAlgo == null || graphAlgo.nodeSize() == 0)
            return true;
        for (node_data node1 : graphAlgo.getV()) {
            node1.setTag(-1);
        }
        node_data node = graphAlgo.getV().iterator().next();
        ArrayDeque<node_data> queue = new ArrayDeque<node_data>();
        queue.addFirst(node);
        node.setTag(0);
        int c = 0;
        while (!queue.isEmpty()) {
            node_data tempNode = queue.pollLast();
            c++;
            for (edge_data edge : graphAlgo.getE(tempNode.getKey())) {
                if (graphAlgo.getNode(edge.getDest()).getTag() == -1) {
                    graphAlgo.getNode(edge.getDest()).setTag(0);
                    queue.addFirst(graphAlgo.getNode(edge.getDest()));
                }

            }

        }


        directed_weighted_graph gr = this.switchGraph();
        for (node_data temp : gr.getV())
            temp.setTag(-1);
        node = gr.getNode(node.getKey());
        queue = new ArrayDeque<node_data>();
        queue.addFirst(node);
        node.setTag(0);
        int c2 = 0;
        while (!queue.isEmpty()) {
            node_data tempNode = queue.pollLast();
            c2++;
            for (edge_data edge : gr.getE(tempNode.getKey())) {
                if (gr.getNode(edge.getDest()).getTag() == -1) {
                    gr.getNode(edge.getDest()).setTag(0);
                    queue.addFirst(gr.getNode(edge.getDest()));
                }

            }
        }
        if (c == c2 && c == graphAlgo.nodeSize())
            return true;
        else return false;
    }


    /**
     * this method  returns the length of the shortest path between src to dest
     * if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return dest weight ->which contain the shortest path
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (graphAlgo.getNode(src) == null || graphAlgo.getNode(dest) == null)
            return -1;
        if (src == dest) return 0;
        PriorityQueue<node_data> queue = new PriorityQueue<node_data>(new DWGraph_DS());
        Iterator<node_data> allNodes = graphAlgo.getV().iterator();
        node_data node = new NodeData();
        while (allNodes.hasNext()) {
            node = allNodes.next();
            node.setTag(-1);
            node.setWeight(-1);
        }

        node = graphAlgo.getNode(src);
        queue.add(node);
        node.setTag(0);
        node.setWeight(0);
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (edge_data edge : graphAlgo.getE(node.getKey())) {
                node_data nextNode = graphAlgo.getNode(edge.getDest());
                if (nextNode.getTag() == -1) {
                    nextNode.setTag(0);
                    nextNode.setWeight(edge.getWeight() + node.getWeight());
                    queue.add(nextNode);
                } else if (nextNode.getTag() == 0 && nextNode.getWeight() >= edge.getWeight() + node.getWeight()) {
                    nextNode.setWeight(edge.getWeight() + node.getWeight());

                }
            }

        }
        if (graphAlgo.getNode(dest).getTag() == -1)
            return -1;
        else return graphAlgo.getNode(dest).getWeight();

    }

    /**
     * this method switch the edges of the graph and return a deep copy
     * of te new graph
     *
     * @return new copy of the new graph
     */
    public directed_weighted_graph switchGraph() {
        directed_weighted_graph sGraph = new DWGraph_DS();
        for (node_data node : graphAlgo.getV()) {
            node_data copyNode = new NodeData(node);
            sGraph.addNode(copyNode);
        }
        for (node_data node : graphAlgo.getV()) {
            for (edge_data edge : graphAlgo.getE(node.getKey())) {
                sGraph.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
            }

        }

        return sGraph;
    }

    /**
     * this method returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        double path = this.shortestPathDist(src, dest);
        if (path == -1)
            return null;
        directed_weighted_graph graph = this.switchGraph();
        node_data node = graph.getNode(dest);
        LinkedList<node_data> list = new LinkedList<node_data>();
        if (dest == src) {
            list.addFirst(node);
            return list;
        }
        Iterator<node_data> allGraph = graph.getV().iterator();
        list.addFirst(node);
        while (node.getKey() != src) {
            for (edge_data edge : graph.getE(node.getKey())) {
                if (edge.getWeight() + graph.getNode(edge.getDest()).getWeight() == node.getWeight()) {
                    node = graph.getNode(edge.getDest());
                    list.addFirst(node);
                    break;
                }

            }

        }

        return list;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(graphAlgo, writer);
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */

    @Override
    public boolean load(String file) {


        JsonDeserializer<directed_weighted_graph> deserializer = new JsonDeserializer<directed_weighted_graph>() {
            @Override
            public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonobject = jsonElement.getAsJsonObject();
                directed_weighted_graph graph = new DWGraph_DS();
                JsonArray arr = jsonobject.get("Nodes").getAsJsonArray();
                for (JsonElement e : arr) {
                    node_data node = new NodeData(e.getAsJsonObject().get("id").getAsInt());
                    String str = e.getAsJsonObject().get("pos").getAsString();
                    String[] arrStr = str.split(",");
                    double[] convertedStr = new double[arrStr.length];
                    try {
                        for (int i = 0; i < arrStr.length; i++) {
                            convertedStr[i] = Double.parseDouble(arrStr[i]);
                        }
                    } catch (NumberFormatException ex) {
                        return null;
                    }
                    geo_location geo = new GeoLocation(convertedStr[0], convertedStr[1], convertedStr[2]);
                    node.setLocation(geo);
                    graph.addNode(node);
                }
                arr = jsonobject.getAsJsonArray("Edges");
                try {
                    for (JsonElement e : arr) {
                        graph.connect(e.getAsJsonObject().get("src").getAsInt(), e.getAsJsonObject().get("dest").getAsInt(), e.getAsJsonObject().get("w").getAsDouble());

                    }
                } catch (Exception e) {
                    return null;
                }


                return graph;
            }

        };
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(directed_weighted_graph.class, deserializer);
        Gson customJson = gsonBuilder.create();
        try {
            directed_weighted_graph customGraph = customJson.fromJson(new FileReader(file), directed_weighted_graph.class);
            graphAlgo = customGraph;
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the json string was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    public boolean load2(String file) {
        JsonDeserializer<directed_weighted_graph> deserializer = new JsonDeserializer<directed_weighted_graph>() {

            @Override
            public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonobject = jsonElement.getAsJsonObject();
                directed_weighted_graph graph = new DWGraph_DS();
                JsonArray arr = jsonobject.get("Nodes").getAsJsonArray();
                for (JsonElement e : arr) {
                    node_data node = new NodeData(e.getAsJsonObject().get("id").getAsInt());
                    String str = e.getAsJsonObject().get("pos").getAsString();
                    String[] arrStr = str.split(",");
                    double[] convertedStr = new double[arrStr.length];
                    try {
                        for (int i = 0; i < arrStr.length; i++) {
                            convertedStr[i] = Double.parseDouble(arrStr[i]);
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("un success converted ");
                        return null;
                    }
                    geo_location geo = new GeoLocation(convertedStr[0], convertedStr[1], convertedStr[2]);
                    node.setLocation(geo);
                    graph.addNode(node);
                }
                arr = jsonobject.getAsJsonArray("Edges");
                for (JsonElement e : arr) {
                    graph.connect(e.getAsJsonObject().get("src").getAsInt(), e.getAsJsonObject().get("dest").getAsInt(), e.getAsJsonObject().get("w").getAsDouble());

                }


                return graph;
            }

        };
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(directed_weighted_graph.class, deserializer);
        Gson customJson = gsonBuilder.create();
        directed_weighted_graph customGraph = customJson.fromJson(file, directed_weighted_graph.class);
        graphAlgo = customGraph;
        return true;
    }

    /**
     * this method go all over the graph and return all connectivity components in the graph
     * sorted by there length
     *
     * @return list of lists that contain the connectivity component of the graph
     */
    public List<List<node_data>> searchComponents() {
        List<List<node_data>> componentList = new ArrayList<>();
        if (graphAlgo == null || graphAlgo.nodeSize() == 0)
            return componentList;
        for (node_data node : graphAlgo.getV()) {
            boolean flag = true;
            for (List<node_data> list : componentList) {
                if (list != null && list.contains(node)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                componentList.add(searchComponent(node));
            }

        }
        if (componentList.size() > 1) {
            List<List<node_data>> sortComponentList = new ArrayList<List<node_data>>();
            int sizeOfComp = componentList.size();
            while (sizeOfComp != 0) {
                List<node_data> tempList = new ArrayList<>();
                for (List<node_data> list : componentList) {
                    if (list.size() > tempList.size() && !sortComponentList.contains(list)) {
                        tempList = list;
                    }
                }
                sortComponentList.add(tempList);
                sizeOfComp--;
            }
            return sortComponentList;
        }
        return componentList;
    }

    /**
     * this method get a node and return list of his connectivity component
     *
     * @param node
     * @return list of the connectivity component of this node
     */
    private List<node_data> searchComponent(node_data node) {
        for (node_data node1 : graphAlgo.getV()) {
            node1.setTag(-1);
        }
        node_data tempNode = graphAlgo.getNode(node.getKey());
        ArrayDeque<node_data> queue = new ArrayDeque<node_data>();
        List<node_data> list1 = new ArrayList<>();
        queue.addFirst(tempNode);
        tempNode.setTag(0);
        while (!queue.isEmpty()) {
            tempNode = queue.pollLast();
            list1.add(tempNode);
            for (edge_data edge : graphAlgo.getE(tempNode.getKey())) {
                if (graphAlgo.getNode(edge.getDest()).getTag() == -1) {
                    graphAlgo.getNode(edge.getDest()).setTag(0);
                    queue.addFirst(graphAlgo.getNode(edge.getDest()));
                }

            }
        }

        directed_weighted_graph g = this.switchGraph();
        for (node_data n : g.getV()) {
            n.setTag(-1);
        }
        tempNode = g.getNode(node.getKey());
        List<node_data> list2 = new ArrayList<>();
        queue = new ArrayDeque<node_data>();
        queue.addFirst(tempNode);
        tempNode.setTag(0);
        while (!queue.isEmpty()) {
            tempNode = queue.pollLast();
            list2.add(tempNode);
            for (edge_data edge : g.getE(tempNode.getKey())) {
                if (g.getNode(edge.getDest()).getTag() == -1) {
                    g.getNode(edge.getDest()).setTag(0);
                    queue.addFirst(g.getNode(edge.getDest()));
                }

            }
        }
        List<node_data> list3 = new ArrayList<node_data>();
        for (node_data n1 : list1) {
            for (node_data n2 : list2) {
                if (n1.getKey() == n2.getKey()) {
                    list3.add(n1);
                    break;
                }

            }

        }

        return list3;
    }
}