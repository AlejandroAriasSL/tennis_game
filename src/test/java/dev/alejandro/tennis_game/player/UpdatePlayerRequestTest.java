package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.alejandro.tennis_game.player.requests.UpdatePlayerRequest;

public class UpdatePlayerRequestTest {

    @Test
    @DisplayName("UpdatePlayerRequest is created with correct attributes")
    void test_UpdatePlayerRequest_attributes(){

        String name = "Player1";
        Long id = 1L;

        UpdatePlayerRequest UpdatePlayerRequest = new UpdatePlayerRequest(name, id);

        assertThat(UpdatePlayerRequest.name(), is(equalTo(name)));
        assertThat(UpdatePlayerRequest.id(), is(equalTo(id)));
    }

    @Test
    @DisplayName("UpdatePlayerRequest creates Player correctly")
    void test_UpdatePlayerRequest_creates_player_instance(){

        String name = "Player1";
        Long id = 1L;

        UpdatePlayerRequest UpdatePlayerRequest = new UpdatePlayerRequest(name, id);
        Player player = UpdatePlayerRequest.toEntity();

        assertThat(player.getClass(), is(equalTo(Player.class)));
        assertThat(player.getName(), is(equalTo(name)));
        assertThat(player.getId(), is(equalTo(id)));
    }

    @Test
    @DisplayName("UpdatePlayerRequest is able to be build statically from an entity")
    void test_UpdatePlayerRequest_from_entity(){

        String name = "Player1";
        Long id = 1L;

        Player player = new Player(name, id);

        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.fromEntity(player);

        assertThat(updatePlayerRequest.getClass(), is(equalTo(UpdatePlayerRequest.class)));
        assertThat(updatePlayerRequest.name(), is(equalTo(name)));
        assertThat(updatePlayerRequest.id(), is(equalTo(id)));
    }

}
