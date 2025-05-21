package dev.alejandro.tennis_game.player;

public record PlayerDTO(String name, Long id) {

    public Player toEntity(){
        return new Player(this.name, this.id);
    }
}
