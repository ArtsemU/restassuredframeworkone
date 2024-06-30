package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class UserTests2 {

    Random rand;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setupData(){
        rand = new Random();
        int randNum = rand.nextInt(10000000);
        userPayload = new User();

        userPayload.setId(randNum);
        userPayload.setUsername("t0" + randNum);
        userPayload.setFirstName("Fname_" + randNum);
        userPayload.setLastName("lname_" + randNum);
        userPayload.setEmail("user_" + randNum + "@example.com");
        userPayload.setPassword("password");
        userPayload.setPhone("+3752912345678");

        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void test_createUser(){
        logger.info("******* creating user *******");
        Response res = UserEndPoints2.createUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("******* user created *******");
    }
    @Test(priority = 2)
    public void test_getUser() {
        logger.info("******* get user *******");
        Response res = UserEndPoints2.getUser(this.userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        logger.info("******* user value received *******");
    }
    @Test(priority = 3)
    public void test_updateUser(){
        // Update user using payload
        logger.info("******* updating user *******");
        userPayload.setFirstName("First _Updated");
        userPayload.setLastName("Last_Updated");
        userPayload.setEmail("user_test_email_updated@example.com");

        Response res = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);

        // Check updating
        Response resAfterUpdating = UserEndPoints2.getUser(this.userPayload.getUsername());
        Assert.assertEquals(resAfterUpdating.getStatusCode(), 200);
        logger.info("******* user updated *******");
    }
    @Test(priority = 4)
    public void test_deleteUser() {
        logger.info("******* deleting user *******");
        Response res = UserEndPoints2.deleteUser(userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);

        // Check deleting
        Response resAfterDeleting = UserEndPoints2.getUser(userPayload.getUsername());
        Assert.assertEquals(resAfterDeleting.getStatusCode(), 404);
        logger.info("******* user deleted *******");
    }
}
