package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class PlayerControllerTest {

    @Test
    @DisplayName("PlayerController getAllPlayers returns empty list when no players found")
    void test_playerController_returns_empty_list_when_no_players(){

        PlayerRepository repository = mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(repository);
        PlayerController playerController = new PlayerController(playerService);

        ResponseEntity<List<PlayerDTO>> response = playerController.getAllPlayers();

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().size(), is(0));
    }

    @Test
    @DisplayName("PlayerController getAllPlayers returns list of playerDTOs")
    void test_playerController_returns_list_of_playerDto(){

        PlayerRepository repository = mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(repository);
        PlayerController playerController = new PlayerController(playerService);

        List<Player> playerEntities = List.of(new Player("player1", 1L), new Player("player2", 2L));

        when(repository.findAll()).thenReturn(playerEntities);

        ResponseEntity<List<PlayerDTO>> response = playerController.getAllPlayers();

        List<PlayerDTO> playerDtos = playerEntities.stream().map(PlayerDTO::fromEntity).toList(); 

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().size(), is(2));
        assertThat(response.getBody(), is(equalTo(playerDtos)));

    }
}
