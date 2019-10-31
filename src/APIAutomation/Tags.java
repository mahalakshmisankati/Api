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

public class Tags {

	Properties prop;
	Logger l=Logger.getLogger("Tags");

	@Test
	public void DisplayHashtag() throws IOException
	{

		prop = new Properties();
		PropertyConfigurator.configure("C:\\ag\\APIAutomation\\src\\Files\\log4j.properties");
		FileInputStream fis = new FileInputStream("C:\\\\ag\\\\APIAutomation\\\\src\\\\data.properties");
		prop.load(fis);
		
		RestAssured.baseURI="https://api.twitter.com/1.1/trends";
		Response res=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).	
		when().
		get("/available.json").then().assertThat().statusCode(200).contentType(ContentType.JSON).
		extract().response();
		
		String response=res.asString();
		
		JsonPath js=new JsonPath(response);
		int count=js.get("size()");
		for(int i=0;i<count;i++)
		{
			String country=js.get("["+i+"].country").toString();
			if(country.equalsIgnoreCase(prop.getProperty("location1")))
			{
				String id=js.get("["+i+"].parentid").toString();
				Response res1=given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
						param("id",id).
						when().
						get("/place.json").then().assertThat().statusCode(200).contentType(ContentType.JSON).
						extract().response();
						
						String response1=res1.asString();
						
						JsonPath js1=new JsonPath(response1);
						int count1=js1.get("["+0+"].trends.size()");
						for(int j=0;j<count1;j++)
						{
							String Hashtag = js1.getString("["+0+"].trends["+j+"].name");
							String actual = js1.getString("["+0+"].trends["+j+"]");
							if(Hashtag.charAt(0)=='#' && j<=5)
							{
								l.info(actual);
							}
						}
						break;
			}
		}
	}
}