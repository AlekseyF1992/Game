package ru.netology.javaqa;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> registeredPlayers = new ArrayList<>();

    public void register(Player player) {
        if (!isRegistered(player.getName())) {
            registeredPlayers.add(player);
        }
    }

    private boolean isRegistered(String playerName) {
        for (Player player : registeredPlayers) {
            if (player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    private Player findByName(String playerName) {
        for (Player player : registeredPlayers) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null) {
            throw new NotRegisteredException(playerName1);
        }
        if (player2 == null) {
            throw new NotRegisteredException(playerName2);
        }

        int strength1 = player1.getStrength();
        int strength2 = player2.getStrength();

        if (strength1 == strength2) {
            return 0; // ничья
        } else if (strength1 > strength2) {
            return 1; // победа первого
        } else {
            return 2; // победа второго
        }
    }

    public List<Player> getRegisteredPlayers() {
        return new ArrayList<>(registeredPlayers);
    }
}
