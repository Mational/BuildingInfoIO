package pl.put.poznan.buildinginfo.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuildingInfoControllerTest {

    private BuildingInfoController buildingInfoController;

    @BeforeEach
    void setUp() {
        buildingInfoController = new BuildingInfoController();
    }

    @Test
    void testGoodJsonFormat() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("Json has been loaded to composite.", buildingInfoController.createBuilding(json),
                "Adding good json format doesn't work properly.");
    }

    @Test
    void testInvalidJsonFormat() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building,
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1,
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("You gave invalid Json format.", buildingInfoController.createBuilding(json),
                "Adding wrong json format doesn't work properly.");
    }

    @Test
    void testNotAllParameters() {
        String json = """
                [
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("You didn't give all of the parameters in json file", buildingInfoController.createBuilding(json),
                "Checking of all parameters doesn't work properly.");
    }

    @Test
    void testConstraints() {
        String json = """
                [
                    {"lowLight": -1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": -14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("At least one parameter is negative which is not allowed", buildingInfoController.createBuilding(json),
                "Checking non-positive parameters doesn't work properly.");
    }
    @Test
    void testNonPositiveArea() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": -14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("One of room parameters is non positive.", buildingInfoController.createBuilding(json),
                "Area non-positive check doesn't work properly.");
    }

    @Test
    void testNonPositiveCube() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": -28.4,
                						"heating": 13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("One of room parameters is non positive.", buildingInfoController.createBuilding(json),
                "Cube non-positive check doesn't work properly.");
    }
    @Test
    void testNonPositiveHeating() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": 28.4,
                						"heating": -13.2,
                						"light": 15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("One of room parameters is non positive.", buildingInfoController.createBuilding(json),
                "Heating non-positive check doesn't work properly.");
    }

    @Test
    void testNonPositiveLight() {
        String json = """
                [
                    {"lowLight": 1.056},
                    {"highHeat": 0.4671},
                	{
                		"id": "1",
                		"name": "building",
                		"levels": [{
                				"id": "1.1",
                				"name": "floor1",
                				"rooms": [{
                						"id": "1.1.1",
                						"name": "room101",
                						"area": 14.2,
                						"cube": 28.4,
                						"heating": 13.2,
                						"light": -15
                					},
                					{
                						"id": "1.1.2",
                						"name": "room102",
                						"area": 15.2,
                						"cube": 30.4,
                						"heating": 14.2,
                						"light": 16
                					}
                				]
                			},
                			{
                				"id": "1.2",
                				"name": "floor2",
                				"rooms": [{
                						"id": "1.2.1",
                						"name": "room201",
                						"area": 14.5,
                						"cube": 29,
                						"heating": 13.6,
                						"light": 15.3
                					},
                					{
                						"id": "1.2.2",
                						"name": "room202",
                						"area": 12.2,
                						"cube": 24.4,
                						"heating": 10.2,
                						"light": 13
                					}
                				]
                			}
                		]
                	}
                ]
                """;
        assertEquals("One of room parameters is non positive.", buildingInfoController.createBuilding(json),
                "Light non-positive check doesn't work properly.");
    }
}