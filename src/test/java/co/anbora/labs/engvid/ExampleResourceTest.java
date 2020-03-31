package co.anbora.labs.engvid;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/manifest.json")
          .then()
             .statusCode(200)
             .body(containsString("co.anbora.labs.engvid.videos"));
    }

}