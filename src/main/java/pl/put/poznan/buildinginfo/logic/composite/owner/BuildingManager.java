package pl.put.poznan.buildinginfo.logic.composite.owner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import pl.put.poznan.buildinginfo.logic.composite.locations.Room;
import pl.put.poznan.buildinginfo.logic.composite.locations.CompoundLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is necessary for managing building.
 * This class is supposed to create the building structure from converted json.
 * This class can also return any information about building such as their structure, floorInfo, roomInfo etc.
 * @author Mational, katrecat, figi14
 * @version 1.0
 */
public class BuildingManager {
    private JsonNode result;
    private List<CompoundLocation> floors;
    private CompoundLocation building;
    private List<Room> rooms;
    public BuildingManager() {
        this.result = null;
        this.floors = null;
        this.building = null;
        this.rooms = null;
    }

    /**
     * This method is used for create building structure from JsonNode object
     * This method returns nothing.
     * @param jsonNode It is input parameter in JsonNode object form. It's just a mapped json with help of Jackson library
     */
    public void createBuilding(JsonNode jsonNode) {
        building = new CompoundLocation(
                String.valueOf(jsonNode.get("id")),
                String.valueOf(jsonNode.get("name"))
        );
        ArrayNode floorsArray = (ArrayNode)jsonNode.get("levels");

        floors = new ArrayList<>();
        rooms = new ArrayList<>();
        for(int i=0;i<floorsArray.size();i++) {
            floors.add(new CompoundLocation(
                    String.valueOf(floorsArray.get(i).get("id")),
                    String.valueOf(floorsArray.get(i).get("name"))
            ));

            ArrayNode roomsArray = (ArrayNode)floorsArray.get(i).get("rooms");

            for(int j=0;j<roomsArray.size();j++) {
                rooms.add(new Room(
                        String.valueOf(roomsArray.get(j).get("id")),
                        String.valueOf(roomsArray.get(j).get("name")),
                        Float.parseFloat(String.valueOf(roomsArray.get(j).get("area"))),
                        Float.parseFloat(String.valueOf(roomsArray.get(j).get("cube"))),
                        Float.parseFloat(String.valueOf(roomsArray.get(j).get("heating"))),
                        Float.parseFloat(String.valueOf(roomsArray.get(j).get("light")))
                ));
                floors.get(floors.size()-1).add(rooms.get(rooms.size()-1));
            }
            building.add(floors.get(floors.size()-1));
        }
        result = jsonNode;
    }

    public String getBuildingStructure() {
        return result.toPrettyString();
    }

