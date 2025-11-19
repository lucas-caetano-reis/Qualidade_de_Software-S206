package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class PokeTest {

    @Test
    public void testGetPikachu() {
        given()
                .baseUri("https://pokeapi.co/api/v2")
                .when()
                .get("/pokemon/pikachu")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("name", equalTo("pikachu"))
                .body("id", equalTo(25))
                .body("types[0].type.name", equalTo("electric"));
    }

    @Test
    public void testGetPokemonById() {
        given()
                .baseUri("https://pokeapi.co/api/v2")
                .pathParam("id", 150)
                .when()
                .get("/pokemon/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("mewtwo"))
                .body("base_experience", greaterThan(200));
    }

    @Test
    public void testListPokemon() {
        given()
                .baseUri("https://pokeapi.co/api/v2")
                .queryParam("limit", 50)
                .when()
                .get("/pokemon")
                .then()
                .statusCode(200)
                .body("results.size()", equalTo(50))
                .body("results.name", hasItem("bulbasaur"));
    }
}
