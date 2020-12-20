package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeDataTest {

    @Test
    void getSrc() {
        EdgeData t = new EdgeData(1,2,1);
        assertNull(t.getInfo());
        assertEquals(t.getSrc(),1);
        assertEquals(t.getDest(),2);
        assertEquals(t.getWeight(),1);
        assertEquals(t.getTag(),0);
        t.setTag(1);
        assertEquals(t.getTag(),1);
        EdgeData s = new EdgeData(2,3,2);
        t=s;
        assertEquals(t.getSrc(),2);
        assertEquals(t.getDest(),3);
        assertEquals(t.getWeight(),2);
        assertEquals(t.getTag(),0);
        assertEquals(t.getInfo(),null);
        t.setInfo("hi");
        assertEquals(t.getInfo(),"hi");



    }

}