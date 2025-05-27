package dev.alejandro.tennis_game.player.requests;

import dev.alejandro.tennis_game.player.Player;

public record CreatePlayerRequest(String name) implements PlayerRequest {

    @Override
    public Player toEntity() {
        return new Player(name);
    }
}
