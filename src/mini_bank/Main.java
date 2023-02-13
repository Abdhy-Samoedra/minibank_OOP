package mini_bank;

import java.util.ArrayList;

import Actors.Nasabah;
import Menu.MainMenu;

public class Main {
	private ArrayList<Nasabah> nasabahArray;
	public static void main(String[] args) {
		new Main().runProgram();
	}
	
	private Main() {
		nasabahArray = new ArrayList<>();
	}
	private void runProgram() {
		new MainMenu().showMenu(nasabahArray);
	}

	

}
