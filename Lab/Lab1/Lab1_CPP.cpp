#include <iostream>
#include <string>

using namespace std;

class Car {
    string make;
    string model;
    int year;

public:
    Car(string carMake, string carModel, int carYear) {
        make = carMake;
        model = carModel;
        year = carYear;
        }

    void displayInfo() {
        cout << make << endl;
        cout << model << endl;
        cout << year << endl; 
    }
};

int main() {

    Car myCar("Toyota", "Corolla", 2020);
    
    myCar.displayInfo();

    return 0;
}