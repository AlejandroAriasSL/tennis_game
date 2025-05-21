package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityNotFoundException;

public class PlayerServiceTest {


    @Test
    @DisplayName("PlayerService returns player by ID when found")
    void test_player_found_by_id(){

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        String name = "Player1";
        Long id = 1L;
        Player player = new Player(name, id);

        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        Player foundPlayer = playerService.getPlayerById(id);

        assertThat(foundPlayer, is(equalTo(player)));

    }

    @Test
    @DisplayName("PlayerService throws exception when player not found by ID")
    void test_player_not_found_throws_exception(){

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        PlayerService playerService = new PlayerService(playerRepository);

        Long id = 1L;

        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        String expectedMessage = "Player with ID " + id + " not found";
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            playerService.getPlayerById(id);
        });

        assertThat(exception.getMessage(), is(equalTo(expectedMessage)));
    }
}
