import common.Constants;
import common.pojoes.Users1Pojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReqResTests {
    Constants constants = new Constants();

    @Test
    public void initTest() {
        given()
                .baseUri(constants.getUrl())
                .basePath("/users")
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(200) //Test continues when fail
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200) //Test step has 1 chance assert.
                .and()
                .contentType(ContentType.JSON) //This could be skipped in .then() section.
                .body("data[0].email", equalTo("george.bluth@reqres.in")) // JSON-xpath. Google: gpath.
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George")) //if you need to find List during gpath
                .extract().jsonPath().getList("data.email");
    }

    @Test
    public void init2ndTest() {
        List<String> emails = //emails are located in .extract()jsonPath().getList("data.email")
                given()
                        .baseUri(constants.getUrl())
                        .basePath("/users")
                        .contentType(ContentType.JSON)
                        .when()
                        .get()
                        .then()
                        .extract().jsonPath().getList("data.email");
    }


    @Test
    public void userPojoTest() {
        List<Users1Pojo> users = //main > java > pojoes > UserPojo.
                given()
                        .baseUri(constants.getUrl())
                        .basePath("/users")
                        .contentType(ContentType.JSON)
                        .when()
                        .get()
                        .then()
                        .extract().jsonPath().getList("data", Users1Pojo.class); //deserialized
        assertThat(users).extracting(Users1Pojo::getEmail).contains("george.bluth@reqres.in"); //assertJ-core dependency.

    }
}
