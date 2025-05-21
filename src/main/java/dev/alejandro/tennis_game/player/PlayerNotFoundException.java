package dev.alejandro.tennis_game.player;

import jakarta.persistence.EntityNotFoundException;

public class PlayerNotFoundException extends EntityNotFoundException {
    public PlayerNotFoundException(Long id){
        super("Player with id: " + id + " not found");
    }
}
