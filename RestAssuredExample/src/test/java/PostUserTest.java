import org.example.request.UserRequest;
import org.example.response.UserData;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostUserTest extends BaseTest {
    @Test(groups= "userCreatingTest")
    public void CreateUserTest() {
        UserRequest userRequest = new UserRequest("Test99", "testtest99@gorest.in", "male", "active");
        UserData userData = given()
                .body(userRequest)
                .post("users")
                .then()
                .log()
                .all()
                .statusCode(201)
                .body("id", Matchers.isA(Integer.class))
                .extract().as(UserData.class);
        SoftAssert softAssert = new SoftAssert();
        Assert.assertEquals(userData.getName(), userRequest.getName());
        Assert.assertEquals(userData.getEmail(), userRequest.getEmail());
        Assert.assertEquals(userData.getGender(), userRequest.getGender());
        Assert.assertEquals(userData.getStatus(), userRequest.getStatus());
        softAssert.assertAll();
    }

    @Test(groups= "userCreatingTest")
    public void createUserTest1() {
        UserRequest userRequest = new UserRequest("Test55", "55testtest@gorest.in", "male", "active");
        given()
                .body(userRequest)
                .post("users")
                .then()
                .log()
                .all()
                .statusCode(201)
                .assertThat().body(matchesJsonSchemaInClasspath("user_validation_json_schema.json"));
    }

    @Test(groups= "userCreatingTest")
    public void createUserUsingHashmapTest() {
        HashMap data = new HashMap();
        data.put("name", "Karen");
        data.put("email", "karenkaren97@gorest.in");
        data.put("gender", "male");
        data.put("status", "active");
        UserData userData = given().contentType("application/JSON")
                .body(data)
                .post("users")
                .then()
                .log()
                .all()
                .statusCode(201).extract().as(UserData.class);
        System.out.println(userData.toString());
    }
}
