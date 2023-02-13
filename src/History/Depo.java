package History;

public class Depo extends History{
	public Depo(int amount,String accNumber) {
		super(amount,accNumber);
	}
	public void view() {
		System.out.println(amount);
		System.out.println(accNumber);
	}
}
