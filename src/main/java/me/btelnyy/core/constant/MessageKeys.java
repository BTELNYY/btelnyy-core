package me.btelnyy.core.constant;

public final class MessageKeys {

    public static final String ERROR_PLAYER_NOT_FOUND = "error.player-not-found";
    public static final String ERROR_PLAYERS_ONLY = "error.players-only";

    public static final String COMMAND_COORDS_SELF_POSITION = "commands.coords.self";
    public static final String COMMAND_COORDS_OTHER_POSITION = "commands.coords.other";

    private MessageKeys() {
        throw new UnsupportedOperationException("instantiate static class");
    }
}
