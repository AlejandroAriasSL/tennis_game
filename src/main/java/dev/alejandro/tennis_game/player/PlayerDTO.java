package dev.alejandro.tennis_game.player;

public record PlayerDTO(String name, Long id) {

    public static PlayerDTO fromEntity(Player player){
        return new PlayerDTO(player.getName(), player.getId());
    } 

    public Player toEntity(){
        return new Player(this.name, this.id);
    }
}
