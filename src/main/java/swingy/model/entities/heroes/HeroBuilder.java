package swingy.model.entities.heroes;

public interface HeroBuilder<T extends Hero> {

	HeroBuilder<T> name(String name);
    HeroBuilder<T> level(int level);
    HeroBuilder<T> experience(int experience);
	HeroBuilder<T> attack(int attack);
    HeroBuilder<T> defence(int defence);
    HeroBuilder<T> hitpoints(int hitpoints);
    T build();
}
