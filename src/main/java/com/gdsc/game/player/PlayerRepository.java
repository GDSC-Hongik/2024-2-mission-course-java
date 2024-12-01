package com.gdsc.game.player;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
public class PlayerRepository {
    private final List<Player> players = new ArrayList<>();

    public void save(Player player) {
        players.add(player);
    }

    public List<Player> findAll() {
        return players;
    }

    public Player findOneByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
