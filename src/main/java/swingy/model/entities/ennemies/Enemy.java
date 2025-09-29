package swingy.model.entities.ennemies;

import swingy.model.entities.Entity;

public class Enemy extends Entity {

	public Enemy(int level, int attack, int defense, int hitPoints){
		this.level = level;
		this.attack = calculateAttack(attack);
		this.defence = calculateDefense(defense);
		this.hitpoints = calculateHitPoints(hitPoints);
		this.maxHitpoints = this.hitpoints;
	}

	private int calculateAttack(int attack){
		int l = this.level;
		double p = (l <= 8) ? 0.9 : 1.1;
		return attack + (int)Math.pow(l - 1, p);
	}

	private int calculateDefense(int defense){
		int l = this.level;
		double p = (l <= 8) ? 0.9 : 1.1;
		return attack + (int)Math.pow(l - 1, p);
	}

	private int calculateHitPoints(int hitPoints){
		int l = this.level;
		double p = (l <= 8) ? 1.1 : 1.4;
		return attack + (int)Math.pow(l - 1, p);
	}
}
