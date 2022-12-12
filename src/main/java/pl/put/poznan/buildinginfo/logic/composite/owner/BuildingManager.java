package pl.put.poznan.buildinginfo.logic.composite.owner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pl.put.poznan.buildinginfo.logic.composite.locations.Room;
import pl.put.poznan.buildinginfo.logic.composite.locations.CompoundLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String getBuildingStructureCont() {
        return result.toPrettyString();
    }

    public String getBuildingInfo(String option) throws JsonProcessingException {
        String jsonInString = "{\"id\":" + building.id + ","
                + "\"name\":" + building.name + ",";
        switch (option) {
            case "all":
                jsonInString += "\"area\":" + building.getArea() + ",";
                jsonInString += "\"cube\":" + building.getCube() + ",";
                jsonInString += "\"heating\":" + building.getHeating() + ",";
                jsonInString += "\"light\":" + building.getLight() + "}";
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
            default:
                throw new IllegalArgumentException();
        }
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(jsonInString);
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
                    jsonInString += "\"light\":" + floor.getLight() + "}";
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
                default:
                    throw new IllegalArgumentException();
            }
            if(!Objects.equals(floor.id, floors.get(floors.size() - 1).id)) {
                jsonInString += ",";
            }
        }
        jsonInString += "]";
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(jsonInString);
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
                        jsonInString += "\"light\":" + floors.get(floor).getLight() + "}";
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
                    default:
                        throw new IllegalArgumentException();
                }
                return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(jsonInString);
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
                    jsonInString += "\"light\":" + room.getLight() + "}";
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
                default:
                    throw new IllegalArgumentException();
            }
            if(!Objects.equals(room.id, rooms.get(rooms.size() - 1).id)) {
                jsonInString += ",";
            }
        }
        jsonInString += "]";
        return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(jsonInString);
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
                        jsonInString += "\"light\":" + rooms.get(room).getLight() + "}";
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
                    default:
                        throw new IllegalArgumentException();
                }
                return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(jsonInString);
            }
        }
        throw new IllegalArgumentException();
    }

    public CompoundLocation getBuilding() {
        System.out.println(building.getId());
        System.out.println(building.getName());
        System.out.println(building.getArea());
        System.out.println(building.getCube());
        System.out.println(building.getHeating());
        System.out.println(building.getLight());
        System.out.println(building.getHeatingPerCube());
        System.out.println(building.getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(floors.get(0).getId());
        System.out.println(floors.get(0).getName());
        System.out.println(floors.get(0).getArea());
        System.out.println(floors.get(0).getCube());
        System.out.println(floors.get(0).getHeating());
        System.out.println(floors.get(0).getLight());
        System.out.println(floors.get(0).getHeatingPerCube());
        System.out.println(floors.get(0).getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(floors.get(1).getId());
        System.out.println(floors.get(1).getName());
        System.out.println(floors.get(1).getArea());
        System.out.println(floors.get(1).getCube());
        System.out.println(floors.get(1).getHeating());
        System.out.println(floors.get(1).getLight());
        System.out.println(floors.get(1).getHeatingPerCube());
        System.out.println(floors.get(1).getLightPerSquare());
        System.out.println("\n\n");

        System.out.println(rooms.get(0).getId());
        System.out.println(rooms.get(0).getName());
        System.out.println(rooms.get(0).getArea());
        System.out.println(rooms.get(0).getCube());
        System.out.println(rooms.get(0).getHeating());
        System.out.println(rooms.get(0).getLight());
        return building;
    }

    public static void main (String[] args)
    {
        Room room1 = new Room("1.1.1", "room101", 14.2f, 28.4f, 13.2f, 15.0f);
        Room room2 = new Room("1.1.2", "room102", 15.2f, 20.4f, 14.2f, 16.0f);
        Room room3 = new Room("1.2.1", "room201", 12.2f, 24.4f, 10.2f, 15.3f);
        Room room4 = new Room("1.2.2", "room202", 14.7f, 29.0f, 13.6f, 13.0f);

        CompoundLocation level1 = new CompoundLocation("1.1", "floor1");
        CompoundLocation level2 = new CompoundLocation("1.2", "floor2");

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
