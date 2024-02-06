package Projects.WordLadderSolver;

import java.util.List;

/* 
 * "word" means it's in the lexicon. a "string" doesn't have to be
 */
public interface WordLadderGame {


    /**
     * @return number of words in the lexicon
     */
    int getWordCount();


    /**
     * Checks to see if the given string is a word.
     *
     */
    boolean isWord(String str);


    /**
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
    int getHammingDistance(String str1, String str2);


    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return      the neighbors of the given word
     */
    List<String> getNeighbors(String word);


    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
    boolean isWordLadder(List<String> sequence);


   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, returns the first one found.
    * If no word ladder exists, this method returns an empty
    * list.
    *
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
    List<String> getMinLadder(String start, String end);


}
