package swingy.business.entities.heroes;

import swingy.game.Coordinate;
import swingy.business.entities.Entity;

public abstract class Hero extends Entity {

	protected int experience;
	protected int experienceToNextLevel;
	protected String heroClass;

	public Hero(String name, int attack, int defence, int hitPoints) {
		super();
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.hitpoints = hitPoints;
		this.maxHitpoints = hitPoints;
		this.level = 1;
		this.coordinate = new Coordinate(0, 0);
		this.experience = 0;
		this.experienceToNextLevel = calculateExperienceToNextLevel();
	}

	private int calculateExperienceToNextLevel() {
		return (this.level * 1000 + (int) Math.pow(level - 1, 2) * 450);
	}

	@Override
	public String toString() {
		return "Hero{" +
				"name='" + name + '\'' +
				", level=" + level +
				", attack=" + attack +
				", defence=" + defence +
				", hitpoints=" + hitpoints +
				", maxHitpoints=" + maxHitpoints +
				", coordinate=" + coordinate +
				", experienceToNextLevel=" + experienceToNextLevel +
				", experience=" + experience +
				'}';
	}

	public String getHeroClass() {
		return heroClass;
	}

	public void setHeroClass(String heroClass) {
		this.heroClass = heroClass;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getExperienceToNextLevel() {
		return experienceToNextLevel;
	}

	public void setExperienceToNextLevel(int experienceToNextLevel) {
		this.experienceToNextLevel = experienceToNextLevel;
	}

}
