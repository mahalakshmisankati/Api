package APIAutomation;


	
	import org.testng.annotations.Test;

	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.given;

	public class Retweet {
		int a[]= {1,2295383,28218,23424977,23424852};
		String ConsumerKey="2Xtd4ILUtinfACHehAvCNcbjW";
		String ConsumerSecret="pIHDmJy9fgKlLVuAgG4AuP5rRkaOy6TtWqxLqjVeJ2MywdcGJp";
		String Token="839104214-h1Kaf8iJW2FAyXzNCZNicI1a4Xb8BMw7ZkFKGzmJ";
		String TokenSecret="PTvXawkmrK3EZWeLSSmqtD7hisogRC3nqG48FYzNcLfQy";
			
		@Test
			
			public void tweet() {
				
				
				RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
			Response res= given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
			 //queryParam("id",a[i])
			 when().get("home_timeline.json").then().extract().response();
				String str=res.asString();
				System.out.println(str);
				JsonPath js=new JsonPath(str);
				String text=js.get("text").toString();
				System.out.println(text);
				}
		}

