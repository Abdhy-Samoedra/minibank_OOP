package History;

public class Debit extends History{
	public Debit(int amount,String accNumber) {
		super(amount,accNumber);
	}
	public void view() {
		System.out.println(amount);
		System.out.println(accNumber);
	}
}
