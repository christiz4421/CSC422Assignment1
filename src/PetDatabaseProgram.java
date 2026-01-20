/**
 * I certify that this computer program submitted by me is all of my own work.
 * Signed: Zach Christianson
 * <p>
 * Author: Zach Christianson
 * Date: January 17, 2026
 * Class: CSC 422
 * File Name: CSC 422 Assignment 1 Part 2 / PetDatabaseProgram.java
 * Assignment: CSC 422 Assignment 1 Part 2
 * Description: This program creates a pet database and lets the user add pets and view all pets
 * in a formatted table. It is Release 1 of the Pet Database Program.
 * </p>
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates the class described above.
 */
public class PetDatabaseProgram {

    // Creates a list to store all Pet objects
    private static ArrayList<Pet> pets = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("Pet Database Program.");

        // Loop until the user chooses to exit
        while (choice != 3) {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                showPets();
            } else if (choice == 2) {
                addPets(scanner);
            } else if (choice == 3) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Please enter 1, 2, or 3");
            }
        }
    }

    /**
     * Prints the menu options.
     */
    private static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add pets (or type 'done' to stop adding pets)");
        System.out.println("3) Exit program");
        System.out.print("Your choice: ");
    }

    /**
     * Lets the user add pets until they type "done".
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void addPets(Scanner scanner) {
        int count = 0;

        // Read the first line of user input
        System.out.print("add pet (name age): ");
        String input = scanner.nextLine();

        // Loop until the user types "done"
        while (!input.equalsIgnoreCase("done")) {

            // Split user input into name and age
            String[] parts = input.split(" ");
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);

            // Add the new pet to the list
            pets.add(new Pet(name, age));
            count++;

            // Prompt the user for the next pet
            System.out.print("add pet (name, age): ");
            input = scanner.nextLine();
        }

        System.out.println(count + " pets added.");
    }

    /**
     * Displays all the pets in a formatted table.
     */
    private static void showPets() {
        printTableHeader();

        // Print each pet with its index as its ID number
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.printf("| %-3d | %-10s | %-4d |\n", i, pet.getName(), pet.getAge());
        }

        printTableFooter(pets.size());
    }

    /**
     * Prints the table header for the pet table.
     */
    private static void printTableHeader() {
        System.out.println("+-----------------------------+");
        System.out.println("| ID  | NAME       | AGE |");
        System.out.println("+-----------------------------+");
    }

    /**
     * Prints the table footer including the number of rows.
     *
     * @param rows number of pets displayed
     */
    private static void printTableFooter(int rows) {
        System.out.println("+-----------------------------+");
        System.out.println(rows + " rows in set.");
    }
}
