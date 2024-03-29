package APIAutomation;


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class List {

	Properties prop;
	Logger l=Logger.getLogger("List");

	@Test
	public void searchTweet() throws IOException
	{
	
		Payload p = new Payload();
		
		prop = new Properties();
		PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
		FileInputStream fis = new FileInputStream("C:\\ag\\APIAutomation\\src\\data.properties");
		prop.load(fis);
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		param("screen_name",p.getUsers()).
		when().
		get("/user_timeline.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		l.info(p.getUsers());
		String response = res.asString();
		l.info(response);
		
	
}
	}


