/* A payment for a job position. */
public class Rate {
	private Job job;
	private int pay;

	public Rate(Job job, int pay) {
		this.job = job;
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "$" + this.pay;
	}

	public Job getJob() {
		return this.job;
	}

	public int getPay() {
		return this.pay;
	}
}
