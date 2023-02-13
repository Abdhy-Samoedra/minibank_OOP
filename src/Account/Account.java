package Account;

public abstract class Account {
	int amount;
	String PIN;
	String accNumber;

	public Account(int amount, String PIN) {
		this.amount = amount;
		this.PIN = PIN;
	}

	abstract public void setAmount(int amount);

	abstract public void setPIN(String PIN);

	abstract void setAccNumber(String accNumber);

	public int getAmount() {
		return amount;
	}

	public String getPIN() {
		return PIN;
	}

	public String getAccNumber() {
		return accNumber;
	}

}
