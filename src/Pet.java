/**
 * I certify that this computer program submitted by me is all of my own work.
 * Signed: Zach Christianson
 * <p>
 * Author: Zach Christianson
 * Date: January 17, 2026
 * Revised: January 18, 2026
 * Class: CSC 422
 * File Name: CSC 422 Assignment 1 Part 2 / Pet.java
 * Assignment: CSC 422 Assignment 1 Part 2
 * Description: The Pet class stores basic information about a pet, including its name and age. It
 *  has been revised to add functionality that allows users to update pet information.
 *
 * <p>
 */

/**
 * Creates the class described above.
 */
public class Pet {

    // The pet's name
    private String name;

    // The pet's age
    private int age;

    /**
     * Creates a new Pet with the specified name and age.
     *
     * @param name the pet's name
     * @param age  the pet's age
     */
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Returns the pet's name.
     *
     * @return the name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the pet's name.
     *
     * @param name the new name for the pet
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the pet's age.
     *
     * @return the age of the pet
     */
    public int getAge() {
        return age;
    }

    /**
     * Updates the pet's age.
     *
     * @param age the new age for the pet
     */
    public void setAge(int age) {
        this.age = age;
    }
}



