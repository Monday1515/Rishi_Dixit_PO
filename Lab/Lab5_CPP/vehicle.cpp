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

    virtual ~Vehicle() {}

};

class Engine : virtual public Vehicle {
public:

    Engine(string Brand, string Model) : Vehicle(Brand, Model) {}

    void accelerate() override {
        cout << "Car accelerates" << endl;
    }

};

class Electric : virtual public Vehicle {
public:
    Electric(string Brand, string Model) : Vehicle(Brand, Model) {}

    void accelerate() override {
        cout << "Electric car accelerates faster" << endl;
    }
};

class Hybrid : public Engine, public Electric {
public:
    Hybrid(string Brand, string Model) : Vehicle(Brand, Model), Engine(Brand, Model), Electric(Brand, Model) {}

    void accelerate() override {

        Engine :: accelerate();
        Electric :: accelerate();

    }
};

int main() {

    Hybrid hybrid1("Toyota", "Corolla");
    hybrid1.accelerate();  

    return 0;
}
