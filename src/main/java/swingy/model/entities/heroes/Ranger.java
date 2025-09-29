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

	public static class RangerBuilder{
		private String name;

		public RangerBuilder name(String name) { this.name = name; return this; }

		public Ranger build(){
			return new Ranger(name);
		}
	}
}
