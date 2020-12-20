package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeDataTest {

    @Test
    void getKey() {
        NodeData s = new NodeData();
        assertNotNull(s);
        assertEquals(s.getKey(),s.getTag(),s.getWeight());
        NodeData t = nodeCreator();
        assertEquals(t.getTag(),1);
        t.setTag(0);
        assertEquals(t.getTag(),0);
        assertEquals(t.getWeight(),1);
        t.setWeight(0);
        assertEquals(t.getWeight(),0);
        assertNotNull(t.getLocation());
        assertNotNull(t.getInfo());
        assertEquals(t.getInfo(),"hi");
        t.setInfo("hey");
        assertEquals(t.getInfo(),"hey");

    }

    public NodeData nodeCreator(){
        NodeData t = new NodeData();
        t.setTag(1);
        t.setInfo("hi");
        t.setWeight(1);
        geo_location p = new GeoLocation(1,1,1);
        t.setLocation(p);
        return t;
    }
}