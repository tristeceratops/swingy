package swingy.business.entities.heroes;

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

	public static class KnightBuilder extends AbstractHeroBuilder<Knight, KnightBuilder>{
		{
			name = "Knight";
		}

		@Override
		public Knight build() {
			Knight knight = new Knight(name);
			if (id != null)
				knight.setId(id);
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
