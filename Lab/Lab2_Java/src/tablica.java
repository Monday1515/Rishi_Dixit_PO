
public class tablica {

    private int[] tablica;
    private int rozmiar;

    public tablica(int rozmiar) {
        this.rozmiar = rozmiar;
        tablica = new int[rozmiar];
    }
    
    public tablica() {
    	this.rozmiar = 10;
    	tablica = new int[10];
    }
    
    void add(int indeks, int wartosc) {
    	if (indeks >= 0 && indeks < rozmiar) {
    		tablica[indeks] = wartosc;
    	}
    }
    
    void print() {
    	for (int i = 0; i < rozmiar; i++) {
    		System.out.print(tablica[i] + " ");
    	}
    }

	
	public static void main(String[] args) {
		
		tablica tablica1 = new tablica();
	    tablica1.add(0,1);
	    tablica1.add(1,2);
	    tablica1.add(2,3);
	    tablica1.add(3,4);
	    tablica1.add(4,5);
	    tablica1.add(5,6);
	    tablica1.add(6,7);
	    tablica1.add(7,8);
	    tablica1.add(8,9);
	    tablica1.add(9,10);
	    tablica1.print();

	    System.out.println("");
	    
	    tablica tablica2 = new tablica(3);
	    tablica2.add(0,11);
	    tablica2.add(1,12);
	    tablica2.add(2,13);
	    tablica2.print();

	}

}
