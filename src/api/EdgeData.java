package api;

/**
 * This class implement an interface that represents the set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph.
 */

public class EdgeData implements edge_data {
    private int src;
    private int dest;
    private int tag;
    private double weight;
    private String info;

    /**
     * this method is constructor for new edge
     *
     * @param dest1 ->destination of the new edge
     * @param src1  -> source of the edge
     * @param w     -> weight of the edge (most be positive)
     */
    public EdgeData(int src1, int dest1, double w) {
	if(w>0){
        src = src1;
        dest = dest1;
            weight = w;
}
    }

    /**
     * this method return the key of the node src
     *
     * @return  key
     */
    @Override
    public int getSrc() {
        return src;
    }

    /**
     * this method return the key of the node dest
     *
     * @return  key
     */
    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdgeData edgeData = (EdgeData) o;

        if (src != edgeData.src) return false;
        if (dest != edgeData.dest) return false;
        return Double.compare(edgeData.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = src;
        result = 31 * result + dest;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * this method return the weight of this edge
     *
     * @return weight
     */
    @Override
    public double getWeight() {
        return weight;


    }

    /**
     * this method set new weight to this edge
     *
     * @param w
     */
    public void setWeight(double w) {
        if (w > 0)
            weight = w;
    }

    /**
     * this method info that associated with this edge
     *
     * @return info
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * this method set a new info to this edge
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        if (s != null)
            info = s;
    }

    /**
     * this method return the tag who associated with this edge
     *
     * @return tag
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * this nethod set a new tag to this edge
     *
     * @param t
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }

}
