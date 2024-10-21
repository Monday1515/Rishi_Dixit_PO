
public class Car {

	private String make;
	private String model;
	private int year;
	private int currentYear;
	
	public Car(String make, String model, int year, int currentYear) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.currentYear = currentYear;
	}
	
	public void displayInfo() {
		System.out.println(make);
		System.out.println(model);
		System.out.println(year);
	}
	
	public int getCarAge() {
		int carAge = currentYear - year;
		return carAge;
	}
	
	public boolean isSameCar(Car otherCar) {
		if (make == otherCar.make && model == otherCar.model && year == otherCar.year) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		Car Car1 = new Car("Toyota", "Corolla", 2020, 2024);
		Car1.displayInfo();
		
		System.out.println(Car1.getCarAge());
		
		Car Car2 = new Car("Audi", "A4", 2018, 2024);
		
		Car Car3 = new Car("Toyota", "Corolla", 2020, 2024);
		
		if (Car1.isSameCar(Car2) == true) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		if (Car1.isSameCar(Car3) == true) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}

	}

}
