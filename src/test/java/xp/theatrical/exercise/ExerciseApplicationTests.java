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
    void name() {
        String response = given().port(port).when().get("/statement/BigCo").asString();
        assertThat(response).isEqualTo("Statement for BigCo\n"
                + "  Hamlet: $400.00 (10 seats)\n"
                + "  As You Like It: $500.00 (25 seats)\n"
                + "  Othello: $400.00 (20 seats)\n"
                + "Amount owed is $1,300.00\n"
                + "You earned 5 credits\n");
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
