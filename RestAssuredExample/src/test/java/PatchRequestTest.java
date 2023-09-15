import io.restassured.response.Response;
import org.example.request.UserRequest;
import org.example.response.UserData;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchRequestTest extends BaseTest {
    @Test(groups = "dataChangeTest")
    public void patchRequestExample() {
        UserRequest userRequest = new UserRequest("Test555", "555testtest@gorest.in", "male", "active");
        UserData userData =
                given()
                        .body(userRequest)
                        .post("users")
                        .then()
                        .log()
                        .all()
                        .statusCode(201).extract().as(UserData.class);
        userRequest.setName("Karen");
        Response res =
                given()
                        .header("Content-Type", "application/json")
                        .body(userRequest)
                        .when()
                        .patch("/users/{id}", userData.getId())
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println(res.body().asString());
    }
}
