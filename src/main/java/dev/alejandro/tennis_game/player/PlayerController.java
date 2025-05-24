package dev.alejandro.tennis_game.player;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alejandro.tennis_game.player.requests.CreatePlayerRequest;
import dev.alejandro.tennis_game.player.requests.UpdatePlayerRequest;

@RestController
@RequestMapping(path = PlayerController.BASE_PATH)
public class PlayerController {

    private static final String BASE_PATH = "/api/player";
    
    private final PlayerService playerService;

    public PlayerController(final PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<UpdatePlayerRequest>> getAllPlayers(){
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdatePlayerRequest> getPlayerById(@PathVariable final Long id){
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping
    public ResponseEntity<UpdatePlayerRequest> createPlayer(@RequestBody final CreatePlayerRequest createRequest){
        UpdatePlayerRequest response = playerService.createOrUpdate(createRequest);
        URI location = URI.create(BASE_PATH + "/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdatePlayerRequest> updatePlayer(@RequestBody final UpdatePlayerRequest updateRequest){
        UpdatePlayerRequest response =  playerService.createOrUpdate(updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable final Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
