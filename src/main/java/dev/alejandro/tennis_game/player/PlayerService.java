package dev.alejandro.tennis_game.player;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public void savePLayer(Player player){
        playerRepository.save(player);
    }

    public Player getPlayerById(Long id){
        return playerRepository.findById(id)
                               .orElseThrow(() -> 
                               new PlayerNotFoundException(id));
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

}
