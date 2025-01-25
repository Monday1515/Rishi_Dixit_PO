import java.io.FileWriter;  // poprawnie
import java.io.IOException;
import java.io.PrintWriter;

class User {
    private String name;
    private String email;
    private String phoneNumber;

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }
}

class UserSaver {
    private String file;

    public UserSaver(String file) {
        this.file = file;
    }

    public void saveUser(User user) {
        try (FileWriter fw = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println("Name: " + user.getName());
            writer.println("Email: " + user.getEmail());
            writer.println("Phone Number: " + user.getPhoneNumber());
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class SRP {
    public static void main(String[] args) {
        User user1 = new User("Jan Nowak", "jan.nowak@abc.pl", "123456789");
        User user2 = new User("Pawel Kowalski", "pawel.kowalski@abc.pl", "987654321");

        UserSaver saver = new UserSaver("file1.txt");

        saver.saveUser(user1);
        saver.saveUser(user2);
    }
}
