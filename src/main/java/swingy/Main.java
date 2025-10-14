package swingy;

import swingy.controller.console.MenuController;
import swingy.model.game.GameMap;
import swingy.model.entities.heroes.Hero;
import swingy.model.entities.heroes.HeroFactory;

public class Main
{
    public static void main( String[] args )
    {
//        Hero josianne = HeroFactory.create("Knight", "Josianne");
//        josianne.setLevel(15);
//        GameMap gameMap = new GameMap(josianne.getLevel());
//        char[][] mapCharacters = gameMap.getMap();
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < mapCharacters.length; i++) {
//			for (int j = 0; j < mapCharacters[i].length; j++) {
//                sb.append(mapCharacters[i][j]);
//            }
//            sb.append(" ").append(i).append("\n");
//        }
//        System.out.println(sb.toString());

        MenuController mc = new MenuController();
        mc.start();
    }
}