package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerDTOTest {

    @Test
    @DisplayName("PlayerDTO is created with correct attributes")
    void test_playerDTO_attributes(){

        String name = "Player1";
        Long id = 1L;

        PlayerDTO playerDto = new PlayerDTO(name, id);

        assertThat(playerDto.name(), is(equalTo(name)));
        assertThat(playerDto.id(), is(equalTo(id)));
    }

    @Test
    @DisplayName("PlayerDTO creates Player correctly")
    void test_playerDTO_creates_player_instance(){

        String name = "Player1";
        Long id = 1L;

        PlayerDTO playerDto = new PlayerDTO(name, id);
        Player player = playerDto.toEntity();

        assertThat(player.getClass(), is(equalTo(Player.class)));
        assertThat(player.getName(), is(equalTo(name)));
        assertThat(player.getId(), is(equalTo(id)));
    }

}
