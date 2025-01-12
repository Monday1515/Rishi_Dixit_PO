#include <iostream> // poprawnie; ale, dlaczego tylko 2 zadanie z pięciu?
#include <string>
#include <fstream>

using namespace std;

// Klasa użytkownika
class User {
private:
    string name;
    string email;
    string phoneNumber;

public:
    User(const string& name, const string& email, const string& phoneNumber) : name(name), email(email), phoneNumber(phoneNumber) {}

    string getName() const {
        return name;
    }

    string getEmail() const {
        return email;
    }

    string getPhoneNumber() const {
        return phoneNumber;
    }

    void setName(const string& newName) {
        name = newName;
    }

    void setEmail(const string& newEmail) {
        email = newEmail;
    }

    void setPhoneNumber(const string& newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

};


// Klasa odpowiedzialna za zapis użytkownika
class UserSaver {
private:
    string File;

public:

    UserSaver(const string& File) : File(File) {}

    void saveUser(const User& user) {
        fstream file(File, ios::app);
        
        if (file.is_open()) {
            file << "Name: " << user.getName() << endl << "Email: " << user.getEmail() << endl << "Phone Number: " << user.getPhoneNumber() << "\n" << endl;
            file.close();
        } 
    }
};

int main() {
    
    User user("Jan Nowak", "jan.nowak@abc.pl", "123456789");

    User user2("Pawel Kowalski", "pawel.kowalski@abc.pl", "987654321");

    UserSaver saver("file1.txt");

    saver.saveUser(user);
    saver.saveUser(user2);
    return 0;
}
