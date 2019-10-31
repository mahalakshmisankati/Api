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

	public class Post {
		Properties prop;
		Logger l=Logger.getLogger("Post");
			

			@Test
			
			public void creatTweet()throws IOException {
				prop = new Properties();
				PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
				FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
				prop.load(fis);
			
				RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
			
				Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
			
			 queryParam("status","I am learning API testing using RestAssured Java Qualitest")
			 .when().post("/update.json").then().extract().response();
				String response=res.asString();
				l.info(response);
				JsonPath js=new JsonPath(response);
				String id=js.get("id").toString();
				l.info(id);
				String text=js.get("text").toString();
				l.info(text);
		
			}
		
	}


