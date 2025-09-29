package swingy;

import swingy.model.entities.ennemies.Enemy;
import swingy.model.entities.ennemies.EnemyFactory;
import swingy.model.entities.heroes.Hero;
import swingy.model.entities.heroes.HeroFactory;
import swingy.model.entities.heroes.Knight;

public class Main
{
    public static void main( String[] args )
    {
        Hero josianne = HeroFactory.create("Knight", "Josianne");
        System.out.println(josianne);
        Enemy slime = EnemyFactory.create("Slime", "slimy");
        System.out.println(josianne);

    }
}