package test;

import api.EdgeData;
import api.GeoLocation;
import api.edge_data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CL_PokemonTest {
    public static CL_Pokemon PokemonBuild() {
        CL_Pokemon pok = new CL_Pokemon(null, -1, 2, null);
        return pok;
    }

    @Test
    void get_edge() {
        CL_Pokemon pok = PokemonBuild();
        assertNull(pok.get_edge());
        assertNull(pok.getLocation());

    }

    @Test
    void set_edge() {
        CL_Pokemon pok = PokemonBuild();
        edge_data edge = new EdgeData(1, 4, 7);
        pok.set_edge(edge);
        assertEquals(7, edge.getWeight());
    }

    @Test
    void getLocation() {
        CL_Pokemon pok = PokemonBuild();
        assertNull(pok.getLocation());
    }

    @Test
    void getType() {
        CL_Pokemon pok = PokemonBuild();
        assertEquals(-1, pok.getType());
    }

    @Test
    void getValue() {
        CL_Pokemon pok = PokemonBuild();
        assertEquals(2, pok.getValue());

    }
}