package pl.put.poznan.buildinginfo.rest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.put.poznan.buildinginfo.logic.composite.owner.BuildingManager;

@RestController
@RequestMapping("/BuildingInfoService")
public class BuildingInfoController {

    private BuildingManager buildingManager;
    @RequestMapping(value="", method=RequestMethod.POST)
    public String createBuilding(@RequestBody String json) throws JsonProcessingException {
        if(buildingManager != null) {
            System.out.println("Composite is full, space will be cleared.\n");
            buildingManager = null;
        }

        try {
            buildingManager = new BuildingManager();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            buildingManager.createBuilding(jsonNode);
            return "Json has been loaded to composite.";
        }catch(JsonParseException jpe) {
            return "You gave invalid Json format.";
        }
    }
    @RequestMapping(value="", method=RequestMethod.GET)
    public String getBuildingStructure() {
        try{
            return buildingManager.getBuildingStructureCont();
        }catch(NullPointerException e) {
            return "You don't create a building yet.";
        }
    }

    @RequestMapping(value="/{option}", method=RequestMethod.GET)
    public String getBuildingInfo(@PathVariable String option) throws JsonProcessingException {
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

    @RequestMapping(value="/floors/{option}", method=RequestMethod.GET)
    public String getFloorsInfo(@PathVariable String option) throws JsonProcessingException {
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

    @RequestMapping(value="/floors/{floor_name}/{option}", method=RequestMethod.GET)
    public String getFloorInfo(@PathVariable("floor_name") String floor_name, @PathVariable("option") String option) throws JsonProcessingException {
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

    @RequestMapping(value="/rooms/{option}", method=RequestMethod.GET)
    public String getRoomsInfo(@PathVariable("option") String option) throws JsonProcessingException {
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

    @RequestMapping(value="/rooms/{room_name}/{option}", method=RequestMethod.GET)
    public String getRoomInfo(@PathVariable("room_name") String room_name,
                                @PathVariable("option") String option) throws JsonProcessingException {
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
}


