package api;

/**
 * This class implement the interface
 * that represents the set of operations applicable on a
 * node (vertex) in a (directional) weighted graph.
 */

public class NodeData implements node_data {
    private int key;
    private int tag;
    private double weight;
    private String info;
    private geo_location geo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeData nodeData = (NodeData) o;

        if (key != nodeData.key) return false;
        return geo != null ? geo.equals(nodeData.geo) : nodeData.geo == null;
    }

    @Override
    public int hashCode() {
        int result = key;
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        return result;
    }

    /**
     * this method is constructor for new node
     */
    public NodeData() {
        key = tag = 0;
        weight = 0;
        geo = null;
    }

    /**
     * this method is constructor for new node
     *
     * @param key
     */
    public NodeData(int key) {
        tag = 0;
        this.key = key;
        weight = 0;
        geo = null;

    }


    /**
     * this method is copy constructor for new node
     *
     * @param n
     */
    public NodeData(node_data n) {
        if (n != null) {
            key = n.getKey();
            weight = n.getWeight();
            tag = n.getTag();
            info = n.getInfo();
            double x, y, z;
            if (n.getLocation() != null) {
                x = n.getLocation().x();
                y = n.getLocation().y();
                z = n.getLocation().z();
                geo = new GeoLocation(x, y, z);

            }

        }
    }

    /**
     * this method return the key that associated with this node
     *
     * @return key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * this method of this node if there is no node return null
     *
     * @return node location
     */
    @Override
    public geo_location getLocation() {
        return this.geo;
    }

    /**
     * this method set a new location fpr this node
     *
     * @param p
     */
    @Override
    public void setLocation(geo_location p) {
        geo = p;

    }

    /**
     * this method return the weight that associated with this node
     *
     * @return weight
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * this method set a new wight to this node
     *
     * @param w
     */
    @Override
    public void setWeight(double w) {
        weight = w;

    }

    /**
     * this method return the info that associated with this node
     *
     * @return info
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * this method set a new info to this node
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        if (s != null) info = new String(s);
    }

    /**
     * this method return the tag that associated with this node
     *
     * @return tag
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * this method set a new tag to this node
     *
     * @param t
     */
    @Override
    public void setTag(int t) {
        tag = t;
    }


}
