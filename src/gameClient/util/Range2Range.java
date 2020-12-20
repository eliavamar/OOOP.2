package gameClient.util;

import api.geo_location;

/**
 * This class represents a simple world 2 frame conversion (both ways).
 */

public class Range2Range {
    private Range2D _world, _frame;

    /**
     * this method is a constructor for a new range2range
     *
     * @param w
     * @param f
     */
    public Range2Range(Range2D w, Range2D f) {
        _world = new Range2D(w);
        _frame = new Range2D(f);
    }

    /**
     * this method calculate the portion from the world
     * to the point p to the frame
     *
     * @param p
     * @return geo_location: contain the portion
     */
    public geo_location world2frame(geo_location p) {
        Point3D d = _world.getPortion(p);
        Point3D ans = _frame.fromPortion(d);
        return ans;
    }

    /**
     * this method return this frame
     *
     * @return frame.
     */
    public Range2D getFrame() {
        return _frame;
    }
}