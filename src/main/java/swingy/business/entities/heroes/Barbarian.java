package swingy.business.entities.heroes;

public class Barbarian extends Hero{
	public Barbarian(String name) {
		super(name, 2, 0, 15);
		this.heroClass = "Barbarian";
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

	public static class BarbarianBuilder implements HeroBuilder<Barbarian>{
		private String name = "Barbarian";
		private int level = 1;
		private int experience = 0;
		private int attack = 0;
		private int defence = 0;
		private int hitpoints = 0;

		public BarbarianBuilder name(String name) { this.name = name; return this; }
		public BarbarianBuilder level(int level) {this.level = level; return this; }
		public BarbarianBuilder experience(int experience) {this.experience = experience; return this; }
		public BarbarianBuilder attack(int attack) {this.attack = attack; return this; }
		public BarbarianBuilder defence(int defence) {this.defence = defence; return this; }
		public BarbarianBuilder hitpoints(int hitpoints) {this.hitpoints = hitpoints; return this; }


		public Barbarian build(){
			Barbarian barbarian = new Barbarian(name);
			barbarian.setLevel(level);
			barbarian.setExperience(experience);
			if (attack > 0)
				barbarian.setAttack(this.attack);
			if (defence > 0)
				barbarian.setDefence(this.defence);
			if (hitpoints > 0)
				barbarian.setHitpoints(this.hitpoints);
			return new Barbarian(name);
		}
	}
}
