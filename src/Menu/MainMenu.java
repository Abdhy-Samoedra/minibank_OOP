package Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Actors.Nasabah;

public class MainMenu {
	
	Login login = new Login();
	SignUp signUp = new SignUp();
	
	public void showMenu(ArrayList<Nasabah> nasabahArray){
		System.out.println("=====WELCOME====");
		System.out.println("====BANK ABC====");
		System.out.println("================");
		
		int input = 0;
		Scanner scan = new Scanner(System.in);
		String badInput = null;
		
		do {
			System.out.println("1. Login");
			System.out.println("2. Sign up");
			System.out.println("3. Exit");
			do {
				try {
					System.out.print(">> ");
					input = scan.nextInt();
					scan.nextLine();
					badInput = null;
				} catch (InputMismatchException e) {
					badInput = scan.next();
					System.out.println("Please input a number");
				}
			
			}while(badInput != null);
			switch (input) {
			case 1:
				login.showMenu(nasabahArray);
				break;
			case 2:
				signUp.showMenu(nasabahArray);
				break;

			default:
				break;
			}
		}while(input != 3);
	}
}
