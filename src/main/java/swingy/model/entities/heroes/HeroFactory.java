package swingy.model.entities.heroes;

import swingy.model.Coordinate;

public class HeroFactory {

	public static Hero create(String type, String name) {
		switch (type) {
			case "Ranger": return new Ranger.RangerBuilder().name(name).build();
			case "Knight": return new Knight.KnightBuilder().name(name).build();
			case "Barbarian": return new Barbarian.BarbarianBuilder().name(name).build();
			//add new class here
			default: throw new IllegalArgumentException("Unknown enemy type: " + type);
		}
	}
}
