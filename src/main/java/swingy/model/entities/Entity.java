package swingy.model.entities;

import swingy.model.Coordinate;

public abstract class Entity {
	protected String name;
	protected int level;
	protected int attack;
	protected int defence;
	protected int hitpoints;
	protected int maxHitpoints;
	protected Coordinate coordinate;
}