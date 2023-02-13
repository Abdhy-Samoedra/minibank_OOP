package Menu;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Scanner;

import Account.Deposito;
import Account.Savings;
import Actors.Nasabah;
import History.Debit;
import History.Depo;
import History.History;

public class SignUp {

	Scanner scan = new Scanner(System.in);
	int input;
	String PIN;
	String accNumber;
	int firstDebit = 0;
	String password, name;

	public void showMenu(ArrayList<Nasabah> nasabahArray) {
		do {
			System.out.println("====NASABAH SIGNUP====");
			System.out.print("Name : ");
			name = scan.nextLine();
			if (name.length() < 5) {
				System.out.println("name at least must be 5 characthers");
			}
		} while (name.length() < 5);
		do {
			System.out.print("Password [6 characthers]: ");
			password = scan.nextLine();
			if (!password.matches("\\d{6}")) {
				System.out.println("password must be a number 6 characther");
			}
		} while (!password.matches("\\d{6}"));

		Nasabah nasabah = new Nasabah(name, password);
		nasabahArray.add(nasabah);

		String badInput = null;
		int cek = 0;
		do {

			if (cek == 0) {

				System.out.println("Choose the type of savings");
				System.out.println("1. Savings");
				System.out.println("2. Deposito");
				System.out.println("3. Exit");
				do {
					try {
						System.out.print(">> ");
						input = scan.nextInt();
						scan.nextLine();
						badInput = null;
					} catch (Exception e) {
						badInput = scan.next();
						System.out.println("input must be number");
					}
				} while (badInput != null);

			} else {
				input = 3;
			}

			switch (input) {

			case 1:
				do {
					System.out.print("Create PIN [6 characthers]: ");
					PIN = scan.nextLine();
					if (!PIN.matches("\\d{6}")) {
						System.out.println("PIN must be number and 6 characthers");
					}
				} while (!PIN.matches("\\d{6}"));

				do {
					try {
						System.out.print("Enter your first debit amount : ");
						firstDebit = scan.nextInt();
						scan.nextLine();
						badInput = null;
					} catch (Exception e) {
						badInput = scan.next();
						System.out.println("input must be a number");
					}
				} while (badInput != null);

				nasabah.getSavings().add(new Savings(firstDebit, PIN));
				System.out.println("======================================================");
				System.out.printf("Your Account Number %s%n",nasabah.getSavings().get(nasabah.getSavings().size() - 1).getAccNumber());
				System.out.print("Created at : ");
				System.out.println(nasabah.getSavings().get(nasabah.getSavings().size() - 1).getCreateDate());
				System.out.println("======================================================");

				nasabah.getDebit().add(new Debit(firstDebit,nasabah.getSavings().get(nasabah.getSavings().size() - 1).getAccNumber()));
				
				cek = 1;
				break;
			case 2:
				do {
					System.out.print("Create PIN [6 characthers]: ");
					PIN = scan.nextLine();
					if (!PIN.matches("\\d{6}")) {
						System.out.println("input must be number 6 characthers");
					}

				} while (!PIN.matches("\\d{6}"));
				do {
					try {
						System.out.print("Enter your deposito amount : ");
						firstDebit = scan.nextInt();
						scan.nextLine();
						badInput = null;
					} catch (Exception e) {
						badInput = scan.next();
						System.out.println("input must be a number");
					}
				
				} while (badInput != null);
				int inputPeriod = 0;
				do {
					
					System.out.println("Choose the depostio period");
					System.out.println("1. 3 month [3%/year]");
					System.out.println("2. 6 month [3.25%/year]");
					System.out.println("3. 12 month [3.5%/year]");
					
					do {
						try {
							System.out.print(">> ");
							inputPeriod = scan.nextInt();
							scan.nextLine();
							badInput = null;
						} catch (Exception e) {
							badInput = scan.next();
							System.out.println("input must be number");
						}
					} while (badInput != null);
					switch (inputPeriod) {
					case 1:
						nasabah.getDeposito().add(new Deposito(firstDebit, PIN,3,0.0025));
						nasabah.getDepo().add(new Depo(firstDebit,nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getAccNumber()));
						break;
					case 2:
						nasabah.getDeposito().add(new Deposito(firstDebit, PIN,6,0.0027));
						nasabah.getDepo().add(new Depo(firstDebit,nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getAccNumber()));
						break;
					case 3:
						nasabah.getDeposito().add(new Deposito(firstDebit, PIN,12,0.0029));
						nasabah.getDepo().add(new Depo(firstDebit,nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getAccNumber()));
						break;
	
					default:
						System.out.println("Please input a number between 1 - 3");
						break;
					}
				} while (inputPeriod < 1 || inputPeriod > 3);
				System.out.println("======================================================");
				System.out.printf("Your Account Number %s%n",nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getAccNumber());
				System.out.print("Created at : ");
				System.out.println(nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getCreateDate());
				System.out.print("Deposit maturity at : ");
				System.out.println(nasabah.getDeposito().get(nasabah.getDeposito().size() - 1).getDueDate());
				System.out.println("======================================================");
				
				cek = 1;
				break;

			default:
				break;
			}
		} while (input != 3);
		System.out.println("====Account registration has been successful=====");
		System.out.println("======================================================");

	}
}
