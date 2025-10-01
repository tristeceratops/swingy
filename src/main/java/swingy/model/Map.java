package swingy.model;

import swingy.model.entities.ennemies.Enemy;
import swingy.model.entities.ennemies.EnemyCreator;
import swingy.model.entities.ennemies.EnemyFactory;
import swingy.model.entities.heroes.Hero;

import java.util.*;

public class Map {

    int size;
    Hero hero;
    List<Enemy> enemies;

    /*
     * map characters and their meanings:
     * g: ground, empty case
     * e: enemy
     * h: hero
     * w: wall, obstacle that hero or enemy can't pass through
     */
    char[][] map;

    public Map(Hero hero) {
        this.hero = hero;
        this.size = calculateSize();
        this.enemies = new ArrayList<>();
        createMap();
    }

    private int calculateSize() {
        int l = this.hero.getLevel();
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
        hero.move(this.size / 2, this.size / 2);
		Coordinate heroCoord = hero.getCoordinate();

        carveMaze(heroCoord.getX(), heroCoord.getY());

        this.map[heroCoord.getY()][heroCoord.getX()] = 'h';

        // Place enemies on paths
        generateEnemies();

    }

    private void carveMaze(int x, int y) {
        // Directions (N, S, E, W)
        int[][] dirs = { {0, -2}, {0, 2}, {-2, 0}, {2, 0} };
        Collections.shuffle(Arrays.asList(dirs)); // Random order for natural paths

        this.map[y][x] = 'g'; // Mark current as ground

        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (inBounds(nx, ny) && this.map[ny][nx] == 'w') {
                // Carve wall between (x,y) and (nx,ny)
                this.map[y + d[1] / 2][x + d[0] / 2] = 'g';
                carveMaze(nx, ny);
            }
        }
    }

    private boolean inBounds(int x, int y) {
        return x > 0 && y > 0 && x < size-1 && y < size-1;
    }

	private void generateEnemies() {
        int l = this.hero.getLevel();
        int nbEnemies = (this.size * this.size) / 20; // fewer enemies for readability

        // Collect all walkable ground tiles
        List<Coordinate> groundTiles = new ArrayList<>();
        for (int i = 1; i < size-1; i++) {
            for (int j = 1; j < size-1; j++) {
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

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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
}