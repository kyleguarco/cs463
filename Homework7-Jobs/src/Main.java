import java.util.Optional;

public class Main {
	public static void main(String... args) {
		// Rates for every job. In the lecture slides,
		// this is a column inside a square matrix.
		final int[][] COSTS = {
				{ 1, 6, 5, 8 },
				{ 2, 2, 7, 6 },
				{ 7, 6, 3, 9 },
				{ 5, 7, 8, 4 },
		};

		// Safety: All the arrays must be the same length.
		// The length must be the same as the `people` array.
		Job[] jobs = {
				Job.fromRates("1", COSTS[0]),
				Job.fromRates("2", COSTS[1]),
				Job.fromRates("3", COSTS[2]),
				Job.fromRates("4", COSTS[3]),
		};

		Person[] people = {
				new Person("A"), new Person("B"),
				new Person("C"), new Person("D")
		};

		// Begin solving!
		AssumptionStep lowest = solveStep(jobs, people);
		System.out.println("Lowest bound: " + lowest + " = " + lowest.sum());

		for (Job job : jobs) {
			AssumptionStep min = solveStep(jobs, people);

			// Minimums
			Optional<Person> mper = Optional.empty();
			Optional<Rate> mrate = Optional.empty();

			for (int peri = 0; peri < people.length; peri++) {
				Person person = people[peri];
				Rate rate = job.getRates()[peri];

				if (person.isEmployed()) {
					continue;
				}

				job.hire(person, rate);

				AssumptionStep curr = solveStep(jobs, people);
				System.out.println("Possible bound for " + job + ": " + curr + " = " + curr.sum());
				if (mrate.isEmpty() || curr.sum() < min.sum()) {
					mper = Optional.of(person);
					mrate = Optional.of(rate);
					min = curr;
				}
			}

			// Permanently fix this job for the rest of the problem.
			job.hire(mper.get(), mrate.get());
			System.out.println("Lowest bound for " + job + ": " + min + " = " + min.sum());
		}
	}

	/*
	 * This computes the lowest bound at each step of the problem.
	 *
	 * Safety: It is assumed that the length of the rows of `jobs`
	 * is the same as the length of `people`.
	 */
	static AssumptionStep solveStep(final Job[] jobs, final Person[] people) {
		AssumptionStep assumptions = new AssumptionStep(people.length);

		for (int jobi = 0; jobi < jobs.length; jobi++) {
			Job job = jobs[jobi];

			if (jobs[jobi].isAssigned()) {
				Person person = job.getAssignedPerson().get();
				assumptions.add(jobi, person, person.getPosition().get());
				continue;
			}

			// Minimums
			Optional<Person> mper = Optional.empty();
			Optional<Rate> mrate = Optional.empty();

			for (int peri = 0; peri < people.length; peri++) {
				// Again, we assume that `people` and the job rates array are
				// the same size. Oops if not, I guess.
				Person person = people[peri];
				Rate rate = job.getRates()[peri];

				// Skip this row if the person tied to this rate is employed.
				if (person.isEmployed()) {
					continue;
				}

				// Compare known minimum rate to current rate
				if (mrate.isEmpty() || rate.getPay() < mrate.get().getPay()) {
					mper = Optional.of(person);
					mrate = Optional.of(rate);
				}
			}

			// Affirm this assumption for the rest of this problem step.
			assumptions.add(jobi, mper.get(), mrate.get());
		}

		return assumptions;
	}
}
