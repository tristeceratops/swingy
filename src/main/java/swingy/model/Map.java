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
        createMap();
    }

    private int calculateSize() {
        int l = this.hero.getLevel();
        return (l - 1) * 5 + 10 - (l % 2);
    }

    private void createMap() {
        this.map = new char[size][size];

        // Fill with ground
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.map[i][j] = 'g';
            }
        }

        // Place hero at middle
        hero.move(this.size / 2, this.size / 2);
		Coordinate heroCoord = hero.getCoordinate();
        this.map[heroCoord.getY()][heroCoord.getX()] = 'h';

        // Place enemies
        generateEnemies();

		//draw wall
    }

	private void generateEnemies() {
		int l = this.hero.getLevel();
		int nbEnnemies = (this.size * this.size) / 4;
	}
}