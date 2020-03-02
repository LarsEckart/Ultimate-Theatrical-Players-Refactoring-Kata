package xp.theatrical.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExerciseApplicationTests {

    @LocalServerPort
    int port;

    @Test
    void contextLoads() {
    }

    @Test
    void missing_customer() {
        given()
            .port(port)
        .when()
            .get("/statement/")
        .then()
            .statusCode(404);
    }

    @Test
    void missing_customer_message() {
        String response = given().port(port).when().get("/statement/").asString();
        assertThat(response).contains("\"status\":404,\"error\":\"Not Found\",\"message\":\"No message available\",\"path\":\"/statement/\"");
    }

    // TODO: write tests
}
