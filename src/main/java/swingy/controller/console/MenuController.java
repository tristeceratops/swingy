package swingy.controller.console;

import swingy.view.console.MenuView;

public class MenuController {

	boolean running = true;
	MenuView view = new MenuView();

	public MenuController(){}

	//todo : add model for heroes/enemies, create view and controller for menu in terminal interface
	public void start(){
		while(running){
			//view show menu
			view.showMenu();
			String choice = view.getUserInput();
			switch (choice){
				case "1": System.out.println("Create new Hero"); break;
				case "2": System.out.println("Select Hero"); break;
				case "3": System.out.println("New game"); break;
				case "4": System.exit(0);
				default: break;
			}
		}
	}

}
