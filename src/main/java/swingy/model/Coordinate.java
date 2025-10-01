package swingy.model;

import java.util.*;

public class Coordinate {

	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "{" + x + "," + y + '}';
	}

	//maxX and maxY are exclusive
	public static List<Coordinate> generateRandomUniqueCoordinates(int size, int maxX, int maxY) {
		if (size <= 0) {
			return new ArrayList<>();
		}

		List<Coordinate> coordinates = new ArrayList<>();
		Set<Coordinate> usedCoordinates = new HashSet<>();
        Random random = new Random();
        int maxAttempts = size * 10; // Prevent infinite loops for large size
        int attempts = 0;

		while (coordinates.size() < size && attempts < maxAttempts) {
            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);
            Coordinate coord = new Coordinate(x, y);

            if (!usedCoordinates.contains(coord)) {
                usedCoordinates.add(coord);
                coordinates.add(coord);
            }
            attempts++;
        }

        return coordinates;
    }
}