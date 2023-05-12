import java.util.Arrays;
import java.util.Optional;

/* Holds an array of rate-to-person assumptions made during the solving stage. */
public class AssumptionStep {
	private Optional<Assumption>[] assumptions;

	@SuppressWarnings("unchecked")
	public AssumptionStep(int size) {
		this.assumptions = (Optional<Assumption>[]) new Optional[size];
		Arrays.fill(assumptions, 0, size - 1, Optional.empty());
	}

	@Override
	public String toString() {
		String res = "";
		for (Optional<Assumption> as : this.assumptions) {
			if (as.isEmpty()) {
				res += "empty ";
			} else {
				res = res.concat(String.format("(%s, %s)",
						as.get().person, as.get().rate) + " ");
			}
		}
		return res.trim();
	}

	/* Adds an entry to the assumption array */
	public void add(int idx, Person person, Rate rate) {
		this.assumptions[idx] = Optional.of(new Assumption(person, rate));
	}

	public int sum() {
		int sum = 0;
		for (Optional<Assumption> as : this.assumptions) {
			int pay = as.isPresent() ? as.get().rate.getPay() : 0;
			sum += pay;
		}
		return sum;
	}

	/* A rate-to-person pairing made during the solving step. */
	public static class Assumption {
		public Person person;
		public Rate rate;

		public Assumption(Person person, Rate rate) {
			this.person = person;
			this.rate = rate;
		}
	}
}
