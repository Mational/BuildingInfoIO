package pl.put.poznan.buildinginfo.logic.composite.locations;

public interface Location {
    String getId();
    String getName();
    float getArea();
    float getCube();
    float getHeating();
    float getLight();
}
