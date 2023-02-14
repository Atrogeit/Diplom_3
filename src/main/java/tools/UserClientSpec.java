package tools;

import User.User;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClientSpec extends ClientSpec {
    private static final String CREATE_PATH = "/api/auth/register";
    private static final String DELETE_PATH = "/api/auth/user";

    public ValidatableResponse create (User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(CREATE_PATH)
                .then();
    }
    public ValidatableResponse delete (String token) {
        return given()
                .spec(getSpec())
                .header("Authorization", token)
                .when()
                .delete(DELETE_PATH)
                .then();
    }

}
