#include <iostream>

using namespace std;

class Vehicle {
public:
    string brand;
    string model;

    Vehicle(string Brand, string Model) {
       brand = Brand;
       model = Model;
    }

    virtual void accelerate() {
        cout << "Vehicle accelerates" << endl;
    }

};

class Car : public Vehicle {
public:

    Car(string Brand, string Model) : Vehicle(Brand, Model) {}

    void accelerate() override {
        cout << "Car accelerates" << endl;
    }
    
};

class ElectricCar : public Car {
public:
    ElectricCar(string Brand, string Model) : Car(Brand, Model) {}

    void accelerate() override {
        cout << "Electric car accelerates faster";
    }
};


int main() {
    Vehicle vehicle1("Toyota", "Corolla");
    vehicle1.accelerate();

    ElectricCar electricCar1("Tesla", "S");
    electricCar1.accelerate();

    return 0;
}