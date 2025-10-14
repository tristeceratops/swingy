package swingy.model.game;

import swingy.model.entities.ennemies.Enemy;
import swingy.model.entities.ennemies.EnemyFactory;

import java.util.*;

public class GameMap {

	int size;
	int level;
	List<Enemy> enemies;
	private final Random random = new Random();

	/*
	 * gameMap characters and their meanings:
	 * g: ground, empty case
	 * e: enemy
	 * h: hero
	 * w: wall, obstacle that hero or enemy can't pass through
	 */
	char[][] map;

	public GameMap(int level) {
		this.level = level;
		this.size = calculateSize();
		this.enemies = new ArrayList<>();
		createMap();
	}

	private int calculateSize() {
		int l = this.level;
		return (l - 1) * 5 + 10 - (l % 2);
	}

	private void createMap() {
		this.map = new char[size][size];

		// Fill with walls
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.map[i][j] = 'w';
			}
		}

		// Place hero at middle
//		hero.move(this.size / 2, this.size / 2);
//		Coordinate heroCoord = hero.getCoordinate();
		//draw paths
		carveMaze();
		//be sure that hero is well placed on the gameMap
//        this.gameMap[heroCoord.getY()][heroCoord.getX()] = 'h';

		// Place enemies on paths
//        generateEnemies();

	}

	private int nearestPowerOf2Below(int x) {
		if (x < 1) return 0;
		x |= (x >> 1);
		x |= (x >> 2);
		x |= (x >> 4);
		x |= (x >> 8);
		x |= (x >> 16);
		return (x + 1) >> 1;
	}

	//BSP for room generation
	//then still use BSP to link room with paths
	//then create some other random paths, assure that the player(middle of the gameMap) is on a ground tiles
	private void carveMaze() {
		int l = this.level;
		int nbRoom = l * (l + 3) + 1;
		int powerTwo = nearestPowerOf2Below(nbRoom);
		System.out.println("powerTwo: " + powerTwo + " size : " + this.size);

		BSPTree tree = new BSPTree(new Region(new Coordinate(1, 1), size - 2, size - 2));
		tree.recursiveSplit(Math.max(powerTwo / 2, 3), Math.max(size / 10, 2));
		nbRoom -= powerTwo;
		if (nbRoom > 0) {
			// Optional: create extra regions if needed
		}

		tree.recursiveCreateRoom();

		// --- DRAW ROOMS AND PATHS ---
		tree.traverse(node -> {
			if (node.isLeaf() && node.getRoom() != null) {
				Region room = node.getRoom();
				int Rx = room.topLeft().getX();
				int Ry = room.topLeft().getY();
				for (int i = Ry; i <= room.bottom(); i++) {
					for (int j = Rx; j <= room.right(); j++) {
						map[i][j] = 'g';
					}
				}
			} else if (!node.isLeaf() && !node.getPathPoints().isEmpty()) {
				List<Coordinate> pathPoints = node.getPathPoints();
				for (int i = 0; i < pathPoints.size() - 1; i++) {
					Coordinate p = pathPoints.get(i);
					Coordinate q = pathPoints.get(i + 1);

					if (p.getX() == q.getX()) {
						int start = Math.min(p.getY(), q.getY());
						int end = Math.max(p.getY(), q.getY());
						for (int j = start; j <= end; j++) {
							map[j][p.getX()] = 'g';
						}
					} else if (p.getY() == q.getY()) {
						int start = Math.min(p.getX(), q.getX());
						int end = Math.max(p.getX(), q.getX());
						for (int j = start; j <= end; j++) {
							map[p.getY()][j] = 'g';
						}
					}
				}
			}
		});

		// --- ENSURE CENTER IS GROUND ---
		int centerX = size / 2;
		int centerY = size / 2;
		if (map[centerY][centerX] != 'g') {
			System.out.println("Center is not ground, carving to nearest paths...");

			// Collect nearest ground tiles
			List<Coordinate> groundTiles = new ArrayList<>();
			for (int y = 1; y < size - 1; y++) {
				for (int x = 1; x < size - 1; x++) {
					if (map[y][x] == 'g') {
						groundTiles.add(new Coordinate(x, y));
					}
				}
			}

			// Sort by Manhattan distance to center
			groundTiles.sort((a, b) -> {
				int da = Math.abs(a.getX() - centerX) + Math.abs(a.getY() - centerY);
				int db = Math.abs(b.getX() - centerX) + Math.abs(b.getY() - centerY);
				return Integer.compare(da, db);
			});

			// Take up to two nearest ground tiles
			int numConnections = Math.min(2, groundTiles.size());
			for (int i = 0; i < numConnections; i++) {
				Coordinate target = groundTiles.get(i);
				carvePath(centerX, centerY, target.getX(), target.getY());
			}

			// Make sure center itself is carved
			map[centerY][centerX] = 'g';
		}

		// --- CREATE EXIT: RANDOM SIDE, NEAREST 'g' FROM BORDER ---
		List<String> sides = new ArrayList<>(Arrays.asList("TOP", "BOTTOM", "LEFT", "RIGHT"));
		boolean exitPlaced = false;

		while (!sides.isEmpty() && !exitPlaced) {
			String side = sides.remove(random.nextInt(sides.size()));
			List<Coordinate> candidates = new ArrayList<>();

			switch (side) {
				case "TOP": {
					for (int x = 1; x < size - 1; x++) {
						for (int y = 1; y < size - 1; y++) {
							if (map[y][x] == 'g') {
								// Found nearest ground from top border
								candidates.add(new Coordinate(x, y));
							}
							if (!candidates.isEmpty()) break;
						}
					}
					break;
				}
				case "BOTTOM": {
					for (int x = 1; x < size - 1; x++) {
						for (int y = size - 2; y > 0; y--) {
							if (map[y][x] == 'g') {
								candidates.add(new Coordinate(x, y));
							}
							if (!candidates.isEmpty()) break;
						}
					}
					break;
				}
				case "LEFT": {
					for (int y = 1; y < size - 1; y++) {
						for (int x = 1; x < size - 1; x++) {
							if (map[y][x] == 'g') {
								candidates.add(new Coordinate(x, y));
							}
							if (!candidates.isEmpty()) break;
						}
					}
					break;
				}
				case "RIGHT": {
					for (int y = 1; y < size - 1; y++) {
						for (int x = size - 2; x > 0; x--) {
							if (map[y][x] == 'g') {
								candidates.add(new Coordinate(x, y));
							}
							if (!candidates.isEmpty()) break;
						}
					}
					break;
				}
			}

			if (!candidates.isEmpty()) {
				// Pick one random candidate and carve path to border
				Coordinate exitTile = candidates.get(random.nextInt(candidates.size()));

				switch (side) {
					case "TOP":
						for (int y = 0; y <= exitTile.getY(); y++) map[y][exitTile.getX()] = 'g';
						break;
					case "BOTTOM":
						for (int y = size - 1; y >= exitTile.getY(); y--) map[y][exitTile.getX()] = 'g';
						break;
					case "LEFT":
						for (int x = 0; x <= exitTile.getX(); x++) map[exitTile.getY()][x] = 'g';
						break;
					case "RIGHT":
						for (int x = size - 1; x >= exitTile.getX(); x--) map[exitTile.getY()][x] = 'g';
						break;
				}

				System.out.println("Exit created on " + side + " at " + exitTile);
				exitPlaced = true;
			}
		}

		if (!exitPlaced) {
			System.out.println("⚠️ Warning: could not place exit!");
		}
	}


	private boolean inBounds(int x, int y) {
		return x > 0 && y > 0 && x < size - 1 && y < size - 1;
	}

	private void carvePath(int x1, int y1, int x2, int y2) {
		if (random.nextBoolean()) {
			// Horizontal then vertical
			for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) map[y1][x] = 'g';
			for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) map[y][x2] = 'g';
		} else {
			// Vertical then horizontal
			for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) map[y][x1] = 'g';
			for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) map[y2][x] = 'g';
		}
	}

	private void generateEnemies() {
		int l = this.level;
		int nbEnemies = (this.size * this.size) / 20; // fewer enemies for readability

		// Collect all walkable ground tiles
		List<Coordinate> groundTiles = new ArrayList<>();
		for (int i = 1; i < size - 1; i++) {
			for (int j = 1; j < size - 1; j++) {
				if (this.map[i][j] == 'g') {
					groundTiles.add(new Coordinate(j, i));
				}
			}
		}

		Collections.shuffle(groundTiles);
		for (int i = 0; i < nbEnemies && i < groundTiles.size(); i++) {
			Coordinate c = groundTiles.get(i);
			this.map[c.getY()][c.getX()] = 'e';
			this.enemies.add(EnemyFactory.randomCreation(l, c));
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMapLevel() {
		return level;
	}

	public void setHero(int level) {
		this.level = level;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public List<Coordinate> getGroundTiles(){
		List<Coordinate> coordinates = new ArrayList<>();
		int size = this.getSize();
		char[][] map = this.getMap();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 'g') {
					coordinates.add(new Coordinate(j, i));
				}
			}
		}
		return coordinates;
	}
}