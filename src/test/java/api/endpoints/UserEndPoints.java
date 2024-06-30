package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .log().all()
        .when()
                .post(Routes.POST_URL);
        return response;
    }
    public static Response getUser(String userName){
        System.out.println("username param : " + userName);
        Response response = given()
                .pathParams("username", userName)
                .log().all()
        .when()
                .get(Routes.GET_URL);
        return response;
    }
    public static Response updateUser(String userName, User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(payload)
                .when()
                .put(Routes.PUT_URL);
        return response;
    }
    public static Response deleteUser(String userName){
        Response response = given()
                .pathParams("username", userName)
                .when()
                .delete(Routes.DELETE_URL);
        return response;
    }



}
