package Menu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import Account.Deposito;
import Account.Savings;
import Actors.Nasabah;
import History.Credit;
import History.Debit;
import History.Depo;

public class UserMenu {

	Scanner scan = new Scanner(System.in);

	public void showMenu(ArrayList<Nasabah> nasabahArray, int index) {

		long currMonth, createdMonth, beetwenMonth;
		LocalDate currDate;

		currDate = LocalDate.of(2023, 12, 10);
		currMonth = currDate.getMonthValue();
		for (int i = 0; i < nasabahArray.get(index).getSavings().size(); i++) {
			createdMonth = nasabahArray.get(index).getSavings().get(i).getCreateDate().getMonthValue();
			beetwenMonth = currMonth - createdMonth;
			double interest = beetwenMonth * 0.00208;
			int balance = nasabahArray.get(index).getSavings().get(i).getAmount();
			int currBalance = balance + (int) (balance * interest);
			nasabahArray.get(index).getSavings().get(i).setAmount(currBalance);	
		}


		System.out.println("=========================================");
		System.out.printf("Welcome %s%n", nasabahArray.get(index).getName());
		System.out.println("=========================================");
		int input = 0;
		String badInput = null;

		do {
			System.out.print("Date now : ");
			System.out.println(currDate);
			System.out.println("Choose what you want to do");
			System.out.println("1. Cash deposit");
			System.out.println("2. Cash withdrawals");
			System.out.println("3. Mutations checking");
			System.out.println("4. Deposito checking");
			System.out.println("5. Add savings products");
			System.out.println("6. Log out");
			do {
				try {
					System.out.print(">> ");
					input = scan.nextInt();
					scan.nextLine();
					badInput = null;
				} catch (InputMismatchException e) {
					badInput = scan.next();
					System.out.println("input must be a number");
				}
			} while (badInput != null);
			switch (input) {
				case 1:
					deposit(nasabahArray, index);
					break;
				case 2:
					withdrawals(nasabahArray, index);
					break;
				case 3:
					mutations(nasabahArray, index);
					break;
				case 4:
					deposito(nasabahArray, index);
					break;
				case 5:
					addSaving(nasabahArray, index);
					break;

				default:
					break;
			}

		} while (input != 6);
	}

	public int cekAccNumber(ArrayList<Nasabah> nasabahArray, int index, String AccNumber, String PIN) {
		int indexAcc = -1;
		for (int i = 0; i < nasabahArray.get(index).getSavings().size(); i++) {
			if (nasabahArray.get(index).getSavings().get(i).getAccNumber().equals(AccNumber)&& nasabahArray.get(index).getSavings().get(i).getPIN().equals(PIN)) {
				indexAcc = i;
			}
		}
		return indexAcc;
	}

	public int cekDepositoNumber(ArrayList<Nasabah> nasabahArray, int index, String AccNumber, String PIN) {
		int indexAcc = -1;
		for (int i = 0; i < nasabahArray.get(index).getDeposito().size(); i++) {
			if (nasabahArray.get(index).getDeposito().get(i).getAccNumber().equals(AccNumber)&& nasabahArray.get(index).getDeposito().get(i).getPIN().equals(PIN)) {
				indexAcc = i;
			}
		}
		return indexAcc;
	}

	public void deposit(ArrayList<Nasabah> nasabahArray, int index) {
		String accNumber, PIN;
		int amount;
		int indexAcc;

		do {
			System.out.print("Input your Account Number : ");
			accNumber = scan.nextLine();
			System.out.print("input your PIN : ");
			PIN = scan.nextLine();
			indexAcc = cekAccNumber(nasabahArray, index, accNumber, PIN);

			if (indexAcc < 0) {
				System.out.println("Wrong account number or PIN");
			}else{
				int currBalance = nasabahArray.get(index).getSavings().get(indexAcc).getAmount();
				System.out.printf("Balance : %d%n", currBalance);
				System.out.print("Input Amount : ");
				amount = scan.nextInt();
				scan.nextLine();
				nasabahArray.get(index).getSavings().get(indexAcc).setAmount(currBalance + amount);
				nasabahArray.get(index).getDebit().add(new Debit(amount, nasabahArray.get(index).getSavings().get(indexAcc).getAccNumber()));
			}
		} while (indexAcc < 0);
	}

