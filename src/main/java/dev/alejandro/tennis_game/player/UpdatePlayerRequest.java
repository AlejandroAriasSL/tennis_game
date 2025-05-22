package dev.alejandro.tennis_game.player;

public record UpdatePlayerRequest(String name, Long id) implements PlayerRequest{

    public static UpdatePlayerRequest fromEntity(Player player){
        return new UpdatePlayerRequest(player.getName(), player.getId());
    } 

    @Override
    public Player toEntity(){
        return new Player(this.name, this.id);
    }
}
