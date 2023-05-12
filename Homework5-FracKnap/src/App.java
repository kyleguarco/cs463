import java.util.ArrayList;

public class App {
	public static void main(String... args) {
		// This solves the fractional knapsack problem presented in the
		// lecture slideshow. Week 10, "Greedy Algorithms" slide 11
		Knapsack sack = Knapsack.builder()
				.insert(5, 1)
				.insert(10, 3)
				.insert(15, 5)
				.insert(7, 4)
				.insert(8, 1)
				.insert(9, 3)
				.insert(4, 2)
				.construct();
		System.out.println("Maximum value: " +
				sack.fractionalKnapsack(15));
		System.out.println(sack);
	}

	/**
	 * Represents an item inside a knapsack.
	 * The implementation of Comparable<Item> allows one to use
	 * comparator operators on Item object instances (using density()).
	 */
	static class Item implements Comparable<Item> {
		/** The worth of an item. */
		public int value;
		/** The cost of putting an item inside a knapsack. */
		public int weight;

		// With public members like these, Java 19's records are also
		// another way of writing this class.
		public Item(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}

		/** Returns the profit-over-weight ratio of this item. */
		public double density() {
			return (double) this.value / this.weight;
		}

		/**
		 * Takes a fraction of the value based on remaining capacity. The value
		 * of the item is returned if fromCapacity is larger than the item weight.
		 */
		public double fraction(int fromCapacity) {
			if (fromCapacity > this.weight)
				return this.value;

			return this.value * (fromCapacity / this.weight);
		}

		/** Compare the densities between items. */
		@Override
		public int compareTo(Item item) {
			return ((Double) this.density()).compareTo(item.density());
		}

		@Override
		public String toString() {
			return String.format("(%d, %d, %.2f)", this.value, this.weight, this.density());
		}
	}

	static class Knapsack {
		public static KnapsackBuilder builder() {
			return new Knapsack.KnapsackBuilder();
		}

		private ArrayList<Item> items;

		public Knapsack() {
			this.items = new ArrayList<>();
		}

		/** Adds an item to the knapsack. The items are sorted in ascending order. */
		public void insertItem(int value, int weight) {
			this.items.add(new Item(value, weight));
		}

		/**
		 * Solves the fractional knapsack problem. Solved in O(N*logN).
		 * The item list is sorted in-place.
		 *
		 * @return The maximum value of the bag
		 */
		public double fractionalKnapsack(int capacity) {
			// This sorts the items in descending order by density.
			// The sort used by the standard library is an O(N*logN) mergesort.
			// See
			// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#sort(java.util.Comparator)
			this.items.sort((item1, item2) -> item2.compareTo(item1));

			double maxValue = 0d;
			for (Item item : this.items) {
				if (item.weight > capacity) {
					maxValue += item.fraction(capacity);
					break;
				} else {
					maxValue += item.value;
				}

				capacity -= item.weight;
			}

			return maxValue;
		}

		@Override
		public String toString() {
			String res = "[ ";
			for (Item item : this.items)
				res += item.toString();
			res += " ]";
			return res;
		}

		/** An easy, declarative way of constructing a knapsack. */
		static class KnapsackBuilder {
			private Knapsack sack;

			private KnapsackBuilder() {
				this.sack = new Knapsack();
			}

			/** Adds an item to the knapsack. */
			public KnapsackBuilder insert(int value, int weight) {
				this.sack.insertItem(value, weight);
				return this;
			}

			/** Constructs the final knapsack object. */
			public Knapsack construct() {
				return sack;
			}
		}
	}

}
