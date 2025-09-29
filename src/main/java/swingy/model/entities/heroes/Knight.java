package swingy.model.entities.heroes;

public class Knight extends Hero{
	public Knight(String name) {
		super(name, 1, 2, 10);
	}

	@Override
	public String toString() {
		return "Knight{" +
				"experience=" + experience +
				", experienceToNextLevel=" + experienceToNextLevel +
				", name='" + name + '\'' +
				", level=" + level +
				", attack=" + attack +
				", defence=" + defence +
				", hitpoints=" + hitpoints +
				", maxHitpoints=" + maxHitpoints +
				", coordinate=" + coordinate +
				'}';
	}

	public static class KnightBuilder{
		private String name;

		public KnightBuilder name(String name) { this.name = name; return this; }

		public Ranger build(){
			return new Ranger(name);
		}
	}
}
