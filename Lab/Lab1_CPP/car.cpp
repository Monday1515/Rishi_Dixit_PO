#include <iostream>
#include <string>

using namespace std;

class Car {
    string make;
    string model;
    int year;
    int currentyear;

public:
    Car(string Make, string Model, int Year, int CurrentYear) {
        make = Make;
        model = Model;
        year = Year;
        currentyear = CurrentYear;
    }

    void displayInfo() {
        cout << make << endl;
        cout << model << endl;
        cout << year << endl;
    }

    int getCarAge() {
        int carAge = currentyear - year;
        return carAge;
    }

    bool isSameCar(Car otherCar) {
        if (make == otherCar.make && model == otherCar.model && year == otherCar.year) {
            return true;
        }
        else {
            return false;
        }
    }
};

int main() {

    Car Car1("Toyota", "Corolla", 2020, 2024);

    Car1.displayInfo();
    cout << Car1.getCarAge() << endl;

    Car Car2("Audi", "A4", 2018, 2024);
    Car Car3("Toyota", "Corolla", 2020, 2024);

    if (Car1.isSameCar(Car2) == true) {
        cout << "true" << endl;
    }
    else {
        cout << "false" << endl;
    }

    if (Car1.isSameCar(Car3) == true) {
        cout << "true" << endl;
    }
    else {
        cout << "false" << endl;
    }

    return 0;
}