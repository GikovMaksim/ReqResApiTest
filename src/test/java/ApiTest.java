import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTest {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();


    @Test
    public void checkEmailByFirstnameAndLastname() {
        given().spec(REQ_SPEC)
         .when().get()
         .then().statusCode(200)
                .body("data.find{it.first_name == 'George' && it.last_name == 'Bluth'}.email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    public void checkEmailByFirstnameAndLastnamePagination() {
        given().spec(REQ_SPEC)
                .queryParam("page", 2)
        .when().get()
        .then().statusCode(200)
                .body("data.find{it.first_name == 'Michael' && it.last_name == 'Lawson'}.email", equalTo("michael.lawson@reqres"));
    }
}
