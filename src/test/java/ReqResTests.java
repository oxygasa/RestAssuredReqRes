import common.pojoes.CreateUserReqPojo;
import common.pojoes.CreateUserResPojo;
import common.pojoes.Users1Pojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import test_steps.UserSteps;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReqResTests {


    @Test
    public void initTest() {
        given()
                .spec(UserSteps.REQ_SPEC)
                .expect()
                .statusCode(200) //Test continues when fail
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .spec(UserSteps.RES_SPEC)
                .body("data[0].email", equalTo("george.bluth@reqres.in")) // JSON-xpath. Google: gpath.
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George")) //if you need to find List during gpath
                .extract().jsonPath().getList("data.email");
    }

    @Test
    public void init2ndTest() {
        List<String> emails = //emails are located in .extract()jsonPath().getList("data.email")
                given()
                        .spec(UserSteps.REQ_SPEC)
                        .when().get()
                        .then()
                        .spec(UserSteps.RES_SPEC)
                        .extract().jsonPath().getList("data.email");
    }


    @Test
    public void userPojoTest1() { //long variation
        List<Users1Pojo> users = //main > java > pojoes > UserPojo.
                given()
                        .spec(UserSteps.REQ_SPEC)
                        .when().get()
                        .then()
                        .spec(UserSteps.RES_SPEC)
                        .extract().jsonPath().getList("data", Users1Pojo.class); //deserialized
        assertThat(users).extracting(Users1Pojo::getEmail).contains("george.bluth@reqres.in"); //assertJ-core dependency.

    }

    @Test
    public void userPojoTest2(){ //short variation
        List<Users1Pojo> users = UserSteps.getUsers();
        assertThat(users).extracting(Users1Pojo::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser(){
        CreateUserReqPojo reqPojo = new CreateUserReqPojo();
        reqPojo.setName("Dimitri");
        reqPojo.setJob("QA-Automation");

        CreateUserResPojo resPojo = given()
                .spec(UserSteps.REQ_SPEC)
                .body(reqPojo)
                .when().post()
                .then()
                .extract().as(CreateUserResPojo.class);
        assertThat(resPojo)
                .isNotNull()
                .extracting(CreateUserResPojo::getName)
                .isEqualTo(reqPojo.getName());
    }
}
