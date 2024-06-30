package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    // Method return url from [property file
    public static ResourceBundle getURL(){
        // load properties
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){
        String post_url = getURL().getString("post_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .log().all()
        .when()
                .post(post_url);
        return response;
    }
    public static Response getUser(String userName){
        String get_url = getURL().getString("get_url");
        Response response = given()
                .pathParams("username", userName)
                .log().all()
        .when()
                .get(get_url);
        return response;
    }
    public static Response updateUser(String userName, User payload){
        String put_url = getURL().getString("put_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", userName)
                .body(payload)
                .when()
                .put(put_url);
        return response;
    }
    public static Response deleteUser(String userName){
        String delete_url = getURL().getString("delete_url");
        Response response = given()
                .pathParams("username", userName)
                .when()
                .delete(delete_url);
        return response;
    }
}
