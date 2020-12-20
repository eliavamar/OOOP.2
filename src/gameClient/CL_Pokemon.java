package gameClient;

import api.edge_data;
import com.google.gson.Gson;
import gameClient.util.Point3D;
import org.json.JSONObject;

/**
 * this class represent a pokemon on the graph
 */
public class CL_Pokemon {
    private edge_data _edge;
    private double _value;
    private int _type;
    private Point3D _pos;
    private double min_dist;
    private int min_ro;

    /**
     * this method is constructor for a new pokemon\
     *
     * @param p ->point 3D
     * @param e ->edge_data
     * @param t ->type of the pokemon
     * @param v ->value of the pokemon
     */
    public CL_Pokemon(Point3D p, int t, double v, edge_data e) {
        _type = t;
        _value = v;
        set_edge(e);
        _pos = p;
        min_dist = -1;
        min_ro = -1;
    }

    /**
     * this method get an json object the represent a list of pokemons
     * and convert it to pokemon list
     *
     * @param json
     * @return pokemon list
     */
    public static CL_Pokemon init_from_json(String json) {
        CL_Pokemon ans = null;
        try {
            JSONObject p = new JSONObject(json);
            int id = p.getInt("id");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * this method return a json string
     *
     * @return string-> json
     */
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * this method return the edge of the pokemon
     *
     * @return edge_data->edge of the pokemon
     */
    public edge_data get_edge() {
        return _edge;
    }

    /**
     * this method set a new edge to this pokemon
     *
     * @param _edge
     */
    public void set_edge(edge_data _edge) {
        this._edge = _edge;
    }

    /**
     * this method return the location of this pokemon on the graph
     *
     * @return point3D->location of the pokemon on the graph
     */
    public Point3D getLocation() {
        return _pos;
    }

    /**
     * this method return the type of the pokemon
     *
     * @return type of this pokemon
     */
    public int getType() {
        return _type;
    }

    /**
     * this method return the value of this pokempn
     *
     * @return value of this pokemon
     */
    public double getValue() {
        return _value;
    }

    /**
     * this method compare between two Pokemon's and return boolean value
     * @param pokemon
     * @return true if the Pokemon's are equals else return false
     * */
    public boolean equals(CL_Pokemon pokemon) {
        boolean ans = true;
        if ((pokemon._pos == null && this._pos != null) || (pokemon._pos != null && this._pos == null))
            ans = false;
        if (pokemon._pos != null && this._pos != null) {
            if (pokemon._pos.x() != this._pos.x())
                ans = false;
            else if (pokemon._pos.y() != this._pos.y())
                ans = false;
            else if (pokemon._pos.x() != this._pos.x())
                ans = false;
            else if (pokemon._type != this._type)
                ans = false;
            else if (pokemon._value != this._value)
                ans = false;

        }

        return ans;
    }

}