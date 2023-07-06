package week2.ClassesAndInterfaces.Exe1;

class Circle extends Figure {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * (radius * radius);
    }

}