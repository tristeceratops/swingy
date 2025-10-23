package swingy.business.entities.enemies;

import swingy.game.Coordinate;

@FunctionalInterface
public interface EnemyCreator {
	Enemy create(String name, Coordinate coordinate, int level);
}
