import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private static final String BASE_URI = "https://gorest.co.in/public/v2";
    private static final String TOKEN = "e8dbbfbe9401e875adb46a50cce9ce234fe59a8054c0eb588153c58a1c04635a";

    @BeforeSuite
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder().build()
                .baseUri(BASE_URI)
                .header("Authorization", "Bearer " + TOKEN)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);
    }
}
