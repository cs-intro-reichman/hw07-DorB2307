import javax.print.DocFlavor.STRING;

public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		//System.out.println(levenshtein(word, "woman"));
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.isEmpty()) return "";
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase(); word2 = word2.toLowerCase();
		int lenA = word1.length();
        int lenB = word2.length();
        if (lenA == 0) {
            return lenB;
        }
        if (lenB == 0) {
            return lenA;
        }
        int cost = 0;
		if (word1.charAt(0) != word2.charAt(0)) cost = 1;
        int deletion = levenshtein(word1.substring(1), word2) + 1;
        int insertion = levenshtein(word1, word2.substring(1)) + 1;
        int substitution = levenshtein(word1.substring(1), word2.substring(1)) + cost;
        return Math.min(Math.min(deletion, insertion), substitution);
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
		int min = threshold + 1, lev = 0;
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
