import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexGenerator {
    public static class Entry implements Comparable<Entry> {
        ArrayList<Integer> lines = new ArrayList<Integer>();
        String word = "";

        Entry(String word,int line) {
            this.word = word.toLowerCase();
            lines.add(line);
        }
        public void addLine(int line){
            lines.add(line);
        }
        public String getWord(){
            return this.word;
        }
        @Override
        public int compareTo(Entry t) {
            return this.word.compareTo(t.getWord());
        }

        @Override
        public String toString() {
            return this.word + this.lines.toString();
        }
    }
    private static final Pattern wordPattern =  Pattern.compile("[\\p{L}\\p{N}']+");
    public static BinarySearchTree<Entry> readFile(String fileName, BinarySearchTree<Entry> bst){
        try{
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line = in.readLine();
            int lineCount =0;
            while(line != null){
                lineCount++;
                Matcher m = wordPattern.matcher(line);
                ArrayList<String> matches = new ArrayList<String>();
                while(m.find()){
                    //instantiate a new entry
                    Entry entry = new Entry(m.group(), lineCount);
                    //search the bst to see if there is already an entry for this word;
                    Entry e = bst.find(entry);
                    if (e == null){
                        //if there is not an entry for this word add it
                        bst.add(entry);
                    }else{
                        //the word is already in the bst, so add this line to it
                        e.addLine(lineCount);
                    }
                }
                line = in.readLine();
            }
            in.close();
        }catch (FileNotFoundException e){
            System.out.println("Failed to load text file--File Not Found");
        }catch(IOException e){
            System.out.println("Failed to load text file--IOException Occurred");
        }
        return bst;
    }
    public static void main(String[] args){
        BinarySearchTree<Entry> bst = new BinarySearchTree<Entry>();
        bst = readFile(Paths.get(".").toAbsolutePath().normalize().toString() + "\\data.txt", bst);
        System.out.println(bst.toStringInOrder().replaceAll(" ", ""));
    }
}