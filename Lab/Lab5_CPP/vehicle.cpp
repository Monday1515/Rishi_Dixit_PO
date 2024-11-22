#include <iostream>

using namespace std;

class Vehicle {
private:
    string brand;
    string model;

public:
    Vehicle(string Brand, string Model) {
        brand = Brand;
        model = Model;
    }

    string getBrand() const {
        return brand;
    }

    string getModel() const {
        return model;
    }

    void setBrand(string Brand) {
        brand = Brand;
    }

    void setModel(string Model) {
        model = Model;
    }
};

class Car : private Vehicle {
public:

    Car(string Brand, string Model) : Vehicle(Brand, Model) {}

    void brandModel(string Brand, string Model) {
        setBrand(Brand);
        setModel(Model);
    }
    
    void showData() {
        cout << getBrand() << endl;
        cout << getModel() << endl;
    }
};


int main() {
    Car car1("Toyota", "Corolla");
    car1.showData();

    car1.brandModel("Audi", "A4");
    car1.showData();

    // Próba wywołania brand oraz model bezpośrednio w main() powoduje błąd kompilacji
    // Metody setBrand i setModel pozwalają na modyfikację prywatnych atrybutów brand i model
    // cout << car1.brand << endl; 
    // cout << car1.model << endl;

    return 0;
}