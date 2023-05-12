import java.util.Optional;

public class Job {
	public static Job fromRates(String title, int[] costs) {
		Job job = new Job(title, costs.length);
		for (int i = 0; i < costs.length; i++)
			job.rates[i] = new Rate(job, costs[i]);
		return job;
	}

	private String title;
	private Optional<Person> assigned;
	private Rate[] rates;

	private Job(String title, int nums) {
		this.title = title;
		this.assigned = Optional.empty();
		this.rates = new Rate[nums];
	}

	private Job(String title) {
		this(title, 0);
	}

	@Override
	public String toString() {
		return "Job " + this.title;
	}

	/*
	 * Hires a new person to do this job. Fires the previous
	 * position holder before applying the new hire.
	 */
	public Optional<Person> hire(Person person, Rate pos) {
		Optional<Person> oldHire = this.fire();

		person.payAt(pos);
		this.assigned = Optional.of(person);

		return oldHire;
	}

	/*
	 * Fires the currently assigned person doing this job,
	 * and sets their pay to nothing.
	 */
	public Optional<Person> fire() {
		Optional<Person> person = this.assigned;

		if (person.isPresent()) {
			person.get().stopPaying();
		}
		this.assigned = Optional.empty();

		return person;
	}

	public boolean isAssigned() {
		return this.assigned.isPresent();
	}

	public Optional<Person> getAssignedPerson() {
		return this.assigned;
	}

	public Rate[] getRates() {
		return this.rates;
	}
}
