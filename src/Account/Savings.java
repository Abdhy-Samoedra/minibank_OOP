package Account;

import java.time.LocalDate;

public class Savings extends Account{
	LocalDate createDate;
	public Savings(int amount, String PIN) {
		super(amount,PIN);
		this.accNumber = "9999-" + ((long) (Math.random() * (999999L - 100000L + 1)) + 100000L);
		this.createDate = LocalDate.now();
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public void addSavings() {
		
	}
	public void view() {
		System.out.println(amount);
		System.out.println(accNumber);
	}
	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
		
	}
	@Override
	public void setPIN(String PIN) {
		// TODO Auto-generated method stub
		
	}
	@Override
	void setAccNumber(String accNumber) {
		// TODO Auto-generated method stub
		
	}
}
