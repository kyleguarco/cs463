public class MainNoComment {

	public static int search(String text, String pattern) {
		int N = text.length();
		int P = pattern.length();
		for (int i = 0; i < N - P + 1; i++) {
			int j;
			for (j = 0; j < P; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if (j == P) {
				return i;
			}
		}
		return N;
	}

	public static void main(String[] args) {
		String text = "THIS IS A TEST";
		String pattern = "TEST";

		int result = search(text, pattern);

		System.out.println("result: " + result);
	}
}

