package test_steps;

import common.pojoes.Users1Pojo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserSteps {
    public static final RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in/api")
            .setBasePath("/users")
            .setContentType(ContentType.JSON)
            .build();

    public static final ResponseSpecification RES_SPEC = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public static List<Users1Pojo> getUsers(){
        return given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data",Users1Pojo.class);
    }
}
