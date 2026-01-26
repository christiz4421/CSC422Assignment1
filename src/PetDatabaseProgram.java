/**
 * I certify that this computer program submitted by me is all of my own work.
 * Signed: Zach Christianson
 * <p>
 * Author: Zach Christianson
 * Date: January 17, 2026
 * Revised: January 18, 2026
 * Class: CSC 422
 * File Name: CSC 422 Assignment 1 Part 2 / PetDatabaseProgram.java
 * Assignment: CSC 422 Assignment 1 Part 2
 * Description: This program creates a pet database and lets the user add pets,
 * view all pets in a formatted table, search for pets by name or age, update pet
 * information, and remove pets. It is Release 3 of the Pet Database Program.
 * </p>
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

        // Load pets from file at startup
        loadPetsFromFile();

        // Loop until the user chooses to exit
        while (choice != 7) {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                showPets();
            } else if (choice == 2) {
                addPets(scanner);
            } else if (choice == 3) {
                searchByName(scanner);
            } else if (choice == 4) {
                searchByAge(scanner);
            } else if (choice == 5) {
                updatePet(scanner);
            } else if (choice == 6) {
                removePet(scanner);
            } else if (choice == 7) {
                // Save pets to file before exiting
                savePetsToFile();
                System.out.println("Goodbye!");
            } else {
                System.out.println("Please enter a number from 1 to 7.");
            }
        }
    }

    /**
     * Loads pet data from the pets.txt file at program startup.
     * Each line in the file must have the format of (name age).
     * Invalid lines are ignored. If the file does not exist,
     * the program starts with an empty pet list.
     */
    private static void loadPetsFromFile() {

        File filename = new File("pets.txt");

        try (Scanner scanner = new Scanner(filename)) {

            // Read each line until the file ends or the list reaches 5 pets
            while (scanner.hasNextLine() && pets.size() < 5) {

                String line = scanner.nextLine().trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split into two parts: name and age
                String[] parts = line.split(" ");

                // Skip lines that do not have exactly two values
                if (parts.length != 2) {
                    continue;
                }

                String name = parts[0];
                String ageText = parts[1];

                try {
                    // Convert age to an integer
                    int age = Integer.parseInt(ageText);

                    // Only accept valid age range
                    if (age >= 1 && age <= 20) {
                        pets.add(new Pet(name, age));
                    }
                // Ignore lines where the age is not a valid number
                } catch (NumberFormatException e) {

                }
            }
        // File does not exist — start with an empty list
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Saves all pets in the database to the pets.txt file.
     * Each pet is written on its own line with the format of (name age).
     * This method is called when the user exits the program.
     */
    private static void savePetsToFile() {

        try (java.io.PrintWriter writer = new java.io.PrintWriter("pets.txt")) {

            // Write each pet to the file
            for (Pet pet : pets) {
                writer.println(pet.getName() + " " + pet.getAge());
            }
            // Confirm that the pets were saved successfully
            System.out.println("The pet data was successfully saved to the file pets.txt.");


        } catch (java.io.IOException e) {
            // Notify the user if saving the file fails
            System.out.println("Sorry, the pets were not able to be saved to the file.");
        }
    }

    /**
     * Prints the menu options.
     */
    private static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add pets (or type 'done' to stop adding pets)");
        System.out.println("3) Search for pets by name");
        System.out.println("4) Search for pets by age");
        System.out.println("5) Update a pet");
        System.out.println("6) Remove a pet");
        System.out.println("7) Exit program");
        System.out.print("Your choice: ");
    }

    /**
     * Lets the user add pets until they type "done".
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void addPets(Scanner scanner) {
        int count = 0;

        // Do not allow adding more than 5 pets
        if (pets.size() >= 5) {
            System.out.println("Sorry, the pet database already contains the maximum number of 5 pets.");
            return;
        }

        // Read the first line of user input
        System.out.print("add pet (name age): ");
        String input = scanner.nextLine();

        // Loop until the user types "done"
        while (!input.equalsIgnoreCase("done")) {

            // Split user input into name and age
            String[] parts = input.split(" ");
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);

            // Validate age range
            if (age < 1 || age > 20) {
                System.out.println("Sorry, the pet's age must be between 1 and 20.");
            } else {
                pets.add(new Pet(name, age));
                count++;
            }

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
            System.out.printf("| %-3d | %-15s | %-3d |\n", i, pet.getName(), pet.getAge());
        }

        printTableFooter(pets.size());
    }

    /**
     * Searches for pets by name and displays all that match.
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void searchByName(Scanner scanner) {
        System.out.print("Enter a name to search: ");
        String name = scanner.nextLine();

        printTableHeader();

        int match = 0;

        // Check each pet to see if the entered name matches
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);

            // Print any matching names
            if (pet.getName().equalsIgnoreCase(name)) {
                System.out.printf("| %-3d | %-15s | %-3d |\n", i, pet.getName(), pet.getAge());
                match++;
            }
        }

        printTableFooter(match);
    }

    /**
     * Searches for pets by age and displays all that match.
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void searchByAge(Scanner scanner) {
        System.out.print("Enter an age to search: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        printTableHeader();

        int match = 0;

        // Check each pet to see if the entered age matches
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);

            // Print any matching names
            if (pet.getAge() == age) {
                System.out.printf("| %-3d | %-15s | %-3d |\n", i, pet.getName(), pet.getAge());
                match++;
            }
        }

        printTableFooter(match);
    }

    /**
     * Updates a pet's name and age based on its ID number.
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void updatePet(Scanner scanner) {
        showPets();

        System.out.print("Enter the pet ID you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Check to ensure the ID is within the valid range
        if (id < 0 || id >= pets.size()) {
            System.out.println("That is not a valid pet ID. Please try again.");
            return;
        }

        Pet pet = pets.get(id);

        // Store old values for the update message
        String oldName = pet.getName();
        int oldAge = pet.getAge();

        // Get new values
        System.out.print("Enter new name and new age: ");
        String input = scanner.nextLine();

        // Split user input into name and age
        String[] parts = input.split(" ");
        String newName = parts[0];
        int newAge = Integer.parseInt(parts[1]);

        // Age range validation
        if (newAge < 1 || newAge > 20) {
            System.out.println("Sorry, the pet's age must be between 1 and 20.");
            return;
        }

        // Update the pet
        pet.setName(newName);
        pet.setAge(newAge);

        // Print a message showing the old and new values
        System.out.println(oldName + " " + oldAge + " changed to " + newName + " " + newAge + ".");
    }


    /**
     * Removes a pet from the database based on its ID number.
     *
     * @param scanner the Scanner used for reading user input
     */
    private static void removePet(Scanner scanner) {
        showPets();

        System.out.print("Enter the ID of the pet to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Check to ensure the ID is within the valid range
        if (id < 0 || id >= pets.size()) {
            System.out.println("That is not a valid pet ID. Please try again.");
            return;
        }

        // Get the pet before removing it
        Pet pet = pets.get(id);
        String name = pet.getName();
        int age = pet.getAge();

        // Remove the pet
        pets.remove(id);

        // Print a message showing which pet was removed
        System.out.println(name + " " + age + " is removed.");
    }

    /**
     * Prints the table header for the pet table.
     */
    private static void printTableHeader() {
        System.out.println("+-----------------------------+");
        System.out.println("| ID  | NAME            | AGE |");
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


