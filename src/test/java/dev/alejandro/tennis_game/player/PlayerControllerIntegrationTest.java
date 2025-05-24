
package dev.alejandro.tennis_game.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.alejandro.tennis_game.player.exception.ApiError;
import dev.alejandro.tennis_game.player.requests.CreatePlayerRequest;
import dev.alejandro.tennis_game.player.requests.UpdatePlayerRequest;


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

    @Test
    @DisplayName("It should return a list of players and 2xx response")
    void test_create_and_get_player_returns_2XX(){
        CreatePlayerRequest request = new CreatePlayerRequest("Player1");
        restTemplate.postForEntity("/api/player", request, Void.class);

        ResponseEntity<UpdatePlayerRequest[]> getResponse = restTemplate.getForEntity("/api/player", UpdatePlayerRequest[].class);

        List<String> playerNames = Arrays.stream(getResponse.getBody())
                                         .map(UpdatePlayerRequest::name)
                                         .toList();

        assertThat(getResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(playerNames).contains(request.name());
    }

    @Test
    @DisplayName("It should return the selected player only and 2xx response")
    void test_get_selected_player_by_id_returns_2XX(){

        CreatePlayerRequest request = new CreatePlayerRequest("Player1");
        CreatePlayerRequest request2 = new CreatePlayerRequest("Player2");

        restTemplate.postForEntity("/api/player", request, Void.class);
        restTemplate.postForEntity("/api/player", request2, Void.class);

        ResponseEntity<UpdatePlayerRequest> getByIDresponse = restTemplate.getForEntity("/api/player/2", UpdatePlayerRequest.class);

        assertThat(getByIDresponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(getByIDresponse.getBody()).isNotNull();
        assertThat(getByIDresponse.getBody().id()).isEqualTo(2);
        assertThat(getByIDresponse.getBody().name()).isEqualTo(request2.name());
    }

    @Test
    @DisplayName("It should update the selected player and return 2xx response")
    void test_update_selected_player_and_return_2XX(){

        CreatePlayerRequest request = new CreatePlayerRequest("Player1");
        ResponseEntity<UpdatePlayerRequest> response = restTemplate.postForEntity("/api/player", request, UpdatePlayerRequest.class);

        URI playerLocation = response.getHeaders().getLocation();
        String path = playerLocation.getPath();
        Long id = Long.parseLong(path.substring(path.lastIndexOf("/") +1));

        UpdatePlayerRequest updateRequest = new UpdatePlayerRequest("Manolo", id);
        HttpEntity<UpdatePlayerRequest> entity = new HttpEntity<>(updateRequest);

        ResponseEntity<UpdatePlayerRequest> updateResponse = restTemplate.exchange(playerLocation, HttpMethod.PUT, entity, UpdatePlayerRequest.class);
        assertThat(updateResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(updateResponse.getBody().name()).isEqualTo(updateRequest.name());
    }

    @Test
    @DisplayName("It should delete the selected player and return 2xx response")
    void test_delete_selected_player_and_return_2XX(){

        CreatePlayerRequest request = new CreatePlayerRequest("Player1");
        restTemplate.postForEntity("/api/player", request, Void.class);

        ResponseEntity<UpdatePlayerRequest> getResponse = restTemplate.getForEntity("/api/player/1", UpdatePlayerRequest.class);
        assertThat(getResponse.getBody().name()).isEqualTo(request.name());

        restTemplate.delete("/api/player/1");

        ResponseEntity<ApiError> getResponseAfterDelete = restTemplate.getForEntity("/api/player/1", ApiError.class);
        assertThat(getResponseAfterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(getResponseAfterDelete.getBody().message()).containsIgnoringCase("not found");

    }
}