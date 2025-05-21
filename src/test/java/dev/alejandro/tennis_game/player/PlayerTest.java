package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PlayerTest {


    @Test
    @DisplayName("Player is created with correct attributes")
    void test_player_attributes(){

        String name = "player1";
        Long id = 1L;

        Player player = new Player(name, id);

        assertThat(player.getName(), is(equalTo(name)));
        assertThat(player.getId(), is(equalTo(id)));
    }
}
