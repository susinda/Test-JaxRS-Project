package org.test.map;

import javax.ws.rs.*;

@Path("/")
public class SimpleMap {

	
	@GET
	@Path("/getcity/")
	@Produces("application/json")
	public String getcity(@QueryParam("x") int x, @QueryParam("y") int y) {
		
		if (x==6 && y==79) {
			return "{\"city\":\"Colombo\"}";
		}
		else if (x==7 && y==80) {
			return "{\"city\":\"Kandy\"}";
		}
		else if (x==6 && y==81) {
			return "{\"city\":\"Galle\"}";
		}
		else if (x==9 && y==80) {
			return "{\"city\":\"Jaffna\"}";
		}
		else {
			return "{\"city\":\"Not found\"}";
		}
	}

}