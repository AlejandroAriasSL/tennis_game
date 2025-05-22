package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

public class PlayerControllerTest {

    PlayerRepository repository;
    PlayerService playerService;
    PlayerController playerController;

    @BeforeEach
    void setUp(){
        repository  = mock(PlayerRepository.class);
        playerService = new PlayerService(repository);
        playerController = new PlayerController(playerService);
    }

    @Test
    @DisplayName("PlayerController getAllPlayers returns empty list when no players found")
    void test_playerController_returns_empty_list_when_no_players(){

        ResponseEntity<List<PlayerDTO>> response = playerController.getAllPlayers();

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().size(), is(0));
    }

    @Test
    @DisplayName("PlayerController getAllPlayers returns list of playerDTOs")
    void test_playerController_returns_list_of_playerDto(){

        List<Player> playerEntities = List.of(new Player("player1", 1L), new Player("player2", 2L));

        when(repository.findAll()).thenReturn(playerEntities);

        ResponseEntity<List<PlayerDTO>> response = playerController.getAllPlayers();

        List<PlayerDTO> playerDtos = playerEntities.stream().map(PlayerDTO::fromEntity).toList(); 

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().size(), is(2));
        assertThat(response.getBody(), is(equalTo(playerDtos)));

    }

    @Test
    @DisplayName("PlayerController getPlayerById returns player by id")
    void test_playerController_returns_player_by_id(){

        Player player = new Player("Player1", 1L);
        
        when(repository.findById(player.getId())).thenReturn(Optional.of(player));

        ResponseEntity<PlayerDTO> response = playerController.getPlayerById(player.getId());

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue(null)));
        assertThat(response.getBody(), is(equalTo(PlayerDTO.fromEntity(player))));
    }

    @Test
    @DisplayName("PlayerController createPlayer returns success response")
    void test_playerController_create_succesful(){

        PlayerDTO player = new PlayerDTO("Player1", 1L);

        ResponseEntity<String> response = playerController.createPlayer(player);

        assertThat(response.getStatusCode().is2xxSuccessful(), is(equalTo(true)));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody(), is(equalTo("Jugador creado con éxito")));

        verify(repository, times(1)).save(any(Player.class));
    }
}
