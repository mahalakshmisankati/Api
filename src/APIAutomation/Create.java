
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

	public class Create {
		Properties prop;
		Logger l=Logger.getLogger("Create");
		
	
		@Test
		
		public void getTweet()throws IOException{
			prop = new Properties();
			PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
			FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
			prop.load(fis);
			
			RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
		
			Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		 queryParam("count","1")
		 .when().get("home_timeline.json").then().extract().response();
			String response=res.asString();
			 l.info(response);
			System.out.println(response);
			JsonPath js=new JsonPath(response);
			String id=js.get("id").toString();
			l.info(id);
			System.out.println(id);
			String text=js.get("text").toString();
			 l.info(text);
			System.out.println(text);
			
		
		}	
	}

