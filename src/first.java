import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class first {

	@Test
	public void APITest() {
		RestAssured.baseURI="https://reqres.in";
	 ((RestAssured) given()). 
		      
		    when().
		    get("api/users").
		    then().assertThat().
		    statusCode(200).and().contentType(ContentType.JSON).and().
		    body("data[0].first_name",equalTo("Eve1")).and().
	 body("data[0].avatar",equalTo( "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg")).and().
	 body("page",equalTo(2)).and().header("Server","cloudflare");
	 
	 
	 
		
		

	}

	private static Object given() {
		// TODO Auto-generated method stub
		return null;
	}

}
