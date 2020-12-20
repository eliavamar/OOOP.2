package gameClient;

import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import com.google.gson.Gson;
import gameClient.util.Point3D;
import org.json.JSONObject;

public class CL_Agent {
    public static final double EPS = 0.0001;
    private static int _count = 0;
    private static int _seed = 3331;
    private static long disTime;
    private int _id;
    private geo_location _pos;
    private double _speed;
    private edge_data _curr_edge;
    private node_data _curr_node;
    private directed_weighted_graph _gg;
    private CL_Pokemon _curr_fruit;
    private long _sg_dt;
    private double _value;

    /**
     * this method is a constructor for a new agent
     *
     * @param g->graph
     * @param start_node->start location of the agent
     */
    public CL_Agent(directed_weighted_graph g, int start_node) {
        _gg = g;
        setMoney(0);
        this._curr_node = _gg.getNode(start_node);
        _pos = _curr_node.getLocation();
        _id = -1;
        setSpeed(0);
    }

    /**
     * this method get a json with new info
     * and up data the:info,speed,pos,dest,src,value.
     * of the agent
     *
     * @param json
     */
    public void update(String json) {
        JSONObject line;
        try {
            line = new JSONObject(json);
            JSONObject ttt = line.getJSONObject("Agent");
            int id = ttt.getInt("id");
            if (id == this.getID() || this.getID() == -1) {
                if (this.getID() == -1) {
                    _id = id;
                }
                double speed = ttt.getDouble("speed");
                String p = ttt.getString("pos");
                Point3D pp = new Point3D(p);
                int src = ttt.getInt("src");
                int dest = ttt.getInt("dest");
                double value = ttt.getDouble("value");
                this._pos = pp;
                this.setCurrNode(src);
                this.setSpeed(speed);
                this.setNextNode(dest);
                this.setMoney(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this return the src node of the agent
     *
     * @return src node
     */
    public int getSrcNode() {
        return this._curr_node.getKey();
    }

    /**
     * this method return the info of the agent
     * as a json string
     *
     * @return string-> agent information
     */
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * this method set a new value to this agent
     *
     * @param v->new value
     */
    private void setMoney(double v) {
        _value = v;
    }

    /**
     * this method set a new dest to this agent
     *
     * @param dest->the new destination
     * @return true if there is edge to the destination else return false
     */
    public boolean setNextNode(int dest) {
        boolean ans = false;
        int src = this._curr_node.getKey();
        this._curr_edge = _gg.getEdge(src, dest);
        if (_curr_edge != null) {
            ans = true;
        } else {
            _curr_edge = null;
        }
        return ans;
    }

    /**
     * this method set a new cuurr node to this agent
     *
     * @param src->curr node
     */
    public void setCurrNode(int src) {
        this._curr_node = _gg.getNode(src);
    }

    /**
     * this method return the id that associated with this agent
     *
     * @return id of this agent
     */
    public int getID() {
        // TODO Auto-generated method stub
        return this._id;
    }

    /**
     * this method return the position of this agent
     *
     * @return geo_location->position of this agent
     */
    public geo_location getLocation() {
        // TODO Auto-generated method stub
        return _pos;
    }

    /**
     * this method return tje value of this agent
     *
     * @return value of the agent
     */
    public double getValue() {
        // TODO Auto-generated method stub
        return this._value;
    }

    /**
     * this method return the destination of the agent
     *
     * @return the destination if there is none return -1
     */
    public int getNextNode() {
        int ans = -2;
        if (this._curr_edge == null) {
            ans = -1;
        } else {
            ans = this._curr_edge.getDest();
        }
        return ans;
    }

    /**
     * this method return the speed of the agent
     *
     * @return speed
     */
    public double getSpeed() {
        return this._speed;
    }

    /**
     * this method a new speed to this agent
     *
     * @param v->new speed of this agent
     */
    public void setSpeed(double v) {
        this._speed = v;
    }

    /**
     * this method return the target pokemon of the agent
     *
     * @return pokemon->the target of the agent
     */
    public CL_Pokemon get_curr_fruit() {
        return _curr_fruit;
    }

    /**
     * this method set the time from pokemon to the end of the edge
     *
     * @param time->time from the pokemon to the end of the edge
     */
    public void setDisTime(long time) {
        this.disTime = time;
    }

    /**
     * this method get the time from the pokemon up to the end of the edge
     *
     * @return time
     */
    public long getDisTime() {
        return this.disTime;
    }

    /**
     * this method set new pokemon target to this agent
     *
     * @param curr_fruit->new target for the agent
     */
    public void set_curr_fruit(CL_Pokemon curr_fruit) {
        this._curr_fruit = curr_fruit;
    }

    /**
     * this method set time that take the agent to cross the edge
     * if there is pokemon on the edge of the agent than the method
     * set the time up to the pokemon
     *
     * @param ddtt->default time
     */
    public void set_SDT(long ddtt) {
        long ddt = ddtt;
        long finalTime = 0;
        if (this._curr_edge != null) {
            double w = get_curr_edge().getWeight();
            geo_location dest = _gg.getNode(get_curr_edge().getDest()).getLocation();
            geo_location src = _gg.getNode(get_curr_edge().getSrc()).getLocation();
            double de = src.distance(dest);
            double dist = _pos.distance(dest);
            if (this.get_curr_fruit().get_edge() == this.get_curr_edge()) {
                double n = dist / de;
                double d = (w * n) / this.getSpeed();
                finalTime = (long) (1000 * d);
                dist = _curr_fruit.getLocation().distance(this._pos);
            }
            double norm = dist / de;
            double dt = (w * norm) / this.getSpeed();
            ddt = (long) (1000.0 * dt);

            if (this.get_curr_fruit().get_edge() == this.get_curr_edge()) {
                this.setDisTime(finalTime - ddt);
            }
        }
        this.set_sg_dt(ddt);
    }

    /**
     * this method set new current edge to this agent
     *
     * @param edge
     */
    public void set_curr_edge(edge_data edge) {
        this._curr_edge = edge;
    }

    /**
     * this method return the current edge that the agent cross
     *
     * @return edge
     */
    public edge_data get_curr_edge() {
        return this._curr_edge;
    }

    /**
     * this method return the time that take to agent
     * cross a edge if there is pokemon
     * on the edge than the time up to the pokemon
     *
     * @return time
     */
    public long get_sg_dt() {
        return _sg_dt;
    }

    /**
     * this method set a new time for cross the edge
     *
     * @param _sg_dt
     */
    public void set_sg_dt(long _sg_dt) {
        this._sg_dt = _sg_dt;
    }
}