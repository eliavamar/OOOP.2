package gameClient.util;

import api.geo_location;

/**
 * This class represents a 2D Range, composed from two 1D Ranges.
 */
public class Range2D {
    private Range _y_range;
    private Range _x_range;

    /**
     * this method is a builder for new range2D
     *
     * @param y
     * @param x
     */
    public Range2D(Range x, Range y) {
        _x_range = new Range(x);
        _y_range = new Range(y);
    }

    /**
     * this method is a copy constructor for new range2D
     *
     * @param w
     */
    public Range2D(Range2D w) {
        _x_range = new Range(w._x_range);
        _y_range = new Range(w._y_range);
    }

    /**
     * this method calculate the portion
     * of x value and y value
     * and build a new point with the portion values
     *
     * @param p
     * @return point3D : new point with the portion value
     */
    public Point3D getPortion(geo_location p) {
        double x = _x_range.getPortion(p.x());
        double y = _y_range.getPortion(p.y());
        return new Point3D(x, y, 0);
    }

    /**
     * this method calculate the portion from x and y value of p
     * and build a new point3D with this portion
     *
     * @param p
     * @return point3D:contain the portion value
     */
    public Point3D fromPortion(geo_location p) {
        double x = _x_range.fromPortion(p.x());
        double y = _y_range.fromPortion(p.y());
        return new Point3D(x, y, 0);
    }
}