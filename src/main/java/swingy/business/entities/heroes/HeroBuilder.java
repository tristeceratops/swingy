package swingy.business.entities.heroes;

import java.util.UUID;

public interface HeroBuilder<T extends Hero, B extends HeroBuilder<T,B>> {

	B id(UUID id);
	B name(String name);
    B level(int level);
    B experience(int experience);
	B attack(int attack);
    B defence(int defence);
    B hitpoints(int hitpoints);
    T build();
}
