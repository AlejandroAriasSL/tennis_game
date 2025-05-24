package dev.alejandro.tennis_game.player.exception;

public record ApiError(int status, String error, String message) {

}
