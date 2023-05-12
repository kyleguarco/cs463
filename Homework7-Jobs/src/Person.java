import java.util.Optional;

public class Person {
	private String name;
	/* The payment for performing a job. Empty if unemployed. */
	private Optional<Rate> rate;

	public Person(String name, Rate pos) {
		this.name = name;
		this.rate = Optional.of(pos);
	}

	public Person(String name) {
		this.name = name;
		this.rate = Optional.empty();
	}

	@Override
	public String toString() {
		return "Person " + this.name;
	}

	/*
	 * Start paying this person at the specified rate.
	 * Automatically assigns a job.
	 */
	public Optional<Rate> payAt(Rate pos) {
		Optional<Rate> oldPay = this.rate;
		this.rate = Optional.of(pos);
		return oldPay;
	}

	/* Unemploys this person. */
	public Optional<Rate> stopPaying() {
		Optional<Rate> oldPay = this.rate;
		this.rate = Optional.empty();
		return oldPay;
	}

	public Optional<Rate> getPosition() {
		return this.rate;
	}

	public boolean isEmployed() {
		return this.rate.isPresent();
	}
}
