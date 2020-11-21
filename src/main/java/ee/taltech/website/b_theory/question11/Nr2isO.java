package ee.taltech.website.b_theory.question11;

public class Nr2isO {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does O stand for in SOLID? Explain the principle.
    // Answer: O stands for Open-Closed principle
    // Principle states that software entities (classes, modules, methods, etc.) should be open for extension,
    // but closed for modification. This means that the code should be written so that you will be
    // able to add new functionality without changing the existing code. That way it is easier to maintain code and
    // prevent it from running to errors.


    //todo B Give an example. Write actual or pseudo code.

}
// creating interface to calculate area of different shapes
interface CalculateAreaInterface {

    double calculateArea();
}

// class to calculate the area of a rectangle
class RectangleArea implements CalculateAreaInterface {
    private double length;
    private double height;

    public RectangleArea(double length, double height) {
        this.height = height;
        this.length = length;
    }

    @Override
    public double calculateArea() {
        return length * height;
    }
}

// class to calculate the area of a triangle
class TriangleArea implements CalculateAreaInterface {
    private double length;
    private double height;

    public TriangleArea(double length, double height) {
        this.height = height;
        this.length = length;
    }

    @Override
    public double calculateArea() {
        return (length * height) / 2;
    }
}

// class to calculate a shape's area
class CalculateArea {
    double objectArea;

    public void calculate(CalculateAreaInterface object) {
        if (object != null) {
            objectArea = object.calculateArea();
        }
    }
}

// now we extend our program by making a new class and following Open-Closed principle, by not making changes in the existing code.
class CircleArea implements CalculateAreaInterface {
    private double radius;
    private static final double PI = Math.PI;

    public CircleArea(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return PI * (radius * radius);
    }
}



