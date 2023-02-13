package Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Actors.Nasabah;

public class Login {
	
	Scanner scan = new Scanner(System.in);
	public int check(ArrayList<Nasabah> nasabahArray,String username, String password) {
		int index = -1;
		for(int i = 0; i<nasabahArray.size();i++) {
			if (nasabahArray.get(i).getName().equals(username) && nasabahArray.get(i).getPassword().equals(password)) {
				index = i;
			}
		}
		return index;
	}
	
	public void showMenu(ArrayList<Nasabah> nasabahArray) {
		System.out.println("====NASABAH LOGIN=====");
		int index;

		do {
			System.out.print("Username : ");
			String username = scan.nextLine();
			System.out.print("Password : ");
			String password = scan.nextLine();
			index = check(nasabahArray,username, password);
			if (index < 0) {
				System.out.println("Wrong username or password");
			}else{
				System.out.println("=====Login Succes=====");
				new UserMenu().showMenu(nasabahArray,index);
			}
		} while (index < 0);
	}

}
