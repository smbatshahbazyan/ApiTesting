import org.example.response.UserData;
import org.hamcrest.Matchers;
import org.hamcrest.core.Every;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.when;

public class GetUsersTest extends BaseTest {
    @Test(groups = "getUserTest")
    public void getListTest() {
        List<UserData> userDataList = when()
                .get("users")
                .then()
                .log()
                .all().statusCode(200)
                .extract().body().jsonPath().getList("$", UserData.class);
        System.out.println(userDataList);
    }

    @Test(groups = "emailTest")
    public void getEmailTest() {
        when()
                .get("users")
                .then()
                .log()
                .all().statusCode(200)
                .body("email", Every.everyItem(Matchers.matchesRegex(".*@.*")));
    }
}
