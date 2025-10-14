package swingy.model.entities.heroes;

public interface HeroBuilder<T extends Hero> {
	HeroBuilder<T> name(String name);
    HeroBuilder<T> level(int level);
    HeroBuilder<T> experience(int experience);
    T build();
}
