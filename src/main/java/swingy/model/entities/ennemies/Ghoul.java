package swingy.model.entities.ennemies;

import swingy.model.Coordinate;

public class Ghoul extends Enemy{

	public Ghoul(String name) {
		super(1, 2, 2, 5);
		this.name = name;
		this.coordinate = new Coordinate(0, 0);
	}

	public Ghoul(String name, Coordinate coordinate) {
		super(1, 2, 2, 5);
		this.name = name;
		this.coordinate = coordinate;
	}

	public Ghoul(String name, Coordinate coordinate, int level){
		super(level, 2, 2, 5);
		this.name = name;
		this.coordinate = coordinate;
	}

	public static class GhoulBuilder{
		private String name = "Ghoul";
		private Coordinate coordinate = new Coordinate(0, 0);
		private int level = 1;

		public GhoulBuilder name(String name) { this.name = name; return this; }
		public GhoulBuilder coordinate(Coordinate c) { this.coordinate = c; return this; }
		public GhoulBuilder level(int l) { this.level = l; return this; }
		public Ghoul build(){
			return new Ghoul(name, coordinate, level);
		}
	}
}
