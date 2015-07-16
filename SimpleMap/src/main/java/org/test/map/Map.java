package org.test.map;

import javax.ws.rs.*;


@Path("/")
public class Map {
	
	float x;
	float y;

	@GET
	@Path("/getlocation/")
	@Produces("application/json")
	public String getlocation() {
		return "{\"location\":\"(" + x + "," + y + ")\"}";
	}
	
	@POST
	@Path("/setlocation/")
	@Produces("application/json")
	public String setlocation(String location) {
		int startIndex = location.indexOf("(");
		int endIndex = location.indexOf(")");
		String xy = location.substring(startIndex + 1, endIndex - 1);
		String[] xyArray = xy.split(",");
		x = Float.valueOf(xyArray[0]);
		y = Float.valueOf(xyArray[1]);
		return "{\"setlocation\":\"true\"}";
	}

}
