#include <iostream>

using namespace std;

class Vehicle {
protected:
    string brand;
    string model;

public:
    Vehicle(string Brand, string Model) {
        brand = Brand;
        model = Model;
    }
};

class Car : protected Vehicle {
public:

    Car(string Brand, string Model) : Vehicle(Brand, Model) {}

    void brandModel(string Brand, string Model) {
        brand = Brand;
        model = Model;
    }
    
    void showData() {
        cout << brand << endl;
        cout << model << endl;
    }
};


int main() {
    Car car1("Toyota", "Corolla");
    car1.showData();

    car1.brandModel("Audi", "A4");
    car1.showData();

    // Car dziedziczy atrybuty chronione brand i model z klasy Vehicle
    // Próba wywołania brand oraz model bezpośrednio w main() powoduje błąd kompilacji
    // cout << car1.brand << endl; 
    // cout << car1.model << endl;

    return 0;
}