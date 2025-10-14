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

	public static class KnightBuilder implements HeroBuilder<Knight>{
		private String name = "Knight";
		private int level = 1;
		private int experience = 0;

		public KnightBuilder name(String name) { this.name = name; return this; }
		public KnightBuilder level(int level) { this.level = level; return this; }
		public KnightBuilder experience(int experience) { this.experience = experience; return this; }

		public Knight build(){
			Knight knight = new Knight(name);
			knight.setLevel(level);
			knight.setExperience(experience);
			return knight;
		}
	}
}
