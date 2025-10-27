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

	public static class BarbarianBuilder extends AbstractHeroBuilder<Barbarian, BarbarianBuilder> {

		{
			name = "Barbarian";
		}

		@Override
		public Barbarian build() {
			Barbarian barbarian = new Barbarian(name);
			if (id != null)
				barbarian.setId(id);
			barbarian.setLevel(level);
			barbarian.setExperience(experience);
			if (attack > 0)
				barbarian.setAttack(this.attack);
			if (defence > 0)
				barbarian.setDefence(this.defence);
			if (hitpoints > 0)
				barbarian.setHitpoints(this.hitpoints);
			return barbarian;
		}
	}
}
