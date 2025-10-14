package swingy.view.console;

import java.util.Scanner;

public class MenuView {

	private Scanner sc = new Scanner(System.in);

	public MenuView(){}

	public void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.println("Clear done");
		System.out.flush();
	}

	public void showMenu(){
		clearScreen();
		System.out.println("Menu");
		System.out.println("[1]: Create new Hero");
		System.out.println("[2]: Select Hero");
		System.out.println("[3]: New game");
		System.out.println("[4]: Exit");
		System.out.println("[0]: help");
	}

	public String getUserInput(){
		return sc.nextLine();
	}

}
