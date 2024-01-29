
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		//System.out.println(levenshtein(word, "largely"));
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.isEmpty()) return "";
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		int count = 0, length1 = word1.length(), length2 = word2.length();
		if (length1 > length2) {
			count = length1 - length2; 
			return count + levenshtein(tail(word1.substring(count)), tail(word2));
		}
		if (length2 > length1) {
			count = length2 - length1; 
			return count + levenshtein(tail(word2.substring(count)), tail(word1));
		}
		if (word1 == "" && word2 == "") return count;
		word1 = word1.toLowerCase(); word2 = word2.toLowerCase();
		if (word1.charAt(0) != word2.charAt(0)) 
			return 1 + levenshtein(tail(word1), tail(word2));
		else return levenshtein(tail(word1), tail(word2));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = threshold, lev = 0;
		String closeString = "";
		for (int i = 0; i < dictionary.length; i++) {
			lev = levenshtein(word, dictionary[i]);
			if (lev < min) {
				min = lev;
				closeString = dictionary[i];
			}
		}
		if (closeString.equals("")) return word;
		return closeString;
	}

}