    public String getBuildingInfo(String option) throws JsonProcessingException {
        String jsonInString = "{\"id\":" + building.id + ","
                + "\"name\":" + building.name + ",";
        switch(option) {
            case "all":
                jsonInString += "\"area\":" + building.getArea() + ",";
                jsonInString += "\"cube\":" + building.getCube() + ",";
                jsonInString += "\"heating\":" + building.getHeating() + ",";
                jsonInString += "\"light\":" + building.getLight() + ",";
                jsonInString += "\"lightPerSquare\":" + building.getLightPerSquare() + ",";
                jsonInString += "\"heatingPerCube\":" + building.getHeatingPerCube() + "}";
                break;
            case "area": jsonInString += "\"area\":" + building.getArea() + "}";
                break;
            case "cube":
                jsonInString += "\"cube\":" + building.getCube() + "}";
                break;
            case "heating":
                jsonInString += "\"heating\":" + building.getHeating() + "}";
                break;
            case "light":
                jsonInString += "\"light\":" + building.getLight() + "}";
                break;
            case "lps":
                jsonInString += "\"lightPerSquare\":" + building.getLightPerSquare() + "}";
                break;
            case "hpc":
                jsonInString += "\"heatingPerCube\":" + building.getHeatingPerCube() + "}";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return jsonInString;
    }

    public String getFloorsInfo(String option) throws JsonProcessingException {
        String jsonInString = "[";
        for(CompoundLocation floor:floors) {
            jsonInString += "{\"id\":" + floor.id + ",";
            jsonInString += "\"name\":" + floor.name + ",";
            switch (option) {
                case "all":
                    jsonInString += "\"area\":" + floor.getArea() + ",";
                    jsonInString += "\"cube\":" + floor.getCube() + ",";
                    jsonInString += "\"heating\":" + floor.getHeating() + ",";
                    jsonInString += "\"light\":" + floor.getLight() + ",";
                    jsonInString += "\"lightPerSquare\":" + floor.getLightPerSquare() + ",";
                    jsonInString += "\"heatingPerCube\":" + floor.getHeatingPerCube() + "}";
                    break;
                case "area": jsonInString += "\"area\":" + floor.getArea() + "}";
                    break;
                case "cube":
                    jsonInString += "\"cube\":" + floor.getCube() + "}";
                    break;
                case "heating":
                    jsonInString += "\"heating\":" + floor.getHeating() + "}";
                    break;
                case "light":
                    jsonInString += "\"light\":" + floor.getLight() + "}";
                    break;
                case "lps":
                    jsonInString += "\"lightPerSquare\":" + floor.getLightPerSquare() + "}";
                    break;
                case "hpc":
                    jsonInString += "\"heatingPerCube\":" + floor.getHeatingPerCube() + "}";
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if(!Objects.equals(floor.id, floors.get(floors.size() - 1).id)) {
                jsonInString += ",";
            }
        }
        jsonInString += "]";
        return jsonInString;
    }

    public String getFloorInfo(String floor_name, String option) throws JsonProcessingException {
        floor_name = "\"" + floor_name + "\"";
        for(int floor=0; floor<floors.size();floor++) {
            if (Objects.equals(floors.get(floor).name, floor_name)) {
                String jsonInString = "{\"id\":" + floors.get(floor).id + ",";
                jsonInString += "\"name\":" + floors.get(floor).name + ",";
                switch (option) {
                    case "all":
                        jsonInString += "\"area\":" + floors.get(floor).getArea() + ",";
                        jsonInString += "\"cube\":" + floors.get(floor).getCube() + ",";
                        jsonInString += "\"heating\":" + floors.get(floor).getHeating() + ",";
                        jsonInString += "\"light\":" + floors.get(floor).getLight() + ",";
                        jsonInString += "\"lightPerSquare\":" + floors.get(floor).getLightPerSquare() + ",";
                        jsonInString += "\"heatingPerCube\":" + floors.get(floor).getHeatingPerCube() + "}";
                        break;
                    case "area": jsonInString += "\"area\":" + floors.get(floor).getArea() + "}";
                        break;
                    case "cube":
                        jsonInString += "\"cube\":" + floors.get(floor).getCube() + "}";
                        break;
                    case "heating":
                        jsonInString += "\"heating\":" + floors.get(floor).getHeating() + "}";
                        break;
                    case "light":
                        jsonInString += "\"light\":" + floors.get(floor).getLight() + "}";
                        break;
                    case "llps":
                        jsonInString += "\"lightPerSquare\":" + floors.get(floor).getLightPerSquare() + "}";
                        break;
                    case "hpc":
                        jsonInString += "\"heatingPerCube\":" + floors.get(floor).getHeatingPerCube() + "}";
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                return jsonInString;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getRoomsInfo(String option) throws JsonProcessingException {
        String jsonInString = "[";
        for(Room room:rooms) {
            jsonInString += "{\"id\":" + room.id + ",";
            jsonInString += "\"name\":" + room.name + ",";
            switch (option) {
                case "all":
                    jsonInString += "\"area\":" + room.getArea() + ",";
                    jsonInString += "\"cube\":" + room.getCube() + ",";
                    jsonInString += "\"heating\":" + room.getHeating() + ",";
                    jsonInString += "\"light\":" + room.getLight() + ",";
                    jsonInString += "\"lightPerSquare\":" + room.getLightPerSquare() + ",";
                    jsonInString += "\"heatingPerCube\":" + room.getHeatingPerCube() + "}";
                    break;
                case "area": jsonInString += "\"area\":" + room.getArea() + "}";
                    break;
                case "cube":
                    jsonInString += "\"cube\":" + room.getCube() + "}";
                    break;
                case "heating":
                    jsonInString += "\"heating\":" + room.getHeating() + "}";
                    break;
                case "light":
                    jsonInString += "\"light\":" + room.getLight() + "}";
                    break;
                case "lps":
                    jsonInString += "\"lightPerSquare\":" + room.getLightPerSquare() + "}";
                    break;
                case "hpc":
                    jsonInString += "\"heatingPerCube\":" + room.getHeatingPerCube() + "}";
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if(!Objects.equals(room.id, rooms.get(rooms.size() - 1).id)) {
                jsonInString += ",";
            }
        }

        jsonInString += "]";
        System.out.println(jsonInString);
        return  jsonInString;
    }

    public String getRoomInfo(String room_name, String option) throws JsonProcessingException {
        room_name = "\"" + room_name + "\"";
        for(int room=0; room<rooms.size();room++) {
            if (Objects.equals(rooms.get(room).name, room_name)) {
                String jsonInString = "{\"id\":" + rooms.get(room).id + ",";
                jsonInString += "\"name\":" + rooms.get(room).name + ",";
                switch (option) {
                    case "all":
                        jsonInString += "\"area\":" + rooms.get(room).getArea() + ",";
                        jsonInString += "\"cube\":" + rooms.get(room).getCube() + ",";
                        jsonInString += "\"heating\":" + rooms.get(room).getHeating() + ",";
                        jsonInString += "\"light\":" + rooms.get(room).getLight() + ",";
                        jsonInString += "\"lightPerSquare\":" + rooms.get(room).getLightPerSquare() + ",";
                        jsonInString += "\"heatingPerCube\":" + rooms.get(room).getHeatingPerCube() + "}";
                        break;
                    case "area": jsonInString += "\"area\":" + rooms.get(room).getArea() + "}";
                        break;
                    case "cube":
                        jsonInString += "\"cube\":" + rooms.get(room).getCube() + "}";
                        break;
                    case "heating":
                        jsonInString += "\"heating\":" + rooms.get(room).getHeating() + "}";
                        break;
                    case "light":
                        jsonInString += "\"light\":" + rooms.get(room).getLight() + "}";
                        break;
                    case "lps":
                        jsonInString += "\"lightPerSquare\":" + rooms.get(room).getLightPerSquare() + "}";
                        break;
                    case "hpc":
                        jsonInString += "\"heatingPerCube\":" + rooms.get(room).getHeatingPerCube() + "}";
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                return jsonInString;
            }
        }
        throw new IllegalArgumentException();
    }
}
