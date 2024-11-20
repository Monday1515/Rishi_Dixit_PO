#include <iostream>
#include <regex>

using namespace std;

class Person {
private: 
    string name;
    string surname;
    int age;
    string email;
    string number;

    public:
    string getName() const {
        return name;
    }

    string getSurname() const {
        return surname;
    }

    int getAge() const {
        return age;
    }

    string getEmail() {
        return email;
    }

    string getNumber() {
        return number;
    }


public:
void setName(const string& Name) {
        name = Name;
    }

void setSurname(const string& Surname) {
        surname = Surname;
    }

void setAge(int Age) {
            age = Age;
    }

void setEmail(const string& Email) {
        regex pattern(R"(\w+@\w+)"); 
        if (regex_search(Email, pattern)) {
            email = Email;
        } 
        
        else {
            cout << "Błędny email" << endl;
        }
    }

void setNumber(const string& Number) {
    regex pattern(R"(^\d{9}$)");
    if (regex_search(Number, pattern) == true) {
        number = Number;
    } 
    
    else {
        cout << "Błędny numer" << endl;
    }
}
};

int main() {
    Person person1;

    person1.setName("Jan");
    person1.setSurname("Kowalski");
    person1.setAge(21);
    person1.setEmail("jan.kowalski@123.pl");
    person1.setNumber("123456789");

    cout << person1.getName() << endl;
    cout << person1.getSurname() << endl;
    cout << person1.getAge() << endl;
    cout << person1.getEmail() << endl;
    cout << person1.getNumber() << endl;

    return 0;
}