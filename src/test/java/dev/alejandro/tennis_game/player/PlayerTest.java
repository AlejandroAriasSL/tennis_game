package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;

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

    @Test
    @DisplayName("Player can change name")
    void test_player_name_change(){

        String name = "player1";
        Long id = 1L;

        Player player = new Player(name, id);
        String newName = "player2";
        
        player.setName(newName);
        assertThat(player.getName(), is(equalTo(newName)));

    }

    @Test
    @DisplayName("Player starts with 0 points")
    void test_player_initial_points_are_zero(){

        String name = "Player1";
        Long id = 1L;

        Player player = new Player(name, id);
        int points = player.getPoints();

        assertThat(player.getPoints(), is(equalTo(0)));
    }
}