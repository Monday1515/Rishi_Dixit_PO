class Rectangle {  // poprawnie
	
    private double width;
    private double height;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

class Square extends Rectangle {
    @Override
    public void setWidth(double length) {
        super.setWidth(length);
        super.setHeight(length);
    }

    @Override
    public void setHeight(double length) {
        super.setHeight(length);
        super.setWidth(length);
    }
}

public class LSP {
    public static void main(String[] args) {
    	
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(3);
        rectangle.setHeight(6);
        
        System.out.println("Rectangle area: " + rectangle.getArea());

        Square square = new Square();
        square.setWidth(5);
        
        System.out.println("Square area: " + square.getArea());
    }
}
