package week2.ClassesAndInterfaces.Exe1;


class Square extends Figure {
    double length;

    Square(double length) {
        this.length = length;
    }

    @Override
    public double area() {
        return length * length;
    }
}