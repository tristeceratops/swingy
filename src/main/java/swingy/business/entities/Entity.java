package swingy.business.entities;

import swingy.game.Coordinate;

import jakarta.validation.constraints.*;

public abstract class Entity {

	@NotEmpty
	@Size(min=1, max=25)
	protected String name;

	@Positive
	protected int level;
	@PositiveOrZero
	protected int attack;
	@PositiveOrZero
	protected int defence;
	@PositiveOrZero
	protected int hitpoints;
	@Positive
	protected int maxHitpoints;
	@NotNull
	protected Coordinate coordinate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public int getMaxHitpoints() {
		return maxHitpoints;
	}

	public void setMaxHitpoints(int maxHitpoints) {
		this.maxHitpoints = maxHitpoints;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public void move(int x, int y) {
		this.coordinate.setX(x);
		this.coordinate.setY(y);
	}
}