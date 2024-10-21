
public class Parrot {
	
	private String phrase;
	
	public Parrot(String phrase) {
		this.phrase = phrase;
	}
	
	private void say(int repetition) {
		for (int i = 0; i < repetition; i++) {
			System.out.println(phrase);
		}
	}
	
	private void change(String newPhrase) {
		phrase = newPhrase;
	}
	
	public static void main(String[] args) {

		Parrot parrot = new Parrot("Hello World");
		parrot.say(3);
		
		parrot.change("Hello");
		parrot.say(1);
	}

}
