package pl.put.poznan.buildinginfo.logic.composite.locations;

abstract class BaseLocation implements Location {
    public String id;
    public String name;
    BaseLocation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getArea() {
        return 0;
    }

    @Override
    public float getCube() {
        return 0;
    }

    @Override
    public float getHeating() {
        return 0;
    }

    @Override
    public float getLight() {
        return 0;
    }

    @Override
    public float getLightPerSquare() {
        return 0;
    }

    @Override
    public float getHeatingPerCube() {
        return 0;
    }
}
