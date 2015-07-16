package org.test.map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/")
public class WeatherInfo {

	
	@GET
	@Path("/gettemperature/")
	@Produces("application/json")
	public double gettemperature(@QueryParam("city") String city) {
		
		if ("Colombo".equalsIgnoreCase(city)) {
			return 26.25;
		}
		if ("Kandy".equalsIgnoreCase(city)) {
			return 24.5;
		}
		if ("Galle".equalsIgnoreCase(city)) {
			return 25.00;
		}
		if ("Jafna".equalsIgnoreCase(city)) {
			return 27.25;
		}
		else {
			return -200;
		}
 
	}

}