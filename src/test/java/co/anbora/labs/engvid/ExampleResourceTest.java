package co.anbora.labs.engvid;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ExampleResourceTest {

  @Test
  public void testHelloEndpoint() {
    given()
        .when()
        .get("/manifest.json")
        .then()
        .statusCode(200)
        .body(containsString("co.anbora.labs.engvid.videos"));
  }
}
