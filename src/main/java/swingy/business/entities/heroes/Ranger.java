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

	public static class RangerBuilder extends AbstractHeroBuilder<Ranger, RangerBuilder>{

		{
			name = "Ranger";
		}

		@Override
		public Ranger build() {
			Ranger ranger = new Ranger(name);
			if (id != null)
				ranger.setId(id);
			ranger.setLevel(level);
			ranger.setExperience(experience);
			if (attack > 0)
				ranger.setAttack(this.attack);
			if (defence > 0)
				ranger.setDefence(this.defence);
			if (hitpoints > 0)
				ranger.setHitpoints(this.hitpoints);
			return ranger;
		}
	}
}
