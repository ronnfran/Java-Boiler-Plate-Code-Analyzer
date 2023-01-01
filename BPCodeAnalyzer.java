import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BPCodeAnalyzer {

  public static void main(String[] args) {
    System.out.println("I am the Java Boilerplate Code Analyzer\n");
    try {
      FindKeywords();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  //count key words
  public static void FindKeywords() throws FileNotFoundException {
    HashMap<String, Integer> javaKeywords = new HashMap<String, Integer>();

    // initialize HashMap
    javaKeywords.put("abstract", 0);
    javaKeywords.put("boolean", 0);
    javaKeywords.put("break", 0);
    javaKeywords.put("byte", 0);
    javaKeywords.put("case", 0);
    javaKeywords.put("catch", 0);
    javaKeywords.put("char", 0);
    javaKeywords.put("class", 0);
    javaKeywords.put("continue", 0);
    javaKeywords.put("default", 0);
    javaKeywords.put("do", 0);
    javaKeywords.put("double", 0);
    javaKeywords.put("else", 0);
    javaKeywords.put("enum", 0);
    javaKeywords.put("extends", 0);
    javaKeywords.put("final", 0);
    javaKeywords.put("finally", 0);
    javaKeywords.put("float", 0);
    javaKeywords.put("for", 0);
    javaKeywords.put("if", 0);
    javaKeywords.put("implements", 0);
    javaKeywords.put("import", 0);
    javaKeywords.put("instanceof", 0);
    javaKeywords.put("int", 0);
    javaKeywords.put("interface", 0);
    javaKeywords.put("long", 0);
    javaKeywords.put("new", 0);
    javaKeywords.put("package", 0);
    javaKeywords.put("private", 0);
    javaKeywords.put("protected", 0);
    javaKeywords.put("public", 0);
    javaKeywords.put("return", 0);
    javaKeywords.put("short", 0);
    javaKeywords.put("static", 0);
    javaKeywords.put("super", 0);
    javaKeywords.put("switch", 0);
    javaKeywords.put("this", 0);
    javaKeywords.put("throw", 0);
    javaKeywords.put("throws", 0);
    javaKeywords.put("try", 0);
    javaKeywords.put("void", 0);
    javaKeywords.put("while", 0);

    HashMap<String, Integer> symbols = new HashMap<String, Integer>();

    try {
      File file = new File("BPCodeAnalyzer.java");
      Scanner fs = new Scanner(file);
      ArrayList<String> words = new ArrayList<String>();

      while (fs.hasNextLine()) {
        String line = fs.nextLine();
        line = line.replaceAll("\\R", "");
        line = line.replaceAll("\\W", " ");
        for (String i : line.split("\\s+")) {
          words.add(i);
        }
      }

      HashMap<String, Integer> freq = new HashMap<String, Integer>();

      for (String word : words) {
        int count = freq.containsKey(word) ? freq.get(word) : 0;
        freq.put(word, count + 1);
      }

      for (String word : freq.keySet()) {
        if (javaKeywords.containsKey(word)) {
          javaKeywords.put(word, freq.get(word));
        }
      }

      fs.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occured");
    }

    //print results
    int numOfKeywords = 0;
    for (String i : javaKeywords.keySet()) {
      numOfKeywords += javaKeywords.get(i);
    }

    System.out.println("Number of Keywords: " + numOfKeywords);

    System.out.println("Occurrences of Keywords");

    for (String i : javaKeywords.keySet()) {
      System.out.println("    " + i + ": " + javaKeywords.get(i));
    }
  }
}
