
package dev.alejandro.tennis_game.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import dev.alejandro.tennis_game.player.requests.CreatePlayerRequest;


public class PlayerControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("It should create a player and return 2xx response")
    void test_create_player_returns_2XX() {
        CreatePlayerRequest request = new CreatePlayerRequest("Player1");
        
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/api/player", request, String.class);

        assertThat(postResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(postResponse.getBody()).isNotNull();
        assertThat(postResponse.getBody().contains("Jugador creado con éxito"));
    }
}