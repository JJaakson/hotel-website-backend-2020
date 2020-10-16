package ee.taltech.website.a_theory.question3;

public class Abstraction {

    //todo
    // Abstra-ca-dabra

    //todo p1
    // In your words (do not use wiki definitions)
    // What is abstraction? (P.S It has nothing to do with keyword abstract)
    // Answer: It is when irrelevant are hidden from user to ease the users interaction with the database

    //todo p2
    // Create an abstraction of an animal of your choice.
    // It should have at least 1 property and 1 method.
    // Create it as a real java class inside this package.

    private static class Cat {

        private int age;
        private int size;
        private final String breed;

        public Cat(int age, int size, String breed) {
            this.age = age;
            this.size = size;
            this.breed = breed;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getBreed() {
            return breed;
        }
    }

}
