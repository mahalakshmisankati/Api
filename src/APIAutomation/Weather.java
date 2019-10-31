package APIAutomation;


	import static io.restassured.RestAssured.given;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	public class Weather {

		Properties prop;
		Logger l=Logger.getLogger("Weather");

		@Test
		public void weatherTweet() throws IOException
		{
			prop = new Properties();
			PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
			FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
			prop.load(fis);
			
			RestAssured.baseURI = "https://api.twitter.com/1.1/search";
			Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
			param("q","weather,Bangalore").
			when().
			get("/tweets.json").
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
			extract().response();
			
			String response = res.asString();
			
			
			JsonPath js = new JsonPath(response);
			int count = js.get("statuses.size()");
			
			
			String resp;
			for(int i=0;i<count;i++)
			{
				String place = js.get("statuses["+i+"].user.location").toString();
				if(place.contains("Bengaluru")||place.contains("Bangalore"))
				{
					resp = js.get("statuses["+i+"]").toString();
					l.info(resp);
				}
			}
		}
	}


