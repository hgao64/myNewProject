package edu.gatech.seclass.prj1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static Scanner input;

	public static void main(String[] args) throws FileNotFoundException {
		
		String clearSentence = "";
		
		// Prompts user to enter file name
		System.out.println("Please enter file name: \n");
		
		input = new Scanner(System.in);
		
		String file = input.nextLine();
		
		System.out.println("You entered: " + file);
		
		File fileName = new File();
		AvgSentenceLength.setFile(fileName);
		// first way to read file
		/*
		try {
			Scanner in = new Scanner(fileName);
		
			String fileContent = "";
		
			while(in.hasNextLine()) {
				String line = in.nextLine();
			// System.out.println(line);
			// Concatenate all lines of file content into a string
				fileContent = fileContent + line + " ";			
			}
		*/		
		/*
		//second way to read file 
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				String line;
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (FileNotFoundException e) {
				System.out.println("Can't find file: " + fileName.toString());
			} catch (IOException e) {
				System.out.println("Unable to read file: " + fileName.toString());
			}
		*/

			System.out.println(fileContent);
			clearSentence = fileContent.replaceAll("\\ +", " ");
		
			// If delimiters contain "!" and/or "?", they will be replaced with "."
			if (clearSentence.indexOf("!")>0) {
				clearSentence = clearSentence.replaceAll("!+", "\\.");
			}
			if (clearSentence.indexOf("?")>0) {
				clearSentence = clearSentence.replaceAll("\\?+", "\\.");
			}
			if (clearSentence.indexOf("'")>0) {
				clearSentence = clearSentence.replaceAll("'", "");
			}
			if (clearSentence.indexOf("\"")>0) {
				clearSentence = clearSentence.replaceAll("\"", "");
			}
			clearSentence = clearSentence.replaceAll("\\n+", "");
			clearSentence = clearSentence.replace("\\.+", "\\.");
		
		// If user want to use "," and/or ";" as delimiter, indicating by flag -d, 
		// then the following code will replace "," and/or ";" with "."
		/*
		clearSentence = clearSentence.replaceAll(",", "\\.");
		
		clearSentence = clearSentence.replaceAll(";", "\\.");
		*/
			
			String[] sentence = clearSentence.split("\\. ");
			int countSentence = 0;
			for(int i = 0; i<sentence.length; i++) {				
				int sl = AvgSentenceLength.sentenceLength(sentence[i]);
				if (sl != 0) {
					countSentence += 1;
					System.out.printf("%d words in sentence No. %d: %s\n", sl, countSentence, sentence[i]);
				}
			}
		
			int asl = AvgSentenceLength.averageSentenceLength(sentence);
		
			System.out.printf("Average sentence length of the essay is %d. \n", asl);
		
			in.close();
		}
		catch(FileNotFoundException exception) {
			System.out.println("File not found, " + fileName.toString());
		}
		catch(IllegalArgumentException exception) {
			System.out.println("File is empty, " + fileName.toString());
		}
	}

}
