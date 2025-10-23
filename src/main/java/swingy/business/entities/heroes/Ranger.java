package swingy.business.entities.heroes;

public class Ranger extends Hero{
	public Ranger(String name) {
		super(name, 2, 1, 10);
		this.heroClass = "Ranger";
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
		private int attack = 0;
		private int defence = 0;
		private int hitpoints = 0;

		public RangerBuilder name(String name) { this.name = name; return this; }

		public RangerBuilder level(int level) {
			this.level = level;
			return this;
		}

		public RangerBuilder experience(int experience) {
			this.experience = experience;
			return this;
		}

		public RangerBuilder attack(int attack) {
			this.attack = attack;
			return this;
		}

		public RangerBuilder defence(int defence) {
			this.defence = defence;
			return this;
		}

		public RangerBuilder hitpoints(int hitpoints) {
			this.hitpoints = hitpoints;
			return this;
		}

		public Ranger build(){
			Ranger ranger = new Ranger(name);
			ranger.level = level;
			ranger.experience = experience;
			if (this.attack > 0)
				ranger.setAttack(this.attack);
			if (this.defence > 0)
				ranger.setDefence(this.defence);
			if (this.hitpoints > 0)
				ranger.setHitpoints(this.hitpoints);
			return new Ranger(name);
		}
	}
}