	public void withdrawals(ArrayList<Nasabah> nasabahArray, int index) {
		String accNumber, PIN;
		int amount = 0;
		int indexAcc;
		int limit = 0;
		String badInput = null;
		int currBalance = 0;
		do {
			System.out.print("Input your Account Number : ");
			accNumber = scan.nextLine();
			System.out.println("input your PIN : ");
			PIN = scan.nextLine();
			indexAcc = cekAccNumber(nasabahArray, index, accNumber, PIN);

			if (indexAcc < 0) {
				System.out.println("Wrong account number or PIN");
			} else {
				currBalance = nasabahArray.get(index).getSavings().get(indexAcc).getAmount();
				for (int i = 0; i < nasabahArray.get(index).getCredit().size(); i++) {
					if (nasabahArray.get(index).getCredit().get(i).getAccNumber().equals(accNumber)) {
						limit += nasabahArray.get(index).getCredit().get(i).getLimit();
					}
				}

				System.out.println("Daily Limit 10.000.000");
				System.out.printf("Your remaining limit",10000000-limit);
	
				if (nasabahArray.get(index).getCredit().size() == 0) {
					System.out.printf("Balance : %d%n", currBalance);
					do {
						System.out.print("Input Amount : ");
						do {
							try {
								amount = scan.nextInt();
								scan.nextLine();
								badInput = null;
							} catch (InputMismatchException e) {
								badInput = scan.next();
								System.out.println("input must be a number");
							}
						} while (badInput != null);
					} while (amount > currBalance || amount > 10000000);
				} else {
					if (limit < 10000000) {
						System.out.printf("Balance : %d%n", currBalance);
						do {
							System.out.print("Input Amount : ");
							do {
								try {
									amount = scan.nextInt();
									scan.nextLine();
									badInput = null;
								} catch (InputMismatchException e) {
									badInput = scan.next();
									System.out.println("input must be a number");
								}
								} while (badInput != null);
						} while (amount > currBalance || amount > 10000000 || amount + limit > 10000000);
					} else {
						System.out.println("You have reached the daily limit");
					}
				}
			}
		} while (indexAcc < 0);
		
		nasabahArray.get(index).getCredit().add(new Credit(amount, nasabahArray.get(index).getSavings().get(indexAcc).getAccNumber(), amount));
		nasabahArray.get(index).getSavings().get(indexAcc).setAmount(currBalance - amount);
	}

	public void mutations(ArrayList<Nasabah> nasabahArray, int index) {
		System.out.println("Debit");
		for (int i = 0; i < nasabahArray.get(index).getDebit().size(); i++) {
			String accNumber = nasabahArray.get(index).getDebit().get(i).getAccNumber();
			int amount = nasabahArray.get(index).getDebit().get(i).getAmount();
			System.out.printf("%s [%d]%n", accNumber, amount);
		}
		System.out.println("Credit");
		for (int i = 0; i < nasabahArray.get(index).getCredit().size(); i++) {
			String accNumber = nasabahArray.get(index).getCredit().get(i).getAccNumber();
			int amount = nasabahArray.get(index).getCredit().get(i).getAmount();
			System.out.printf("%s [%d]%n", accNumber, amount);
		}
		System.out.println("Deposito");
		for (int i = 0; i < nasabahArray.get(index).getDepo().size(); i++) {
			String accNumber = nasabahArray.get(index).getDepo().get(i).getAccNumber();
			int amount = nasabahArray.get(index).getDepo().get(i).getAmount();
			System.out.printf("%s [%d]%n", accNumber, amount);
		}
	}

