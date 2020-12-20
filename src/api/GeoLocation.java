package api;

/**
 * This class implement an interface that represents a geo location x,y,z aka Point3D
 */
public class GeoLocation implements geo_location {
    private double x;
    private double y;
    private double z;

    public GeoLocation() {
        x = y = z = 0;
    }

    /**
     * this method is constructor for new GeoLocation
     *
     * @param x
     * @param y
     * @param z
     */
    public GeoLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * this method set a new x for the nodes location
     *
     * @param nx
     */
    public void setX(double nx) {
        x = nx;
    }

    /**
     * this method set a new y for the nodes location
     *
     * @param ny
     */
    public void setY(double ny) {
        y = ny;
    }

    /**
     * this method set a new z for the nodes location
     *
     * @param nz
     */
    public void setZ(double nz) {
        z = nz;
    }

    /**
     * this method return the x that associated with this node
     *
     * @return x
     */
    @Override
    public double x() {

        return this.x;
    }

    /**
     * this method return the y that associated with this node
     *
     * @return y
     */
    @Override
    public double y() {
        return this.y;
    }

    /**
     * this method return the z that associated with this node
     *
     * @return z
     */
    @Override
    public double z() {
        return this.z;
    }

    /**
     * this method calculate the distance from each point in the graph
     *
     * @return the distance -> from:(x1,y1,z1),(x2,y2,z2)->position on the graph
     */
    @Override
    public double distance(geo_location g) {
        double X = Math.pow(this.x - g.x(), 2);
        double Y = Math.pow(this.y - g.y(), 2);
        double Z = Math.pow(this.z - g.z(), 2);
        double dis = Math.sqrt(X + Y + Z);
        return dis;
    }

    /**
     * this two methods (equals,hasCode) compere between two geo
     * return true if them are equals else return false
     *
     * @param o
     * @return true if equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoLocation that = (GeoLocation) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}