package gameClient.util;

/**
 * This class represents a simple 1D range of shape [min,max]
 */
public class Range {
    private double _min, _max;

    /**
     * this method is constructor for new range
     *
     * @param max
     * @param min
     */
    public Range(double min, double max) {
        set_min(min);
        set_max(max);
    }

    /**
     * this method is a copy constructor for range
     *
     * @param x
     */
    public Range(Range x) {
        this(x._min, x._max);
    }

    /**
     * this method return a string
     *
     * @return string with min value and max value
     */
    public String toString() {
        String ans = "[" + this.get_min() + "," + this.get_max() + "]";
        if (this.isEmpty()) {
            ans = "Empty Range";
        }
        return ans;
    }

    /**
     * this method check if min big than max
     *
     * @return true is min big than max else return false
     */
    public boolean isEmpty() {
        return this.get_min() > this.get_max();
    }

    /**
     * this method return max value
     *
     * @return max
     */
    public double get_max() {
        return _max;
    }

    /**
     * this method return the length of the range
     *
     * @return max-min(length)
     */
    public double get_length() {
        return _max - _min;
    }

    /**
     * this method set a new max to the range
     *
     * @param _max
     */
    private void set_max(double _max) {
        this._max = _max;
    }

    /**
     * this method return the min value of the range
     *
     * @return _min
     */
    public double get_min() {
        return _min;
    }

    /**
     * this method set a new min value to the range
     *
     * @param _min
     */
    private void set_min(double _min) {
        this._min = _min;
    }

    /**
     * this method calculate the d portion and return it
     *
     * @param d
     * @return d portion
     */
    public double getPortion(double d) {
        double d1 = d - _min;
        double ans = d1 / get_length();
        return ans;
    }

    /**
     * this method portion from p and return it
     *
     * @param p
     * @return portion from p
     */
    public double fromPortion(double p) {
        return _min + p * get_length();
    }
}
