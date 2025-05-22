package dev.alejandro.tennis_game.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false, unique = true)
    private Long id; 
    
    @Column(name = "player_name", nullable = false)
    private String name;

    private int points = 0;

    public Player() {}

    public Player(String name){
        this.name = name;
    }

    public Player(String name, Long id){
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public Long getId() { return id; }
    public int getPoints() { return points; }

    public void setName(String name) { this.name = name; }

    public void scorePoint(){ points++; }
}
