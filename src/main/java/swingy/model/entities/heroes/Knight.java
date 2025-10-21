package swingy.model.entities.heroes;

public class Knight extends Hero{
	public Knight(String name) {
		super(name, 1, 2, 10);
		this.heroClass = "Knight";
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
		private int attack = 0;
		private int defence = 0;
		private int hitpoints = 0;

		public KnightBuilder name(String name) { this.name = name; return this; }
		public KnightBuilder level(int level) { this.level = level; return this; }
		public KnightBuilder experience(int experience) { this.experience = experience; return this; }
		public KnightBuilder attack(int attack) {this.attack = attack; return this; }
		public KnightBuilder defence(int defence) {this.defence = defence; return this; }
		public KnightBuilder hitpoints(int hitpoints) {this.hitpoints = hitpoints; return this; }

		public Knight build(){
			Knight knight = new Knight(name);
			knight.setLevel(level);
			knight.setExperience(experience);
			if (attack > 0)
				knight.setAttack(this.attack);
			if (defence > 0)
				knight.setDefence(this.defence);
			if (hitpoints > 0)
				knight.setHitpoints(this.hitpoints);
			return knight;
		}
	}
}
