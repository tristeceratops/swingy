package swingy.model.entities.heroes;

public class HeroFactory {

	public static HeroBuilder create(String type) {
		switch (type) {
			case "Ranger": return new Ranger.RangerBuilder();
			case "Knight": return new Knight.KnightBuilder();
			case "Barbarian": return new Barbarian.BarbarianBuilder();
			//add new class here
			default: throw new IllegalArgumentException("Unknown enemy type: " + type);
		}
	}
}
