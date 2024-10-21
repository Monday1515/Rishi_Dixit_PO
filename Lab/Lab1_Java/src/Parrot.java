import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Parrot {
	
	private List<String> Phrase;
	private Random random;
	
	public Parrot() {
		this.Phrase = new ArrayList<>();
		this.random = new Random();
	}
	
	public void addPhrase(String phrase) {
		Phrase.add(phrase);
	}
	
	private void say(int repetition) {
		String choosePhrase = Phrase.get(random.nextInt(Phrase.size()));
		for (int i = 0; i < repetition; i++) {
			System.out.println(choosePhrase);
		}
	}
	
	public static void main(String[] args) {

		Parrot parrot = new Parrot();
		
		parrot.addPhrase("Hello World");
		parrot.addPhrase("Hello");
		
		parrot.say(2);
	}

}
