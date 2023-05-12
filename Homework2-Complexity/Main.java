public class Main {

	public static int search(String text, String pattern) {
		int lengthOfText = text.length();
		int lengthOfPattern = pattern.length();
		// iterate through the whole text character by character
		for (int i = 0; i < lengthOfText - lengthOfPattern+1; i++) {
			int j;
			// On every iteration we check whether the two characters are matching or not
			for (j = 0; j < lengthOfPattern; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break; // if mismatch: we break out
				}
			}
			// We have found the pattern in the text because no mismatching character has been found
			if (j == lengthOfPattern) {
				// i is the first character in the match in this case it is the index of the character T
				return i;
			}
		}
		return lengthOfText; //The given text does not contain the given pattern
	}

	public static void main(String[] args) {
		String text = "THIS IS A TEST";
		String pattern = "TEST";

		int result = search(text, pattern);

		System.out.println("result: " + result);
	}
}