	public void addSaving(ArrayList<Nasabah> nasabahArray, int index) {
		int cek = 0;
		int input = 0;
		String PIN;
		int firstDebit = 0;
		String badInput = null;
		do {
			if (cek == 0) {
				System.out.println("Choose the type of savings");
				System.out.println("1. Savings");
				System.out.println("2. Deposito");
				System.out.println("3. Exit");
				System.out.print(">> ");
				do {
					try {
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

					nasabahArray.get(index).getSavings().add(new Savings(firstDebit, PIN));
					System.out.println(nasabahArray.size());
					System.out.println(nasabahArray.get(index).getSavings().size());
					System.out.println("======================================================");
					System.out.printf("Your Account Number %s%n", nasabahArray.get(index).getSavings().get(nasabahArray.get(index).getSavings().size() - 1).getAccNumber());
					System.out.print("Create at :");
					System.out.println(nasabahArray.get(index).getSavings().get(nasabahArray.get(index).getSavings().size() - 1).getCreateDate());
					System.out.println("======================================================");
					nasabahArray.get(index).getDebit().add(new Debit(firstDebit, nasabahArray.get(index).getSavings().get(nasabahArray.get(index).getSavings().size() - 1).getAccNumber()));
					cek = 1;
					break;
				case 2:
					int inputPeriod = 0;
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
							nasabahArray.get(index).getDeposito().add(new Deposito(firstDebit, PIN, 3, 0.0025));
							System.out.println("======================================================");
							System.out.printf("Your Account Number %s%n", nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber());
							System.out.print("Create at :");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getCreateDate());
							System.out.print("Deposit maturity at : ");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getDueDate());
							System.out.println("======================================================");
							nasabahArray.get(index).getDepo().add(new Depo(firstDebit, nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber()));
							break;
						case 2:
							nasabahArray.get(index).getDeposito().add(new Deposito(firstDebit, PIN, 6, 0.0027));
							System.out.printf("Your Account Number %s%n", nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber());
							System.out.print("Create at :");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getCreateDate());
							System.out.print("Deposit maturity at : ");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getDueDate());
							nasabahArray.get(index).getDepo().add(new Depo(firstDebit, nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber()));
							break;
						case 3:
							nasabahArray.get(index).getDeposito().add(new Deposito(firstDebit, PIN, 12, 0.0029));
							System.out.printf("Your Account Number %s%n", nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber());
							System.out.print("Create at :");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getCreateDate());
							System.out.print("Deposit maturity at : ");
							System.out.println(nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getDueDate());
							nasabahArray.get(index).getDepo().add(new Depo(firstDebit, nasabahArray.get(index).getDeposito().get(nasabahArray.get(index).getDeposito().size() - 1).getAccNumber()));
							break;

