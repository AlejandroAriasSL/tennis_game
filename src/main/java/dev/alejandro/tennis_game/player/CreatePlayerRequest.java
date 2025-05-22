package dev.alejandro.tennis_game.player;

public record CreatePlayerRequest(String name) implements PlayerRequest {

    @Override
    public Player toEntity() {
        return new Player(name);
    }
}
