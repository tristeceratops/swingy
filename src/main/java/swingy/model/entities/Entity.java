package swingy.model.entities;

import swingy.model.Coordinate;

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
}