package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerServiceTest {

    String name;
    Long id;
    PlayerRepository playerRepository;
    PlayerService playerService;

    @BeforeEach
    void setUp(){
        name = "Player1";
        id = 1L;
        playerRepository = mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }


    @Test
    @DisplayName("PlayerService returns player by ID when found")
    void test_player_found_by_id(){

        Player player = new Player(name, id);

        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        Player foundPlayer = playerService.getPlayerById(id);

        assertThat(foundPlayer, is(equalTo(player)));

    }

    @Test
    @DisplayName("PlayerService throws exception when player not found by ID")
    void test_player_not_found_throws_exception(){

        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        String expectedMessage = "Player with id: " + id + " not found";
        PlayerNotFoundException exception = assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getPlayerById(id);
        });

        assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
    }

    @Test
    @DisplayName("PlayerService returns all players")
    void test_all_players_found(){

        Player player = new Player(name, id);
        Player player2 = new Player("player2", 2L);

        List<Player> players = List.of(player, player2);

        when(playerRepository.findAll()).thenReturn(players);

        assertThat(playerRepository.findAll(), is(equalTo(players)));
    }
}
