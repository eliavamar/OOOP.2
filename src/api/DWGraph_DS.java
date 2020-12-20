package api;

import javax.swing.*;
import java.util.*;

public class DWGraph_DS implements directed_weighted_graph, Comparator<node_data> {
    private HashMap<Integer, node_data> graph;
    private HashMap<Integer, HashMap<Integer, edge_data>> dEdge;
    private int MC;
    private int edgeSize;

    /**
     * this method is constructor for new DirectedWeightedGraph
     */
    public DWGraph_DS() {

        graph = new HashMap<Integer, node_data>();
        dEdge = new HashMap<Integer, HashMap<Integer, edge_data>>();
        MC = edgeSize = 0;
    }

    /**
     * this method return the node that associated with this key
     * null if none.
     *
     * @param key
     * @return node
     */
    @Override
    public node_data getNode(int key) {
        return graph.get(key);
    }

    /**
     * this method return the edge between node src and node dest
     * return null of none
     *
     * @param src
     * @param dest
     * @return edge
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        if (dEdge.get(src) == null)
            return null;
        return dEdge.get(src).get(dest);
    }

    /**
     * add a new node to the graph with the giving node_data
     *
     * @param n
     */
    @Override
    public void addNode(node_data n) {
        if (n == null || graph.get(n.getKey()) != null)
            return;
        graph.put(n.getKey(), n);
        HashMap<Integer, edge_data> edge = new HashMap<Integer, edge_data>();
        dEdge.put(n.getKey(), edge);
        MC++;

    }


    /**
     * Connects an edge with weight w between node src to node dest.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (src != dest && graph.get(src) != null && graph.get(dest) != null && w > 0) {
            if (dEdge.get(src).get(dest) == null) {
                EdgeData e = new EdgeData(src, dest, w);
                dEdge.get(src).put(dest, e);
                MC++;
                edgeSize++;

            } else if (w != dEdge.get(src).get(dest).getWeight() && w > 0) {
                EdgeData e = new EdgeData(src, dest, w);
                dEdge.get(src).put(dest, e);
                MC++;

            }

        }

    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     *
     * @return Collection of the node_data
     */
    @Override
    public Collection<node_data> getV() {
        return graph.values();
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     *
     * @param node_id
     * @return Collection of the edge_data
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        if (dEdge.get(node_id) != null)
            return dEdge.get(node_id).values();
        Collection<edge_data> c = new ArrayList<edge_data>();
        return c;
    }

    /**
     * Deletes the node (with the given key) from the graph -
     * and removes all edges which starts or ends at this node.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        if (graph.get(key) == null)
            return null;
        int rmSize = dEdge.get(key).size();
        dEdge.remove(key);
        Iterator<node_data> nodes = getV().iterator();
        while (nodes.hasNext()) {
            node_data node = nodes.next();
            int s = node.getKey();
            int d = key;
            if (s != key && dEdge.get(s).get(d) != null) {
                edge_data w = dEdge.get(s).get(d);
                dEdge.get(s).remove(d);
                edgeSize--;
            }


        }
        MC++;
        edgeSize = edgeSize - (rmSize);
        return graph.remove(key);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        return true;
    }

    /**
     * this method deletes the edge from the graph
     *
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */

    @Override
    public edge_data removeEdge(int src, int dest) {
        if (graph.get(src) == null || graph.get(dest) == null || dest == src || dEdge.get(src).get(dest) == null)
            return null;
        edgeSize--;
        MC++;
        return dEdge.get(src).remove(dest);

    }

    /**
     * this method returns the number of vertices (nodes) in the graph.
     *
     * @return node size->number of nodes in the graph
     */

    @Override
    public int nodeSize() {
        return graph.size();
    }

    /**
     * Returns the number of edges
     *
     * @return edge size->number of edges in the graph
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * this method returns the Mode Count - for testing changes in the graph.
     *
     * @return MC->number of changes in the graph
     */
    @Override
    public int getMC() {
        return MC;
    }


    /**
     * this method compare between two nodes by their weight
     *
     * @param o1
     * @param o2
     * @return and->witch mean who's the bigger one
     */
    @Override
    public int compare(node_data o1, node_data o2) {
        int ans = 0;
        if (o1.getWeight() > o2.getWeight())
            ans = 1;
        if (o2.getWeight() > o1.getWeight())
            ans = -1;
        return ans;
    }

}