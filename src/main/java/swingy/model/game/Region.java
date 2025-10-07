package swingy.model.game;

public record Region(Coordinate topLeft, int width, int height) {
	int x() {return topLeft.getX();}
	int y() {return topLeft.getY();}
	int right() {return topLeft.getX() + width;}
	int bottom() {return topLeft.getY() + height;}

	@Override
	public String toString() {
		return "[" + topLeft + "] -> ["+right() + "," + bottom() +"]";
	}
}
