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
    HashMap<String, Integer> keywordHashMap = new HashMap<String, Integer>();

    // initialize HashMap
    keywordHashMap.put("abstract", 0);
    keywordHashMap.put("boolean", 0);
    keywordHashMap.put("break", 0);
    keywordHashMap.put("byte", 0);
    keywordHashMap.put("case", 0);
    keywordHashMap.put("catch", 0);
    keywordHashMap.put("char", 0);
    keywordHashMap.put("class", 0);
    keywordHashMap.put("continue", 0);
    keywordHashMap.put("default", 0);
    keywordHashMap.put("do", 0);
    keywordHashMap.put("double", 0);
    keywordHashMap.put("else", 0);
    keywordHashMap.put("enum", 0);
    keywordHashMap.put("extends", 0);
    keywordHashMap.put("final", 0);
    keywordHashMap.put("finally", 0);
    keywordHashMap.put("float", 0);
    keywordHashMap.put("for", 0);
    keywordHashMap.put("if", 0);
    keywordHashMap.put("implements", 0);
    keywordHashMap.put("import", 0);
    keywordHashMap.put("instanceof", 0);
    keywordHashMap.put("int", 0);
    keywordHashMap.put("interface", 0);
    keywordHashMap.put("long", 0);
    keywordHashMap.put("new", 0);
    keywordHashMap.put("package", 0);
    keywordHashMap.put("private", 0);
    keywordHashMap.put("protected", 0);
    keywordHashMap.put("public", 0);
    keywordHashMap.put("return", 0);
    keywordHashMap.put("short", 0);
    keywordHashMap.put("static", 0);
    keywordHashMap.put("string", 0);
    keywordHashMap.put("super", 0);
    keywordHashMap.put("switch", 0);
    keywordHashMap.put("this", 0);
    keywordHashMap.put("throw", 0);
    keywordHashMap.put("throws", 0);
    keywordHashMap.put("try", 0);
    keywordHashMap.put("void", 0);
    keywordHashMap.put("while", 0);

    HashMap<Character, Integer> symbolsHashMap = new HashMap<Character, Integer>();

    symbolsHashMap.put('\'', 0);
    symbolsHashMap.put('\"', 0);
    symbolsHashMap.put('[', 0);
    symbolsHashMap.put('(', 0);
    symbolsHashMap.put('{', 0);
    symbolsHashMap.put(';', 0);
    symbolsHashMap.put(']', 0);
    symbolsHashMap.put(')', 0);
    symbolsHashMap.put('}', 0);

    try {
      File file = new File("BPCodeAnalyzer.java");
      Scanner fs = new Scanner(file);

      // count words
      ArrayList<String> words = new ArrayList<String>();
      ArrayList<Character> symbols = new ArrayList<Character>();

      while (fs.hasNextLine()) {
        String line = fs.nextLine();
        String lineWithWords = line;
        lineWithWords = line.replaceAll("\\R", ""); // remove newlines
        lineWithWords = lineWithWords.replaceAll("\\W", " "); // remove non words
        for (String i : lineWithWords.split("\\s+")) {
          words.add(i.toLowerCase());
        }

        String lineWithSymbols = line;
        lineWithSymbols = lineWithSymbols.replaceAll("\\R", ""); // remove newlines
        lineWithSymbols = lineWithSymbols.replaceAll("[a-zA-Z0-9\\s]", "");
        //System.out.println(lineWithSymbols);

        for (char i : lineWithSymbols.toCharArray()) {
          symbols.add(i);
        }
      }

      HashMap<String, Integer> wordFreqHashMap = new HashMap<String, Integer>();
      HashMap<Character, Integer> symbolFreqHashMap = new HashMap<Character, Integer>();

      for (String word : words) {
        int count = wordFreqHashMap.containsKey(word)
          ? wordFreqHashMap.get(word)
          : 0;
        wordFreqHashMap.put(word, count + 1);
      }

      for (String word : wordFreqHashMap.keySet()) {
        if (keywordHashMap.containsKey(word)) {
          keywordHashMap.put(word, wordFreqHashMap.get(word));
        }
      }

      for (char ch : symbols) {
        int count = symbolFreqHashMap.containsKey(ch)
          ? symbolFreqHashMap.get(ch)
          : 0;
        symbolFreqHashMap.put(ch, count + 1);
      }

      for (char ch : symbolFreqHashMap.keySet()) {
        if (symbolsHashMap.containsKey(ch)) {
          symbolsHashMap.put(ch, symbolFreqHashMap.get(ch));
        }
      }

      fs.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occured");
    }
    //print results
    int numOfKeywords = 0;
    for (String i : keywordHashMap.keySet()) {
      numOfKeywords += keywordHashMap.get(i);
    }

    System.out.println("Number of Keywords: " + numOfKeywords + "\n");

    System.out.println("Occurrences of Keywords");

    for (String i : keywordHashMap.keySet()) {
      System.out.println("    " + i + ": " + keywordHashMap.get(i));
    }

    int numOfSymbols = 0;
    for (char i : symbolsHashMap.keySet()) {
      numOfSymbols += symbolsHashMap.get(i);
    }
    System.out.println("Number of Symbols: " + numOfSymbols + "\n");

    System.out.println("Occurrences of Keywords");

    for (char i : symbolsHashMap.keySet()) {
      System.out.println("    " + i + ": " + symbolsHashMap.get(i));
    }
  }
}
