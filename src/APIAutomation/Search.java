package APIAutomation;


	import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

	import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

	import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

	public class Search {
		Properties prop;
		Logger l=Logger.getLogger("Search");

		

			@Test
			public void searchTweet() throws IOException {
				prop = new Properties();
				PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
				FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
				prop.load(fis);
			
				RestAssured.baseURI="https://api.twitter.com/1.1/search/";
			
					Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
			 queryParam("q","Qualitest")
			 .when().get("/tweets.json").then().extract().response();
				String response=res.asString();
				l.info(response);
				
							
	}
            @Test
			
			public void tweet() throws IOException {
	prop = new Properties();
	FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
	prop.load(fis);

				
				
				RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
			
					Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
			 
			 when().get("home_timeline.json").then().extract().response();
				String str=res.asString();
				l.info(str);
				JsonPath js=new JsonPath(str);
				String text=js.get("text").toString();
				l.info(text);
				}
	}


