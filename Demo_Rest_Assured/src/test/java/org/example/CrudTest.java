package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class CrudTest {

    @Test
    public void testCreateUser() {
        given()
                .baseUri("https://reqres.in/api")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1") //Essa chave de api é pública
                .body("{\"name\": \"Lucas\", \"job\": \"dev\"}")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Lucas"))
                .body("job", equalTo("dev"));

    }

    @Test
    public void testGetUser() {
        given()
                .baseUri("https://reqres.in/api")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
    }

    @Test
    public void testUpdateUser() {
        given()
                .baseUri("https://reqres.in/api")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body("{\"name\": \"João\", \"job\": \"tester\"}")
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("João"))
                .body("job", equalTo("tester"));
    }

    @Test
    public void testPatchUser() {
        given()
                .baseUri("https://reqres.in/api")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body("{\"name\": \"João\", \"job\": \"dev\"}")
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("João"))
                .body("job", equalTo("dev"));
    }

    @Test
    public void testDeleteUser() {
        given()
                .baseUri("https://reqres.in/api")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
