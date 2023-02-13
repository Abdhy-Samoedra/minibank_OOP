package History;

public abstract class History {
	int amount;
	String accNumber;

	public History(int amount,String accNumber) {
		this.amount = amount;
		this.accNumber = accNumber;
	}
	public void view() {
		System.out.println(amount);
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	
}
