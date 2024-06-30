package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = Dataproviders.class)
    public void test_PostUser(String userID, String userName, String fname,
                              String lName, String email, String password, String pn){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(pn);

        Response res = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Dataproviders.class)
    public void test_DeleteUser(String userName){

        Response res = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(res.getStatusCode(), 200);

    }
}
