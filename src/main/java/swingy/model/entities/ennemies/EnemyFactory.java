package swingy.model.entities.ennemies;

import swingy.model.Coordinate;

import java.util.*;

public class EnemyFactory {

    private static final Map<String, EnemyCreator> ENEMY_CREATORS = new HashMap<>();

    static {
        // register available enemy types here
        ENEMY_CREATORS.put("Ghoul", (name, coord, lvl) ->
            new Ghoul.GhoulBuilder().name(name).coordinate(coord).level(lvl).build()
        );
        ENEMY_CREATORS.put("Slime", (name, coord, lvl) ->
            new Slime.SlimeBuilder().name(name).coordinate(coord).level(lvl).build()
        );
    }

    public static Enemy create(String type, String name) {
        return create(type, name, new Coordinate(0, 0), 1);
    }

    public static Enemy create(String type, String name, Coordinate coordinate) {
        return create(type, name, coordinate, 1);
    }

    public static Enemy create(String type, String name, Coordinate coordinate, int level) {
        EnemyCreator creator = ENEMY_CREATORS.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
        return creator.create(name, coordinate, level);
    }

    public static Enemy randomCreation(int level) {
        List<String> types = new ArrayList<>(ENEMY_CREATORS.keySet());
        Random random = new Random();
        String type = types.get(random.nextInt(types.size()));
		Coordinate coord = new Coordinate(0, 0);
        return create(type, type, coord, level);
    }
}