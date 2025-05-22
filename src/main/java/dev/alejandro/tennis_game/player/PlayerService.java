package dev.alejandro.tennis_game.player;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public void createOrUpdate(PlayerDTO playerDto){
        playerRepository.save(playerDto.toEntity());
    }

    public PlayerDTO getPlayerById(Long id) throws PlayerNotFoundException{
        return playerRepository.findById(id)
                               .map(PlayerDTO::fromEntity)
                               .orElseThrow(() -> 
                               new PlayerNotFoundException(id));
    }

    public List<PlayerDTO> getAllPlayers(){
        return playerRepository.findAll()
                               .stream()
                               .map(PlayerDTO::fromEntity)
                               .toList();
    }

    public void deletePlayer(Long id) throws PlayerNotFoundException{
        if (!playerRepository.existsById(id)){
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
    }

}
