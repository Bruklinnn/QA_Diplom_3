package AllForUser;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class UserSteps {
    public static final Gson gson = new Gson();

    @Step("Create user")
    public static void createUser(User user) {
        Response response = given()
                .log().all()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
        response.then().log().all();
    }

    @Step("Get accessToken")
    public static String getAccessToken(User user) {
        Response response = RestAssured.given()
                .log().all()
                .header("Content-type", "application/json")
                .body(gson.toJson(user))
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");
        response.then().log().all();
        RegUser regUser = gson.fromJson(response.getBody().asString(), RegUser.class);
        return regUser.getAccessToken();
    }

    @Step("Delete user")
    public static void deleteUser(User user, String accessToken) {
        Response delete = given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(gson.toJson(user))
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
        delete.then().log().all();
    }
}
