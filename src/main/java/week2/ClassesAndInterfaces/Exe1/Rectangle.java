package week2.ClassesAndInterfaces.Exe1;

class Rectangle extends Figure {
    double length;
    double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

}
