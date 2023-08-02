import java.io.*;
import java.util.Scanner;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class Temperature {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println ("This is a program that takes in multiple temperatures in Celsius or Fahrenheit and gives you the average of them. Enter temperatures as numbers only (e.g. 21)\n");
        // Take input from user for a daily temperature log then write to temp.txt
        try (PrintWriter fileWrite = new PrintWriter("temp.txt")) {
            System.out.println("Enter a temperature to track (type 'exit' to stop):");
            // Keep taking in temperatures from user until they reply with 'exit'
            while (true) {
                String userInput = input.nextLine();
                int num = Integer.parseInt(userInput);
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                else {
                    fileWrite.println(num);
                }
            }
            System.out.println("Temperatures successfully written to temp.txt");
        }
        // Exception to check if the program is able to write to temp.txt
        catch (IOException e) {
            System.out.println("Error: unable to write to file");
            e.printStackTrace();
        }
        // Exception to make sure a number was entered
        catch (NumberFormatException nfe) {
            System.out.println("Error:")
        }
        // User is ask if they want the average of the saved temperatures
        System.out.print("Do you want the average of the temperatures? [yes/no]: ");
        String reply = input.nextLine();
        // Program proceeds if user reply's 'yes' to find average temperature
        if (reply.equalsIgnoreCase("yes")) {
            // Read temperatures from 'temp.txt'
            try (Scanner fileReader = new Scanner(new File("temp.txt"))) {
                int totalTemps = 0;
                int numOfTemps = 0;
                // Keep adding all temperatures from 'temp.txt' until the end of the file is reached
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    int temperature = Integer.parseInt(line);
                    // Each temperature is added together and the number of temperatures is tallied
                    totalTemps += temperature;
                    numOfTemps++;
                }
                // Checks to see if temp.txt contains valid numbers then returns the average temperatures
                if (numOfTemps > 0) {
                    double avgTemp = (double) totalTemps / numOfTemps;
                    System.out.println("Average temperature: " + avgTemp);
                }
                else {
                    System.out.println("No temperatures found in the file.");
                }
            // Exception to make sure temp.txt exists
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: File was not found");
                e.printStackTrace();
            }
        }
        // Closes file input
        input.close();
    }
}
