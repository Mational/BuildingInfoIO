package pl.put.poznan.buildinginfo.logic.kompozyt.lokalizacje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundLocation extends BaseLocation {
    protected List<Location> children = new ArrayList<>();

    public CompoundLocation(String id, String name, Location... components) {
        super(id, name);
        add(components);
    }

    public void add(Location component) {
        children.add(component);
    }

    public void add(Location... components) {
        children.addAll(Arrays.asList(components));
    }

    public void remove(Location child) {
        children.remove(child);
    }

    public void remove(Location... components) {
        children.removeAll(Arrays.asList(components));
    }
    public void clear() {
        children.clear();
    }

    @Override
    public float getArea() {
        if(children.size() == 0) {
            return 0;
        }

        float overallArea = 0.0f;
        for(Location child : children) {
            overallArea += child.getArea();
        }
        return overallArea;
    }

    @Override
    public float getCube() {
        if(children.size() == 0) {
            return 0;
        }

        float overallCube = 0.0f;
        for(Location child : children) {
            overallCube += child.getCube();
        }
        return overallCube;
    }

    @Override
    public float getHeating() {
        if(children.size() == 0) {
            return 0;
        }

        float overallHeating = 0.0f;
        for(Location child : children) {
            overallHeating += child.getHeating();
        }
        return overallHeating;
    }

    @Override
    public float getLight() {
        if(children.size() == 0) {
            return 0;
        }

        float overallLight = 0.0f;
        for(Location child : children) {
            overallLight += child.getLight();
        }
        return overallLight;
    }

    public float getHeatingPerCube() {
        if(children.size() == 0) {
            return 0;
        }

        return getHeating()/getCube();
    }

    public float getLightPerSquare() {
        if(children.size() == 0) {
            return 0;
        }

        return getLight()/getArea();
    }
}
