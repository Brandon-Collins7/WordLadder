package Projects.WordLadderSolver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.util.Arrays; 
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;


@SuppressWarnings("unused")

/**
 * Provides an implementation of the WordLadderGame interface. 
 */

public class GameEngine implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated in the constructor.
    private HashSet<String> lexicon;


    // visited neighbors
    private HashSet<String> visited;


    /**
     * Sets up a lexicon for the word ladder. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public GameEngine(InputStream in) {
        try {
            //instantiate lexicon
            lexicon = new HashSet<String>();

            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                
                //stores words from word list in lexicon
                lexicon.add(str.toLowerCase());

                s.nextLine();
            }
            in.close();
            s.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }


   

    public int getWordCount(){
        return lexicon.size();
    }

    public boolean isWord(String str){
        return lexicon.contains(str.toLowerCase());
    }

    public int getHammingDistance(String s1, String s2){

        if(s1.length()!=s2.length()){
            return -1;
        }

        int ham = 0;

        for(int i = 0; i < s1.length(); i++){
            if(s1.toLowerCase().charAt(i) != s2.toLowerCase().charAt(i)){
                ham++;
            }
        }

        return ham;
    }


    public List<String> getNeighbors(String word){
        word = word.toLowerCase();

        List<String> nbrs = new ArrayList<String>();
        for(int l = 0; l < word.length(); l++){
            char let = word.charAt(l);
        
            for(char i = 'a'; i <= 'z'; i++){
                //don't want to make "word" a neighbor of itself
                if(i!=let){
                    String poss = word.substring(0,l) + i + word.substring(l+1);
                    if(isWord(poss) && getHammingDistance(word, poss) == 1){
                        nbrs.add(poss);
                    }
                }
                
            }
        }

        return nbrs;

    }

    //each next word should have a Hamming distance of 1 from the previous word
    public boolean isWordLadder(List<String> seq){
        if(seq.size() == 0){
            return false;
        }

        boolean first = true;
        String prev = "";
        for(String e : seq){
            if(first){
                if(!isWord(e)){
                    return false;
                }
                prev = e;
                first = false;
                continue;
            }

            if(!isWord(e) || getHammingDistance(e, prev) != 1){
                return false;
            }
            prev = e;
            
        }

        return true;
    }

    public List<String> getMinLadder(String start, String end){
        return breadthFirstMemory(start, end);
    }



   
    //BFS methods

    public List<String> breadthFirstMemory(String start, String end) {
        markAllUnvisited();
        return bfsMemory(start, end);
    }

    
    private List<String> bfsMemory(String start, String end) {
        Deque<Node> queue = new ArrayDeque<>();
        List<String> ladder = new ArrayList<String>();

        
        visit(start);
        if (start.equals(end)) {
            ladder.add(start);
            return ladder;
        }
        
        queue.addLast(new Node(start, null));
        while (!queue.isEmpty()) {
            Node n = queue.removeFirst();
            String word = n.word;
            for (String neighbor : getNeighbors(word)) {
                if (!isVisited(neighbor)) {
                    visit(neighbor);
                    if (neighbor.equals(end)) {
                        Node last = new Node(neighbor, n);
                        ladder.add(last.word);

                        while(last.predecessor != null){
                            
                            ladder.add(0, last.predecessor.word);
                            last = last.predecessor;
                        }
                        return ladder;
                    }
                    queue.addLast(new Node(neighbor, n));
                }
            }
        }

        //queue is empty, meaning there are no unvisited neighbors yet,
        //so there is no possible word ladder.
        //this will return an empty list since nothing has been added
        return ladder;
    }



   
    //Constructs a node for linking positions together.
    
    private class Node {
        String word;
        Node predecessor;

        public Node(String start, Node pred) {
            word = start;
            predecessor = pred;
        }
    }

    //empties the visited set so that no words have been checked for the next word ladder
    private void markAllUnvisited(){
        visited = new HashSet<String>();
    }

    //Has this valid position been visited?
    private boolean isVisited(String s) {
        return visited.contains(s);
    }


    //Mark this valid position as having been visited.
    private void visit(String s) {
        visited.add(s);
    }

    
}



