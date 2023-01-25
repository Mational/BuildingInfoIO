package pl.put.poznan.buildinginfo.logic.composite.locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompoundLocationTest {

    Room mock_1 = mock(Room.class);
    Room mock_2 = mock(Room.class);
    Room mock_3 = mock(Room.class);
    Room mock_4 = mock(Room.class);
    CompoundLocation floor;

    @BeforeEach
    void setUp() {
        when(mock_1.getLight()).thenReturn(15.3f);
        when(mock_1.getArea()).thenReturn(14.2f);
        when(mock_1.getCube()).thenReturn(28.4f);
        when(mock_1.getHeating()).thenReturn(13.2f);
        when(mock_1.getHeatingPerCube()).thenReturn(0.46478873f);

        when(mock_2.getLight()).thenReturn(14.7f);
        when(mock_2.getArea()).thenReturn(15.2f);
        when(mock_2.getCube()).thenReturn(30.4f);
        when(mock_2.getHeating()).thenReturn(14.2f);
        when(mock_2.getHeatingPerCube()).thenReturn(0.46710527f);

        when(mock_3.getLight()).thenReturn(15.2f);
        when(mock_3.getArea()).thenReturn(14.5f);
        when(mock_3.getCube()).thenReturn(29.0f);
        when(mock_3.getHeating()).thenReturn(13.6f);
        when(mock_3.getHeatingPerCube()).thenReturn(0.46896553f);

        when(mock_4.getLight()).thenReturn(10.0f);
        when(mock_4.getArea()).thenReturn(12.2f);
        when(mock_4.getCube()).thenReturn(24.4f);
        when(mock_4.getHeating()).thenReturn(10.2f);
        when(mock_4.getHeatingPerCube()).thenReturn(0.4180328f);

        floor = new CompoundLocation("1.2", "floor1", mock_1, mock_2, mock_3, mock_4);
    }

    @Test
    void getLightTest() {
        assertEquals(55.2f,floor.getLight(),
                "getLight function for CompoundLocation doesn't work properly.");
    }

    @Test
    void getAreaTest() {
        assertEquals(56.1f,floor.getArea(),
                "getArea function for CompoundLocation doesn't work properly.");
    }

    @Test
    void getCubeTest() {
        assertEquals(112.2f,floor.getCube(),
                "getCube function for CompoundLocation doesn't work properly.");
    }

    @Test
    void getHeatingTest() {
        assertEquals(51.2f,floor.getHeating(),
                "getHeating function for CompoundLocation doesn't work properly.");
    }

    @Test
    void getHeatingPerCubeTest() {
        assertEquals(0.4547230825f,floor.getHeatingPerCube(),
                "getHeatingPerCube function for CompoundLocation doesn't work properly.");
    }
}