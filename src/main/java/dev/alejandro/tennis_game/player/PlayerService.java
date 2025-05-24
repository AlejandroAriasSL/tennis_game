package dev.alejandro.tennis_game.player;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.alejandro.tennis_game.player.exception.PlayerNotFoundException;
import dev.alejandro.tennis_game.player.requests.PlayerRequest;
import dev.alejandro.tennis_game.player.requests.UpdatePlayerRequest;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public void createOrUpdate(PlayerRequest request){
        playerRepository.save(request.toEntity());
    }

    public UpdatePlayerRequest getPlayerById(Long id) throws PlayerNotFoundException{
        return playerRepository.findById(id)
                               .map(UpdatePlayerRequest::fromEntity)
                               .orElseThrow(() -> 
                               new PlayerNotFoundException(id));
    }

    public List<UpdatePlayerRequest> getAllPlayers(){
        return playerRepository.findAll()
                               .stream()
                               .map(UpdatePlayerRequest::fromEntity)
                               .toList();
    }

    public void deletePlayer(Long id) throws PlayerNotFoundException{
        if (!playerRepository.existsById(id)){
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
    }

}
