package History;

public class Credit extends History{
	int limit;
	public Credit(int amount,String accNumber,int limit) {
		super(amount,accNumber);
		this.limit = limit;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
