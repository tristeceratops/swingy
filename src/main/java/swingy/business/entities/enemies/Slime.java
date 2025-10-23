package swingy.business.entities.enemies;

import swingy.game.Coordinate;

public class Slime extends Enemy {

	public Slime(String name) {
		super(1, 1, 1, 1);
		this.name = name;
		this.coordinate = new Coordinate(0, 0);
	}

	public Slime(String name, Coordinate coordinate) {
		super(1, 1, 1, 1);
		this.name = name;
		this.coordinate = coordinate;
	}

	public Slime(String name, Coordinate coordinate, int level){
		super(level, 1, 1, 1);
		this.name = name;
		this.coordinate = coordinate;
	}

	public static class SlimeBuilder{
		private String name = "Slime";
		private Coordinate coordinate = new Coordinate(0, 0);
		private int level = 1;

		public SlimeBuilder name(String name) { this.name = name; return this; }
		public SlimeBuilder coordinate(Coordinate c) { this.coordinate = c; return this; }
		public SlimeBuilder level(int l) { this.level = l; return this; }
		public Slime build(){
			return new Slime(name, coordinate, level);
		}
	}
}
