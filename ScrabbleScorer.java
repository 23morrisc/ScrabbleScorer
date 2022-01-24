import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Returns the value of a given word if it is legal in scrabble
 * @author 23morrisc
 * @version 01/24/2022
 */

public class ScrabbleScorer {
    private ArrayList<String> dictionary;
    private int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private String alpha;

    /**
     * Initializes the dictionary and the lookup table used later in class
     */

    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        buildDictionary();
    }

    /**
     * Reads the legal words file and adds the values to the dictionary
     */

    public void buildDictionary() {
        try {

            File words = new File("SCRABBLE_WORDS.txt");
            FileReader reader = new FileReader(words);
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                dictionary.add(line);
            }
            Collections.sort(dictionary);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Determines whether a word is in valid or not
     * @param word
     * @return boolean
     */

    public boolean isValidWord(String word) {
        return Collections.binarySearch(dictionary, word) >= 0;
    }

    /**
     * Determines the score value of a word provided by the user
     * @param word
     * @return int value of score
     */

    public int getWordScore(String word) {
        int sum = 0;

        for (int i = 0; i < word.length(); i++) {
            sum += points[alpha.indexOf(word.charAt(i))];
        }

        return sum;
    }

    /**
     * Asks user for a word and prints value associated
     * @param args
     */

    public static void main(String[] args) {

        ScrabbleScorer app = new ScrabbleScorer();
        String userWord;
        Scanner userIn = new Scanner(System.in);

        System.out.println("* Welcome to the Scrabble Word Scorer app *");

        try {

            while(true) {
                System.out.println("Enter a word to score or 0 to quit: ");
                userWord = userIn.nextLine();
                if (userWord.equals("0"))
                    break;
                else {
                    if (app.isValidWord(userWord.toUpperCase(Locale.ROOT)))
                        System.out.println(userWord + " = " + app.getWordScore(userWord.toUpperCase(Locale.ROOT)) + " points");
                    else {
                        System.out.println("is not a valid word in the dictionary");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Exiting the program thanks for playing");
    }
}
