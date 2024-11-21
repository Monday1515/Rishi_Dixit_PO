import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Person {
private String name;
private String surname;
private int age;
private String email;
private String number;
	
public String getName() {
	return name;
}
	
	public String getSurname() {
		return surname;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setEmail(String email) {
		
		 if (email.contains("@")) {
		     this.email = email;
		 } 
		 
		 else {
			 System.out.println("Błędny email");
		 }
	}
	
	public void setNumber(String number) {
		 Pattern pattern = Pattern.compile("^\\d{9}$");
		 Matcher matcher = pattern.matcher(number);

		 if (matcher.matches()) {
		     this.number = number;
		 } 
		 
		 else {
			 System.out.println("Błędny numer");
		 }
	}
	
	public static void main(String[] args) {
		
		Person person1 = new Person();
		
		person1.setName("Jan");
		person1.setSurname("Kowalski");
		person1.setAge(21);
		person1.setEmail("jan.kowalski123.pl");
		person1.setNumber("123456789");
		
		System.out.println(person1.getName());
		System.out.println(person1.getSurname());
		System.out.println(person1.getAge());
		System.out.println(person1.getEmail());
		System.out.println(person1.getNumber());
		
	}

}
