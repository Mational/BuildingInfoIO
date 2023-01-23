package pl.put.poznan.buildinginfo.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.put.poznan.buildinginfo.logic.composite.owner.BuildingManager;

/**
 * This class serves as the application's rest controller.
 * It intercepts the relevant http requests and calls the corresponding methods.
 */
@RestController
@RequestMapping("/BuildingInfoService")
public class BuildingInfoController {

    private BuildingManager buildingManager;
    private float lightParameter;
    private float heatParameter;

    /**
     * This method responds to a POST request. Creates a building composite using a BuildingManager object.
     * @param json This is a building structure in json format.
     * @return This method returns a message depending on the composite build operation.
     */
    @RequestMapping(value="", method=RequestMethod.POST)
    public String createBuilding(@RequestBody String json) {
        if(buildingManager != null) {
            System.out.println("Composite is full, space will be cleared.\n");
            buildingManager = null;
        }

        try {
            buildingManager = new BuildingManager();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            lightParameter = jsonNode.get(0).get("lowLight").floatValue();
            heatParameter = jsonNode.get(1).get("highHeat").floatValue();

            if(lightParameter < 0 || heatParameter < 0) {
                return "At least one parameter is negative which is not allowed";
            }

            System.out.println("Light: "+lightParameter);
            System.out.println("High: "+heatParameter);

            //Dodać nazwy dla parametrów w jsonie i zczytac je do wartosci w BuildingInfoController
            //System.out.println("Lightning level: "+jsonNode.get(0).get);
            JsonNode jsonP2 = jsonNode.get(1);
            System.out.println("test: "+jsonNode.elements());
            buildingManager.createBuilding(jsonNode.get(2));
            return "Json has been loaded to composite.";
        }catch(JsonParseException jpe) {
            return "You gave invalid Json format.";
        }catch(NullPointerException npe) {
            return "You didn't give all of the parameters in json file";
        }catch(JsonProcessingException jpe) {
            return "Core dump!";
        }catch(NumberFormatException nfe) {
            return "One of room parameters is non positive.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns the structure of the building in json format.
     * @return json with the building structure, or an operation failure message.
     */
    @RequestMapping(value="", method=RequestMethod.GET)
    public String getBuildingStructure() {
        try{
            return buildingManager.getBuildingStructure();
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns relevant information about the building.
     * @param option This parameter defines what information about the building is to be retrieved and returned.
     *               This parameter takes the following values: all, area, cube, heating, light, lps, hpc.
     * @return This method returns information about the building in the form of a string
     *         whose structure corresponds to the json format if the operation is successful.
     *         If the operation fails, the method returns an appropriate message.
     */
    @RequestMapping(value="/{option}", method=RequestMethod.GET)
    public String getBuildingInfo(@PathVariable String option) {
        try {
            return buildingManager.getBuildingInfo(option);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(IllegalArgumentException e) {
            return "Incorrect argument was sended.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns relevant information about all floors in building.
     * @param option This parameter defines what information about the floors is to be retrieved and returned.
     *               This parameter takes the following values: all, area, cube, heating, light, lps, hpc.
     * @return This method returns information about all floors in building in the form of a string
     *         whose structure corresponds to the json format if the operation is successful.
     *         If the operation fails, the method returns an appropriate message.
     */
    @RequestMapping(value="/floors/{option}", method=RequestMethod.GET)
    public String getFloorsInfo(@PathVariable String option) {
        try {
            return buildingManager.getFloorsInfo(option);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(IllegalArgumentException e) {
            return "Incorrect argument was sended.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns relevant information about specific floor in building.
     * @param floor_name This parameter defines which floor we want to obtain information about.
     *                   This parameter takes the floor name.
     * @param option This parameter defines what information about the floor is to be retrieved and returned.
     *               This parameter takes the following values: all, area, cube, heating, light, lps, hpc.
     * @return This method returns information about specific floor in building in the form of a string
     *         whose structure corresponds to the json format if the operation is successful.
     *         If the operation fails, the method returns an appropriate message.
     */
    @RequestMapping(value="/floors/{floor_name}/{option}", method=RequestMethod.GET)
    public String getFloorInfo(@PathVariable("floor_name") String floor_name, @PathVariable("option") String option) {
        try {
            return buildingManager.getFloorInfo(floor_name, option);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(IllegalArgumentException e) {
            return "Incorrect argument was sended.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns relevant information about all rooms in building.
     * @param option This parameter defines what information about the rooms is to be retrieved and returned.
     *               This parameter takes the following values: all, area, cube, heating, light, lps, hpc.
     * @return This method returns information about all rooms in building in the form of a string
     *         whose structure corresponds to the json format if the operation is successful.
     *         If the operation fails, the method returns an appropriate message.
     */
    @RequestMapping(value="/rooms/{option}", method=RequestMethod.GET)
    public String getRoomsInfo(@PathVariable("option") String option) {
        try {
            return buildingManager.getRoomsInfo(option);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(IllegalArgumentException e) {
            return "Incorrect argument was sended.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }

    /**
     * This method responds to a GET request. Using an object of the BuildingManager class,
     * it returns relevant information about specific room in building.
     * @param room_name This parameter defines which room we want to obtain information about.
     *                  This parameter takes the room name.
     * @param option This parameter defines what information about the room is to be retrieved and returned.
     *               This parameter takes the following values: all, area, cube, heating, light, lps, hpc.
     * @return This method returns information about specific room in building in the form of a string
     *         whose structure corresponds to the json format if the operation is successful.
     *         If the operation fails, the method returns an appropriate message.
     */
    @RequestMapping(value="/rooms/{room_name}/{option}", method=RequestMethod.GET)
    public String getRoomInfo(@PathVariable("room_name") String room_name,
                                @PathVariable("option") String option) {
        try {
            return buildingManager.getRoomInfo(room_name, option);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(IllegalArgumentException e) {
            return "Incorrect argument was sended.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }

    @RequestMapping(value="/rooms/lightLimit", method=RequestMethod.GET)
    public String getRoomsUnderLitghtLimit() {
        try {
            return buildingManager.getRoomsUnderLightLimit(lightParameter);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }
    
    @RequestMapping(value="/rooms/heatLimit", method=RequestMethod.GET)
    public String getRoomsOverHeatLimit() {
        try {
            return buildingManager.getRoomsOverHeatLimit(heatParameter);
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }catch(HttpClientErrorException.BadRequest e) {
            return "Bad Request.";
        }
    }
}


