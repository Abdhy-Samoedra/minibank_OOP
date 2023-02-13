package Account;

import java.time.LocalDate;

public class Deposito extends Account {
	int periode;
	double interest;
	LocalDate createDate;
	LocalDate dueDate;

	public Deposito(int amount, String PIN, int periode, double interest) {
		super(amount, PIN);
		this.accNumber = "1111-" + ((long) (Math.random() * (999999L - 100000L + 1)) + 100000L);
		this.periode = periode;
		this.interest = interest;
		this.createDate = LocalDate.now();
		this.dueDate = createDate.plusMonths(periode);
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public void view() {
		System.out.println(amount);
		System.out.println(accNumber);
	}

	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub

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
