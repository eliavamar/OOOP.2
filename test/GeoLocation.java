package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoLocationTest {

    @Test
    void setX() {
        GeoLocation t = new GeoLocation();
        assertEquals(t.x(),t.y(),t.z());
        t.setX(1);
        assertNotEquals(t.x(),t.y(),t.z());
        t.setX(0);
        GeoLocation s = new GeoLocation(3,4,0);
        assertEquals(t.distance(s),5);

    }

}