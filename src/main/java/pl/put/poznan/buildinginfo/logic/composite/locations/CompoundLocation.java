package pl.put.poznan.buildinginfo.logic.composite.locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for creating complex locations such as a building or floor.
 * It is part of the composite design pattern.
 */
public class CompoundLocation extends BaseLocation {
    protected List<Location> children = new ArrayList<>();

    /**
     * This method is a constructor of the CompoundLocation class. It is an extension of the BaseLocation abstract class.
     * It calls the constructor of the BaseLocation class and calls its own add() method.
     * @param id Unique ID of location
     * @param name Unique name of location
     * @param components A list of locations that are part of the compound location that is being created.
     */
    public CompoundLocation(String id, String name, Location... components) {
        super(id, name);
        add(components);
    }

    /**
     * This method is responsible for adding location to the list of locations called children.
     * @param component CompoundLocation or Room class object.
     */
    public void add(Location component) {
        children.add(component);
    }

    /**
     * This method is responsible for adding multiple locations to the list of locations called children.
     * @param components List of CompoundLocation or Room class objects.
     */
    public void add(Location... components) {
        children.addAll(Arrays.asList(components));
    }

    /**
     * This method is responsible for removing single location from the list of locations called children.
     * @param child CompoundLocation or Room class object.
     */
    public void remove(Location child) {
        children.remove(child);
    }

    /**
     * This method is responsible for removing multiple locations from the list of locations called children.
     * @param components List of CompoundLocation or Room class objects.
     */
    public void remove(Location... components) {
        children.removeAll(Arrays.asList(components));
    }

    /**
     * This method is responsible for clearing the list of locations called children.
     */
    public void clear() {
        children.clear();
    }

    /**
     * This method calculates the location area.
     * The area is calculated as the sum of the areas of all objects in the children list.
     * @return The method returns the total value of the area in the location.
     */
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

    /**
     * This method calculates the location cube.
     * The cube is calculated as the sum of the cubes of all objects in the children list.
     * @return The method returns the total value of the cube in the location.
     */
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

    /**
     * This method calculates the location heating.
     * The heating is calculated as the sum of the heating of all objects in the children list.
     * @return The method returns the total value of the cube in the location.
     */
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

    /**
     * This method calculates the location light.
     * The light is calculated as the sum of the lights of all objects in the children list.
     * @return The method returns the total value of the light in the location.
     */
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

    /**
     * This method calculates the total energy consumption for heating per cube.
     * The energy for heating per cube is calculated as the average of the energy for heating per cube of all objects
     * in the children list.
     * @return The method returns the total energy per cube needed to heat the location.
     */
    public float getHeatingPerCube() {
        if(children.size() == 0) {
            return 0;
        }

        float hpc = 0.0f;
        for(Location child : children) {
            hpc += child.getHeatingPerCube();
        }
        hpc /= children.size();
        return hpc;
    }

    /**
     * This method calculates the lighting power per square for location.
     * Lighting power per sqaure is calculated as the average of the energy for heating per cube of all objects
     * in the children list.
     * @return This method returns the total lighting power per square for location.
     */
    public float getLightPerSquare() {
        if(children.size() == 0) {
            return 0;
        }

        float lps = 0.0f;
        for(Location child:children) {
            lps += child.getLightPerSquare();
        }
        lps /= children.size();
        return lps;
    }
}
