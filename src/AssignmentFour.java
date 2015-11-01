
import java.io.*;
import java.util.*;

/**
 *
 * @author Kotah Chapman Date Written : 11/1/2015 Purpose : comparisons of words
 * in two files to find number of correct and incorrectly spelled words
 */
public class AssignmentFour {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AssignmentFour rd = new AssignmentFour();
        rd.readFileDictionary();
        rd.readFileOliver();
        rd.output();
    }
    private MyLinkedList[] dictionary;
    private long counterWFound = 0;
    private long counterWFCompared = 0;
    private long counterWNotFound = 0;
    private long counterWNFCompared = 0;
    private long counter = 0;

    /**
     * @ensures: Default Constructor sets list size to 26, and instantiates 26
     * linked lists
     */
    public AssignmentFour() {
        dictionary = new MyLinkedList[26];

    }

    /**
     * @ensures: if input file is read, array filled with words from file,
     * otherwise error reading file
     */
    public void readFileDictionary() {
        File f = new File("random_dictionary.txt");
        try {
            for (int i = 0; i < dictionary.length; i++) {
                dictionary[i] = new MyLinkedList<String>();
            }
            Scanner inf = new Scanner(f);
            while (inf.hasNext()) {
                String d = inf.nextLine();
                d = d.toLowerCase();
                dictionary[(int) d.charAt(0) - 97].addFirst(d);
            }
            inf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @ensures: if input file is read, a String is filled with words from file
     * and searched in dictionary list, and if found increase words found
     * counter, otherwise increase words not found. Otherwise error reading file
     */
    public void readFileOliver() {
        try {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = "";
            String key = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    key = str;
                    str = "";
                    boolean a = dictionary[(int) key.charAt(0) - 97].contains(key);
                    if (a == true) {
                        counter = dictionary[(int) key.charAt(0) - 97].indexOf(key);
                        counterWFound++;
                        counterWFCompared += counter;
                    } else {
                        counter = dictionary[(int) key.charAt(0) - 97].indexOf(dictionary[(int) key.charAt(0) - 97].getLast());
                        counterWNotFound++;
                        counterWNFCompared += counter;
                    }
                }

            }
            inf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @ensures: Output to screen of Total words found, Words found, words not
     * found, total comparisons for words found and not found, and average for
     * comparisons of words found and not found
     */
    public void output() {
        long avgfound = counterWFCompared / counterWFound;
        long avgnotfound = counterWNFCompared / counterWNotFound;
        System.out.println("Average Comparisons Found = " + avgfound + " Comparisons for words found = " + counterWFCompared + " Number of words found = " + counterWFound);
        System.out.println("Average Comparisons Not Found = " + avgnotfound + " Comparisons for words not found = " + counterWNFCompared + " Number of words found = " + counterWNotFound);
    }
    /*
     Assignment Four Outputs:
     run:
     Average Comparisons Found = 3251 Comparisons for words found = 3048493575 Number of words found = 937492
     Average Comparisons Not Found = 7380 Comparisons for words not found = 403322916 Number of words found = 54648
     BUILD SUCCESSFUL (total time: 2 minutes 28 seconds)

     */
}
