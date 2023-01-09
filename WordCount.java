import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCount {
    public static void counter() {
        try {
            //Scan for input file name
            Scanner fileScan = new Scanner(System.in);
            System.out.print("Enter your input file name: ");
            String fileName = fileScan.nextLine();

            //Scan for output file name
            System.out.print("Enter your output file name: ");
            String output = fileScan.nextLine();
            FileWriter fw = new FileWriter(output);

            //Create reader and scanner
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Scanner scan = new Scanner(br);

            //Parallel ArrayLists to store each word, each word count
            ArrayList<String> lyrics = new ArrayList<>();
            ArrayList<Integer> counter = new ArrayList<>();

            //Scan for words
            while (scan.hasNext()) {
                String nextLyric = scan.next(); //Scan for next word
                nextLyric = nextLyric.toLowerCase(); //Convert all words to lowercase
                nextLyric = nextLyric.replaceAll("[.,:;?()]", ""); //Remove punctuation, digits

                //If statement to check if word is already in ArrayList
                if (lyrics.contains(nextLyric)) {
                    int index = lyrics.indexOf(nextLyric); //Find index of word
                    counter.set(index, counter.get(index) + 1); //Set value of index, increment by 1
                } else { //If word is new, increment by 1
                    lyrics.add(nextLyric);
                    counter.add(1);
                }
            }

            //Print headers
            System.out.println("-------------------------------\n" +
                    "OUTPUT REPORT FOR: " + fileName + "\n" +
                    "-------------------------------");
            fw.write("-----------------------------------\n" +
                    "OUTPUT REPORT FOR: " + fileName + "\n" +
                     "-----------------------------------\n");

            //For loop to print out content of ArrayLists
            for (int i = 0; i < lyrics.size(); i++) {
                System.out.println(lyrics.get(i) + ": " + counter.get(i));
                fw.write(lyrics.get(i) + ": " + counter.get(i) + "\n");
            }

            //Close scanners, stream, file writer
            fileScan.close();
            scan.close();
            br.close();
            fw.close();

        } catch (FileNotFoundException e) {
            System.out.println("You've entered an incorrect file name. Please check your spelling and case sensitivity.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}