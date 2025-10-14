package swingy.model.entities.heroes;

public class Ranger extends Hero{
	public Ranger(String name) {
		super(name, 2, 1, 10);
	}

	@Override
	public String toString() {
		return "Ranger{" +
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

	public static class RangerBuilder implements HeroBuilder<Ranger> {
		private String name = "Ranger";
		private int level = 1;
		private int experience = 0;

		public RangerBuilder name(String name) { this.name = name; return this; }

		public RangerBuilder level(int level) {
			this.level = level;
			return this;
		}

		public RangerBuilder experience(int experience) {
			this.experience = experience;
			return this;
		}

		public Ranger build(){
			Ranger ranger = new Ranger(name);
			ranger.level = level;
			ranger.experience = experience;
			return new Ranger(name);
		}
	}
}
