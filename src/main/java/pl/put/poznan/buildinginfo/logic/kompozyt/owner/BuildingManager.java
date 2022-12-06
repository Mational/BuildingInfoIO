package pl.put.poznan.buildinginfo.logic.kompozyt.owner;

import pl.put.poznan.buildinginfo.logic.kompozyt.lokalizacje.Room;
import pl.put.poznan.buildinginfo.logic.kompozyt.lokalizacje.CompoundLocation;

public class BuildingManager {
    public static void main (String[] args)
    {
        Room room1 = new Room("1.1.1", "pokój101", 14.2f, 28.4f, 13.2f, 15.0f);
        Room room2 = new Room("1.1.2", "pokój102", 15.2f, 20.4f, 14.2f, 16.0f);
        Room room3 = new Room("1.2.1", "pokój201", 12.2f, 24.4f, 10.2f, 15.3f);
        Room room4 = new Room("1.2.2", "pokój202", 14.7f, 29.0f, 13.6f, 13.0f);

        CompoundLocation level1 = new CompoundLocation("1.1", "piętro1");
        CompoundLocation level2 = new CompoundLocation("1.2", "piętro2");

        CompoundLocation building = new CompoundLocation("1", "building1");

        level1.add(room1);
        level1.add(room2);

        level2.add(room3);
        level2.add(room4);

        building.add(level1);
        building.add(level2);

        System.out.println(building.getId());
        System.out.println(building.getName());
        System.out.println(building.getArea());
        System.out.println(building.getCube());
        System.out.println(building.getHeating());
        System.out.println(building.getLight());
        System.out.println(building.getHeatingPerCube());
        System.out.println(building.getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(level1.getId());
        System.out.println(level1.getName());
        System.out.println(level1.getArea());
        System.out.println(level1.getCube());
        System.out.println(level1.getHeating());
        System.out.println(level1.getLight());
        System.out.println(level1.getHeatingPerCube());
        System.out.println(level1.getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(level2.getId());
        System.out.println(level2.getName());
        System.out.println(level2.getArea());
        System.out.println(level2.getCube());
        System.out.println(level2.getHeating());
        System.out.println(level2.getLight());
        System.out.println(level2.getHeatingPerCube());
        System.out.println(level2.getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(room1.getId());
        System.out.println(room1.getName());
        System.out.println(room1.getArea());
        System.out.println(room1.getCube());
        System.out.println(room1.getHeating());
        System.out.println(room1.getLight());
    }
}
