package swingy.model.game;

import swingy.model.entities.ennemies.Enemy;
import swingy.model.entities.ennemies.EnemyFactory;
import swingy.model.entities.heroes.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gameManager {

	GameMap gameMap;
	Hero hero;
	List<Enemy> enemies;

	public gameManager(){}

	public gameManager(Hero hero){
		this.hero = hero;
		this.gameMap = new GameMap(hero.getLevel());
		//generate enemies
		generateEnemies();
		//init hero position
		hero.move(gameMap.size / 2, gameMap.size / 2);
	}

	private void generateEnemies(){
		List<Coordinate> grounds = gameMap.getGroundTiles();
		int enemyCreated = grounds.size() / 8;
		//create and spawn random enemies on the map
		Collections.shuffle(grounds);
		List<Coordinate> enemiesPosition = grounds.stream().limit(enemyCreated).toList();
		for (Coordinate coordinate : enemiesPosition) {
			enemies.add(EnemyFactory.randomCreation(hero.getLevel(), coordinate));
		}
	}

}
