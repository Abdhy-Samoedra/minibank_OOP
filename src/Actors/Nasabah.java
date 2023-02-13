package Actors;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Account.Deposito;
import Account.Savings;
import History.Credit;
import History.Debit;
import History.Depo;

public class Nasabah {

	private ArrayList<Deposito> deposito;
	private ArrayList<Savings> savings;
	private ArrayList<Debit> debit;
	private ArrayList<Credit> credit;
	private ArrayList<Depo> depo;
	private String name;
	private String password;

	public Nasabah(String name, String password) {
		this.deposito = new ArrayList<>();
		this.savings = new ArrayList<>();
		this.debit = new ArrayList<>();
		this.credit = new ArrayList<>();
		this.depo = new ArrayList<>();
		this.name = name;
		this.password = password;
	}

	public Nasabah() {

	}

	public ArrayList<Debit> getDebit() {
		return debit;
	}

	public void setDebit(ArrayList<Debit> debit) {
		this.debit = debit;
	}

	public ArrayList<Credit> getCredit() {
		return credit;
	}

	public void setCredit(ArrayList<Credit> credit) {
		this.credit = credit;
	}

	public ArrayList<Depo> getDepo() {
		return depo;
	}

	public void setDepo(ArrayList<Depo> depo) {
		this.depo = depo;
	}

	public ArrayList<Deposito> getDeposito() {
		return deposito;
	}

	public void setDeposito(ArrayList<Deposito> deposito) {
		this.deposito = deposito;
	}

	public ArrayList<Savings> getSavings() {
		return savings;
	}

	public void setSavings(ArrayList<Savings> savings) {
		this.savings = savings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addNasabahSavings() {

	}

	public void addNasabahDeposito() {

	}

}
