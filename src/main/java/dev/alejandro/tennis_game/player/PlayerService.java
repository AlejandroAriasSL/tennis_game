package dev.alejandro.tennis_game.player;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(Long id){
        return playerRepository.findById(id)
                               .orElseThrow(() -> 
                               new EntityNotFoundException("Player with ID " + id + " not found"));
    }

}
