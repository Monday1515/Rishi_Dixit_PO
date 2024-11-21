#include <iostream>

using namespace std;

class Employee {
protected:
    string position;
    float salary;

    virtual void showData() const {
        cout << "Position: " << position << endl;
        cout << "Salary: " << salary << endl;
    }

    Employee(string Position, float Salary) {
        position = Position;
        salary = Salary;
    }

    virtual void showData() const {
        cout << "Position: " << position << endl;
        cout << "Salary: " << salary << endl;
    }

    public:
    virtual ~Employee() {}
};

class Teacher : public Employee {
public:
    Teacher(string Position, float Salary) : Employee(Position, Salary) {}

    void showData() const override {
        cout << "Position: " << position << endl;
        cout << "Salary: " << salary << endl;
    }
};

class Administration : public Employee {
public:
    Administration(string Position, float Salary) : Employee(Position, Salary) {}

    void showData() const override {
        cout << "Position: " << position << endl;
        cout << "Salary: " << salary << endl;
    }
};

int main() {
    Teacher teacher1("Nauczyciel Matematyki", 5220.50);
    Administration administration1("Technik", 4500.75);

    teacher1.showData();
    administration1.showData();

    return 0;
}
