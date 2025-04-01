package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class CheckWeather {

	
	@Test
	public void CheckWeatherMethod() {
			RestAssured.useRelaxedHTTPSValidation();
			Response res = RestAssured.get("https://api.openweathermap.org/data/2.5/weather?lat=12.9716&lon=77.5946&appid=a9479cd0725dd82210ae7a181a6527c4");
			ResponseBody resbody = res.body();
					
			int code = res.getStatusCode();
			Assert.assertEquals(code, 200);
			
			//Validate server
			String sername = res.getHeader("Server");
			Assert.assertEquals(sername, "openresty");
			
			//validate statusline
			String stausline = res.getStatusLine();
			Assert.assertEquals(stausline, "HTTP/1.1 200 OK");
			
			//validate which bangaluru city wather captured
			String cityname = res.getBody().path("name");
			Assert.assertEquals(cityname, "Bengaluru");
			
			//validate cointry is india for bangaluru
			String countrycode = res.getBody().path("sys.country");
			Assert.assertEquals(countrycode, "IN");
			
			//validate cod code for bangaluru
			int weathercod = res.getBody().path("cod");
			Assert.assertEquals(weathercod, 200);
			
			System.out.println("Assert for statucode is successfull");
			
	}
}
