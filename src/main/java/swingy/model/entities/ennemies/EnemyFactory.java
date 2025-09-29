package swingy.model.entities.ennemies;

import swingy.model.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {

	public static Enemy create(String type, String name){
		return create(type, name, new Coordinate(0, 0), 1);
	}

	public static Enemy create(String type, String name, Coordinate coordinate){
		return create(type, name, coordinate, 1);
	}

	public static Enemy create(String type, String name, Coordinate coordinate, int level) {
		switch (type) {
			case "Ghoul": return new Ghoul.GhoulBuilder().name(name).coordinate(coordinate).level(level).build();
			case "Slime": return new Slime.SlimeBuilder().name(name).coordinate(coordinate).level(level).build();
			//add new ennemies here
			default: throw new IllegalArgumentException("Unknown enemy type: " + type);
		}
	}
}
