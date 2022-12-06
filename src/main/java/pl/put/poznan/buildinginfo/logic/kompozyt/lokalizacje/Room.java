package pl.put.poznan.buildinginfo.logic.kompozyt.lokalizacje;

public class Room extends BaseLocation {
    private float area;
    private float cube;
    private float heating;
    private float light;

    public Room(String id, String name, float area, float cube, float heating, float light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    @Override
    public float getArea() {
        return area;
    }

    @Override
    public float getCube() {
        return cube;
    }

    @Override
    public float getHeating() {
        return heating;
    }

    @Override
    public float getLight() {
        return light;
    }
}
