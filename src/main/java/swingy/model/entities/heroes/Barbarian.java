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

	public static class BarbarianBuilder{
		private String name;

		public BarbarianBuilder name(String name) { this.name = name; return this; }

		public Ranger build(){
			return new Ranger(name);
		}
	}
}
