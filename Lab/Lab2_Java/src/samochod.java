
public class samochod {

	String marka;
	String model;
	int rokProdukcji;
	
	public samochod(String marka, String model, int rokProdukcji) {
		this.marka = marka;
		this.model = model;
		this.rokProdukcji = rokProdukcji;
	}
	
	public samochod() {
		marka = "Audi";
		model = "A4";
		rokProdukcji = 2018;
	}
	
	void printInfo() {
		System.out.println(marka);
		System.out.println(model);
		System.out.println(rokProdukcji);
	}
	
	public static void main(String[] args) {
		
		samochod samochod1 = new samochod("Toyota","Corolla", 2020);
		samochod1.printInfo();
		
		samochod samochod2 = new samochod();
		samochod2.printInfo();

	}

}
