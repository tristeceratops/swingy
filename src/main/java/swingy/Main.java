package swingy;

import swingy.model.Map;
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
        Map map = new Map(josianne);
        char[][] mapCharacters = map.getMap();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < mapCharacters.length; i++) {
            for (int j = 0; j < mapCharacters[i].length; j++) {
                sb.append(mapCharacters[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}