package dev.alejandro.tennis_game.player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PlayerRepositoryTest {
    
    @Autowired
    private PlayerRepository repository;

    @Test
    @DisplayName("PlayerRepository save operation persists")
    public void test_save_player_persists(){
        Player player = new Player("Messi");

        Player savedPlayer = repository.save(player);

        assertThat(savedPlayer.getId(), is(notNullValue()));
        assertThat(player, is(equalTo(savedPlayer)));

    }
}
