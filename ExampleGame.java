package Projects.WordLadderSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List; 
@SuppressWarnings("unused")



public class ExampleGame {

    public static void main(String[] args) throws FileNotFoundException {
        WordLadderGame doublets = new GameEngine(new FileInputStream(new File("Projects\\WordLadderSolver\\WordLists\\sowpods.txt")));


        System.out.println(doublets.getHammingDistance("hammer", "hamming"));
        System.out.println(doublets.getHammingDistance("clear", "color"));
        System.out.println(doublets.getHammingDistance("raw", "saw"));
        System.out.println(doublets.getHammingDistance("cats", "zzzz"));

        System.out.println(doublets.isWord("test"));
        System.out.println(doublets.isWord("catastrophic"));
        System.out.println(doublets.isWord("ploe"));

        System.out.println(doublets.getWordCount());

        System.out.println(doublets.isWordLadder(Arrays.asList("flow", "slow", "slot", "soot")));
        System.out.println(doublets.isWordLadder(Arrays.asList("cut", "sut", "sat", "sap")));

        System.out.println(doublets.getNeighbors("cloud"));

        System.out.println(doublets.getMinLadder("bat", "sat"));
        System.out.println(doublets.getMinLadder("crash", "array"));
        System.out.println(doublets.getMinLadder("crash", "bayou"));
        System.out.println(doublets.getMinLadder("work", "team"));
    }
}

/*

RUNTIME OUTPUT

-1
3
1
4
true
true
false
267751
true
false
[aloud, clour, clous, clout]
[bat, sat]
[crash, trash, trass, tress, trets, arets, areas, arras, array]
[]
[work, bork, berk, beak, teak, team]

 */

