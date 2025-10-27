package swingy.business.entities.heroes;

import java.util.UUID;

public abstract class AbstractHeroBuilder<
        T extends Hero,
        B extends AbstractHeroBuilder<T, B>> implements HeroBuilder<T, B> {

    protected UUID id = null;
    protected String name = "";
    protected int level = 1;
    protected int experience = 0;
    protected int attack = 0;
    protected int defence = 0;
    protected int hitpoints = 0;

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    @Override
    public B id(UUID id) {
        this.id = id;
        return self();
    }

    @Override
    public B name(String name) {
        this.name = name;
        return self();
    }

    @Override
    public B level(int level) {
        this.level = level;
        return self();
    }

    @Override
    public B experience(int experience) {
        this.experience = experience;
        return self();
    }

    @Override
    public B attack(int attack) {
        this.attack = attack;
        return self();
    }

    @Override
    public B defence(int defence) {
        this.defence = defence;
        return self();
    }

    @Override
    public B hitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
        return self();
    }

    @Override
    public abstract T build();
}

