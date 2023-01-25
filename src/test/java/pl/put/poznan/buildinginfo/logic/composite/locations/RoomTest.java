package pl.put.poznan.buildinginfo.logic.composite.locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;
    @BeforeEach
    void setUp(){
        room = new Room("1.1.1", "room101", 14.2f, 28.4f, 13.2f, 15f);
    }

    @Test
    void getLightPerSquareTest() {
        assertEquals(1.0563380718231201, room.getLightPerSquare(),
                "Light per square doesn't work properly.");
    }

    @Test
    void getHeatingPerCubeTest() {
        assertEquals(0.4647887349128723, room.getHeatingPerCube(),
                "Heating per cube doesn't work properly.");
    }

}