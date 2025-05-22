package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class PlayerTest {

    String name;
    Long id;
    Player player;

    @BeforeEach
    void setUp(){
        name = "Player1";
        id = 1L;
        player = new Player(name, id); 
    }


    @Test
    @DisplayName("Player is created with correct attributes")
    void test_player_attributes(){

        assertThat(player.getName(), is(equalTo(name)));
        assertThat(player.getId(), is(equalTo(id)));
    }

    @Test
    @DisplayName("Player constructor with no parameters")
    void test_player_constructor_without_parameters(){

        Player playerInstance = new Player();

        assertThat(playerInstance.getClass(), is(equalTo(Player.class)));
    }

    @Test
    @DisplayName("Player can change name")
    void test_player_name_change(){

        String newName = "player2";
        
        player.setName(newName);
        assertThat(player.getName(), is(equalTo(newName)));

    }

    @Test
    @DisplayName("Player starts with 0 points")
    void test_player_initial_points_are_zero(){

        int points = player.getPoints();

        assertThat(points, is(equalTo(0)));
    }

    @Test
    @DisplayName("Player should score 1 point")
    void test_player_scores_points(){

        int points = player.getPoints();
        assertThat(points, is(equalTo(0)));

        player.scorePoint();
        assertThat(player.getPoints(), is(equalTo(1)));
    }
}