package edu.gatech.seclass.prj1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AvgSentenceLength {
	
	/*
	public static int sentenceLength(String sentence) {
		
		int sentenceLength = 0;
			
		String[] words = sentence.split(" ");
			
		int countShortWords = 0;
			
		for (int i=0; i<words.length; i++) {				
			if (words[i].length()<3) {countShortWords += 1;}
		}
			// Count words with at least 3 letters
		sentenceLength = words.length - countShortWords;
		
		return sentenceLength;	
	}
	
	public static int averageSentenceLength(String[] sentences) throws IllegalArgumentException {
		
		int countSentence = 0;
		int totalCountOfWords = 0;
		int asl = 0;
		
		// Count number of sentences
		for (int i=0; i<sentences.length; i++) {
			
			if (sentenceLength(sentences[i]) != 0) {
				totalCountOfWords = totalCountOfWords + sentenceLength(sentences[i]);
				countSentence += 1;
			}
		}
		
		if (countSentence == 0) {
			throw new IllegalArgumentException();
		}
		else {
			asl = (int) totalCountOfWords/countSentence;
			if (totalCountOfWords != 0) {
				System.out.printf("Total %d words in %d sentences. \n", totalCountOfWords, countSentence);
			}
			else {
				System.out.println("There is no syntactic word.");
			}
			return asl;
		}		
	}
	*/
	
	String fileContent = "";
	String clearSentence = "";
	String[] sentences;
	String[] words;
	int minWordLength = 3;
	
	public void setFile(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			line = br.readLine();
			while(line != null) {
				fileContent = fileContent + line + " ";
				//System.out.println(line);
				line = br.readLine();
			}
			//System.out.println(fileContent);
		} catch (FileNotFoundException e) {
			System.out.println("File not found, " + file.toString());
		} catch (IOException e) {
			System.out.println("Unable to read file, " + file.toString());
		} catch (IllegalArgumentException e) {
			System.out.println("File is empty, " + file.toString());
		}
		// Replace consecutive space with single space 
		clearSentence = fileContent.replaceAll(" +", " ");
		// ".", "!", and "?" are default delimiters 
		// If delimiters contain "!" and/or "?", they will be replaced with "."
		if (clearSentence.indexOf("!")>0) {
			clearSentence = clearSentence.replaceAll("!+", "\\.");
		}
		if (clearSentence.indexOf("?")>0) {
			clearSentence = clearSentence.replaceAll("\\?+", "\\.");
		}
		// All "'" (single quotation mark), "\"" (double quotation mark), 
		// and "\n" (new line) will be deleted 
		if (clearSentence.indexOf("'")>0) {
			clearSentence = clearSentence.replaceAll("'", "");
		}
		if (clearSentence.indexOf("\"")>0) {
			clearSentence = clearSentence.replaceAll("\"", "");
		}
		clearSentence = clearSentence.replaceAll("\\n+", "");
	}

	public void setSentenceDelimiters(String string) {
		for (int indexOfSD = 0; indexOfSD<string.length(); indexOfSD++) {
			if (string.substring(indexOfSD, indexOfSD+1) != ".") {
				clearSentence = clearSentence.replace(string.substring(indexOfSD, indexOfSD+1), ".");
			}
		}
		// Replace consecutive periods with one period 
		clearSentence = clearSentence.replace("\\.+", "\\.");
		System.out.println(clearSentence);
	}

	public void setMinWordLength(int i) {
		minWordLength = i;
		System.out.println(minWordLength);
	}

	public double computeAverageSentenceLength() {
		
		int countSentence = 0;
		int totalCountOfWords = 0;
		double aSL = 0;
		
		sentences = clearSentence.split("\\. ");

		for(int indexOfSentence = 0; indexOfSentence<sentences.length; indexOfSentence++) {
			 
			int sentenceLength = 0;
			words = sentences[indexOfSentence].split(" ");
			for (int indexOfWord=0; indexOfWord<words.length; indexOfWord++) {				
				if (words[indexOfWord].length() >= minWordLength) {
					sentenceLength += 1;
				}
			}
			// Count number of sentences
			if (sentenceLength != 0) {
				countSentence += 1;
				totalCountOfWords += sentenceLength;
			}
			System.out.println("minWordLength, "+minWordLength+" "+"total words, "+totalCountOfWords+" sentences, "+countSentence);
		}

		if (countSentence == 0) {
			throw new IllegalArgumentException();
		}
		else {
			aSL = (int) totalCountOfWords/countSentence;
			System.out.printf("Total %d words in %d sentences. \n", totalCountOfWords, countSentence);
		}
		System.out.printf("Average words of sentence in the essay is %.0f.\n\n", aSL);
		return aSL;
	}
}
