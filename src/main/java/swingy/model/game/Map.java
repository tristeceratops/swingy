package swingy.model.game;

import swingy.model.entities.ennemies.Enemy;
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
        //draw paths
        carveMaze();
        //be sure that hero is well placed on the map
//        this.map[heroCoord.getY()][heroCoord.getX()] = 'h';

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
    //then create some other random paths, assure that the player(middle of the map) is on a ground tiles
    private void carveMaze() {
        int l = this.hero.getLevel();
        int nbRoom = l * (l + 3) + 1;
        int powerTwo = nearestPowerOf2Below(nbRoom);
        System.out.println("powerTwo: " + powerTwo + "size : " + this.size);
        BSPTree tree = new BSPTree(new Region(new Coordinate(1, 1), size - 2, size - 2));
        tree.recursiveSplit(Math.max(powerTwo / 2, 3), Math.max(size / 10, 2));
        nbRoom -= powerTwo;
        if (nbRoom > 0){
            //find a way to recreate random region
        }
        tree.recursiveCreateRoom();
        tree.traverse(node -> {
            if (node.isLeaf() && node.getRoom() != null) {
                Region room = node.getRoom();
                System.out.println("room is : " + room);
                int Rx = room.topLeft().getX();
                int Ry = room.topLeft().getY();
//                System.out.println("Rx: " + Rx + " Ry: " + Ry + " Size: " + size);
//                System.out.println("Right: " + region.right() + " bottom: " + region.bottom());
//                System.out.println("Region width: " + region.width() + " height: " + region.height());
                for (int i = Ry; i <= room.bottom(); i++) {
                    for (int j = Rx; j <= room.right(); j++) {
                        map[i][j] = 'g';
                    }
                }
            } else {
                if (!node.isLeaf()) {
                    System.out.println("Not leaf for region :" + node.getRegion());
                }
                else if (node.getRoom() == null){
                    System.out.println("Room is null for region :" + node.getRegion());
                }
            }
        });
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