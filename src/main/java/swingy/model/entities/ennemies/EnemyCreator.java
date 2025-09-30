package swingy.model.entities.ennemies;

import swingy.model.Coordinate;

@FunctionalInterface
public interface EnemyCreator {
	Enemy create(String name, Coordinate coordinate, int level);
}
