/**
 * This class implements the interface that represents a geo location <x,y,z>, aka Point3D
 */
package gameClient.util;

import api.geo_location;

import java.io.Serializable;

public class Point3D implements geo_location, Serializable {
    private double _x, _y, _z;

    /**
     * this method is a constructor for build a new point
     *
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        _x = x;
        _y = y;
        _z = z;
    }

    /**
     * this method is a copy constructor
     *
     * @param p
     */
    public Point3D(Point3D p) {
        this(p.x(), p.y(), p.z());
    }

    /**
     * this method is constructor for new point 2D
     *
     * @param x
     * @param y
     */
    public Point3D(double x, double y) {
        this(x, y, 0);
    }

    /**
     * this method is a constructor for a new point
     * get a string who contains:x,y,z value and build new point
     *
     * @param s
     */
    public Point3D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
            _z = Double.parseDouble(a[2]);
        } catch (IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for POint3D init, got:" + s + "  should be of format: x,y,x");
            throw (e);
        }
    }

    /**
     * this method return this x
     *
     * @return x
     */
    @Override
    public double x() {
        return _x;
    }

    /**
     * this method return this y
     *
     * @return y
     */
    @Override
    public double y() {
        return _y;
    }

    /**
     * this method return this z
     *
     * @return z
     */
    @Override
    public double z() {
        return _z;
    }

    /**
     * this method return the values:x,y,z as string
     *
     * @return String-> values of x,y,z.
     */
    public String toString() {
        return _x + "," + _y + "," + _z;
    }

    /**
     * this method return the distance between two points
     *
     * @param p2
     * @return distance->from this point to p2.
     */
    @Override
    public double distance(geo_location p2) {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double dz = this.z() - p2.z();
        double t = (dx * dx + dy * dy + dz * dz);
        return Math.sqrt(t);
    }

    /**
     * this method compare between two points
     *
     * @param p
     * @return if the points are equals return true else return false
     */
    public boolean equals(Object p) {
        if (p == null || !(p instanceof geo_location)) {
            return false;
        }
        Point3D p2 = (Point3D) p;
        return ((_x == p2._x) && (_y == p2._y) && (_z == p2._z));
    }


}