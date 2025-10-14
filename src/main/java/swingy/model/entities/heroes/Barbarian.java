package swingy.model.entities.heroes;

public class Barbarian extends Hero{
	public Barbarian(String name) {
		super(name, 2, 0, 15);
	}

	@Override
	public String toString() {
		return "Barbarian{" +
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

	public static class BarbarianBuilder implements  HeroBuilder<Barbarian>{
		private String name = "Barbarian";
		private int level = 1;
		private int experience = 0;

		public BarbarianBuilder name(String name) { this.name = name; return this; }

		public BarbarianBuilder level(int level) {this.level = level; return this; }

		public BarbarianBuilder experience(int experience) {this.experience = experience; return this; }

		public Barbarian build(){
			Barbarian barbarian = new Barbarian(name);
			barbarian.setLevel(level);
			barbarian.setExperience(experience);
			return new Barbarian(name);
		}
	}
}
