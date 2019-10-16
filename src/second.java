import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class second {

	@Test
	public void APITest() {
		RestAssured.baseURI="https://reqres.in";
	 given();
	RestAssured. when().
	    post("api/users").
	    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and();
	    
	
	
	
	
	
	
	}

	private RestAssured given() {
		// TODO Auto-generated method stub
		return null;
	}

}