							default:
							System.out.println("Please input a number between 1 - 3");
							break;
						}
						cek = 1;
						break;
					} while (inputPeriod < 1 || inputPeriod > 3);
				} 
			}while (input != 3);
			System.out.println("====Account registration has been successful=====");
			System.out.println("======================================================");
		}


			public void deposito(ArrayList<Nasabah> nasabahArray, int index) {
				String accNumber, PIN;
				int input = 0;
				int indexAcc;
				String badInput = null;
				do {
					System.out.print("input your Account Number : ");
					accNumber = scan.nextLine();
					System.out.print("input your PIN : ");
					PIN = scan.nextLine();
					indexAcc = cekDepositoNumber(nasabahArray, index, accNumber, PIN);
					if (indexAcc < 0) {
						System.out.println("Wrong account number or PIN ");
					}else{
						LocalDate createDate = nasabahArray.get(index).getDeposito().get(indexAcc).getCreateDate();
						LocalDate maturityDate = nasabahArray.get(index).getDeposito().get(indexAcc).getDueDate();
						LocalDate currDate;

						long beetwenDate;
						int periode = nasabahArray.get(index).getDeposito().get(indexAcc).getPeriode();
						double interest = nasabahArray.get(index).getDeposito().get(indexAcc).getInterest();
						int amount = nasabahArray.get(index).getDeposito().get(indexAcc).getAmount();
						int charge = 50000;
						double penalty = amount * 0.01;

						currDate = LocalDate.of(2023, 12, 10);
						beetwenDate = ChronoUnit.DAYS.between(maturityDate, currDate);

						System.out.println("======================================================");
						System.out.printf("Your Deposito amount [%d]%n", amount);
						System.out.print("Created at : ");
						System.out.println(createDate);
						System.out.printf("Periode : %d%n", periode);
						System.out.print("Maturity date : ");
						System.out.println(maturityDate);
						System.out.println("======================================================");
				
						int choose = 0;
						String destAccount;
						do {
							
							System.out.println("Choose Action");
							System.out.println("1. Withdrawls");
							System.out.println("2. exit");
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
									if (beetwenDate < 0) {
										int penaltyDeposito = amount - ( (int) penalty + charge);
										System.out.println("======================================================");
										System.out.println("You take the money before the deposit matures!");
										System.out.printf("Your Deposito amount [%d]%n", amount);
										System.out.printf("Penalty : %d%n",(int)penalty);
										System.out.printf("Charge : %d%n", charge);
										System.out.printf("Your final deposito : %d%n", penaltyDeposito);
										System.out.println("======================================================");
										int cek = 0;

										do {
											if (cek == 0) {
												System.out.println("Are you sure to withdraw the money?");
												System.out.println("1. yes");
												System.out.println("2. no");
												do {
													try {
														System.out.print(">> ");
														choose = scan.nextInt();
														scan.nextLine();
														badInput = null;
													} catch (InputMismatchException e) {
														badInput = scan.next();
														System.out.println("Please input a number");
													}
												
												}while(badInput != null);
											}else {
												choose = 2;
											}
											
											switch (choose) {
												case 1:
													System.out.print("Enter the destination account : ");
													destAccount = scan.nextLine();
													for (int i = 0; i < nasabahArray.size(); i++) {
														for (int j = 0; j < nasabahArray.get(i).getSavings().size(); j++) {
															if (nasabahArray.get(i).getSavings().get(j).getAccNumber().equals(destAccount)) {
																int currbalance = nasabahArray.get(i).getSavings().get(j).getAmount();
																nasabahArray.get(i).getSavings().get(j).setAmount(currbalance + penaltyDeposito);
																nasabahArray.get(i).getDebit().add(new Debit(penaltyDeposito, nasabahArray.get(i).getSavings().get(j).getAccNumber()));
															}
														}
													}
													cek = 1;
													break;
			
												default:
													break;
											}
										} while (choose != 2);
										nasabahArray.get(index).getCredit().add(new Credit(penaltyDeposito,nasabahArray.get(index).getDeposito().get(indexAcc).getAccNumber(), penaltyDeposito));
										nasabahArray.get(index).getDeposito().get(indexAcc).setAmount(0);
			
									} else {
										int finalDeposito = amount + (int)(amount * (periode * interest));
										System.out.println("======================================================");
										System.out.println("Your deposito ready to withdrawals");
										System.out.printf("Your Deposito amount [%d]%n", amount);
										System.out.printf("Your interest : %d%n", (int)(amount * (periode *  interest)));
										System.out.printf("Your final deposito : %d%n", finalDeposito);
										System.out.println("======================================================");
										int cek1 = 0;
										do {
											if (cek1 == 0) {
												System.out.println("Are you sure to withdraw the money?");
												System.out.println("1. yes");
												System.out.println("2. no");
												do {
													try {
														System.out.print(">> ");
														choose = scan.nextInt();
														scan.nextLine();
														badInput = null;
													} catch (InputMismatchException e) {
														badInput = scan.next();
														System.out.println("Please input a number");
													}
												
												}while(badInput != null);
											}else {
												choose = 2;
											}
											
											switch (choose) {
												case 1:
													System.out.print("Enter the destination account : ");
													destAccount = scan.nextLine();
													for (int i = 0; i < nasabahArray.size(); i++) {
														for (int j = 0; j < nasabahArray.get(i).getSavings().size(); j++) {
															if (nasabahArray.get(i).getSavings().get(j).getAccNumber().equals(destAccount)) {
																int currbalance = nasabahArray.get(i).getSavings().get(j).getAmount();
																nasabahArray.get(i).getSavings().get(j).setAmount(currbalance + finalDeposito);
																nasabahArray.get(i).getDebit().add(new Debit(finalDeposito, nasabahArray.get(i).getSavings().get(j).getAccNumber()));
															}
														}
													}
													cek1 = 1;
													break;
			
												default:
													break;
											}
										} while (choose != 2);
										nasabahArray.get(index).getCredit().add(new Credit(finalDeposito,nasabahArray.get(index).getDeposito().get(indexAcc).getAccNumber(), finalDeposito));
										nasabahArray.get(index).getDeposito().get(indexAcc).setAmount(0);
				
										}
										break;
				
									default:
										break;
								}
							} while (input != 2);
					}
			
				} while (indexAcc < 0);
			}
}
