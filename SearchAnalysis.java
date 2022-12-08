package searchanalysis;
//@author Dr. Sawsan Alhalawani

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SearchAnalysis {
    public static void main(String[] args) {
        // 1. Place your code here
        // 1.a Read the data from the dataset
        // 1.b store the data in an array List; Create a node for each word and add it
        // to the list
        System.out.println("\n\n");
        
        
        int searchMethod = 3;
        

        if (args.length > 0) {
            searchMethod = Integer.parseInt(args[0]);
        }
        String dir = "dataset/ds1";
        if (args.length > 0) {
            dir = args[1];
        }
        String[] searchKeys = { "the", "night" };

        // 2. Place your code here
        // 2.a Call exhaustive search to return the frequency of the word occurences in
        // the dataset

        if (searchMethod == 1) {
            printSeperator();
            long startTime = getStartTime();
            System.out.println("Started reading files from directory: " + dir);
            ArrayList<String> words = loadToArrayList(dir);
            long endTime = getEndTime();
            System.out.println(endTime - startTime + " milliseconds");
            printSeperator();
            for (String searchKey : searchKeys) {
                printSeperator();
                startTime = getStartTime();
                int frequency = exhaustiveSearch(words, searchKey);
                endTime = getEndTime();

                System.out.println("Searching key: " + searchKey);
                System.out.println(endTime - startTime + " milliseconds");
                System.out.println(searchKey + " " + frequency);
                printSeperator();
            }
        }
        else if(searchMethod == 2){ ///BST
            printSeperator();
            long startTime = getStartTime();
            System.out.println("Started reading files from directory: " + dir);
            BST BST_tree = loadToBST(dir);
            long endTime = getEndTime();
            System.out.println(endTime - startTime + " milliseconds");
            printSeperator();
            for (String searchKey : searchKeys) {
                printSeperator();
                startTime = getStartTime();
                int frequency = BST_tree.searchTree(searchKey);
                endTime = getEndTime();

                System.out.println("Searching key: " + searchKey);
                System.out.println(searchKey + " " + frequency);
                System.out.println(endTime - startTime + " milliseconds");
                printSeperator();
            }
        }
        else if(searchMethod == 3){ //AVL
            printSeperator();
            long startTime = getStartTime();
            System.out.println("Started reading files from directory: " + dir);
            AVL AVL_tree = loadToAVL(dir);
            long endTime = getEndTime();
            System.out.println(endTime - startTime + " milliseconds");
            printSeperator();
            for (String searchKey : searchKeys) {
                printSeperator();
                startTime = getStartTime();
                int frequency = AVL_tree.searchTree(searchKey);
                endTime = getEndTime();

                System.out.println("Searching key: " + searchKey);
                System.out.println(searchKey + " " + frequency);
                System.out.println(endTime - startTime + " milliseconds");
                printSeperator();
            }
        }
        else System.out.println("Search Method is not valid!");
        
        // (Note: You need to impelement exhaustive search method below)
        // 2.b Calculate and display time taken to run exhaustive search

        // 3. Place your code here
        // 3.a Create a binary search tree object and insert the elements of your
        // dataset into the BST object
        // 3.b Call BST search method to find the frequency of the word occurences in
        // the BST
        // (Note: You need to implement searchTreeHelper and insert method in BST class)
        // 3.c Calculate and display time taken to run BST search method

        // 4. Place your code here
        // 4.a Create an AVL tree object and insert the elements of your
        // dataset into the AVL tree object
        // 4.b Call AVL method search to return the frequency of the word occurences in
        // the AVL
        // (Note: You need to implement insert and search methods in AVL Class)
        // 3.c Calculate and display time taken to run AVL maximum method

    }

    private static void printSeperator() {
        System.out.println("*****");
    }

    private static long getStartTime() {
        long startTime = System.currentTimeMillis();
        System.out.println("Start Time: " + startTime);
        return startTime;
    }

    private static long getEndTime() {
        long endTime = System.currentTimeMillis();
        System.out.println("End Time: " + endTime);
        return endTime;
    }

    public static int exhaustiveSearch(ArrayList<String> words, String searchKey) {
        int frequency = 0;
        for (String word : words) {
            if (word.equals(searchKey)) {
                ++frequency;
            }
        }
        return frequency;
    }

    public static ArrayList<String> getFilesFromDirectory(String dir) {
        ArrayList<String> files = new ArrayList<String>();
        File[] folder = new File(dir).listFiles();
        for (File f : folder)
            if (f.isFile()) {
                files.add(f.getAbsolutePath());
            }
        return files;
    }

    final static String REGEX = "\\s|\\,|\\.|\\!|\\?|\\;";

    public static ArrayList<String> loadToArrayList(String dir) {
        ArrayList<String> words = new ArrayList<String>();
        for (String path : getFilesFromDirectory(dir)) {
            File file = new File(path);
            Scanner sc;
            try {
                sc = new Scanner(file);
                sc.useDelimiter("\\Z");
                for (String word : sc.next().split(REGEX))
                    if (word.length() > 0)
                        words.add(word);

                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Loaded all files in ArrayList");
        return words;
    }

    public static BST loadToBST(String dir) {
        BST tree = new BST();
        for (String path : getFilesFromDirectory(dir)) {
            File file = new File(path);
            Scanner sc;
            try {
                sc = new Scanner(file);
                sc.useDelimiter("\\Z");
                for (String word : sc.next().split(REGEX))
                    if (word.length() > 0)
                        tree.insert(word);

                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Loaded all files in BST tree");
        return tree;
    }

    public static AVL loadToAVL(String dir){
        AVL tree = new AVL();
        for (String path : getFilesFromDirectory(dir)) {
            File file = new File(path);
            Scanner sc;
            try {
                sc = new Scanner(file);
                sc.useDelimiter("\\Z");
                for (String word : sc.next().split(REGEX))
                    if (word.length() > 0){
                        System.out.println(word);
                        tree.insert(word);
                        
                    }

                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Loaded all files in AVL tree");
        tree.ff();
        return tree;
    }
}
