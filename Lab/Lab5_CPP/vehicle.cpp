#include <iostream>

using namespace std;

class Vehicle {
public:

    virtual void accelerate() {
        cout << "Vehicle accelerates" << endl;
    }

    void stop() {
        cout << "Vehicle stops" << endl;
    }

    virtual ~Vehicle() {}
};

class Car : public Vehicle {
public:

    void accelerate() override {
        cout << "Car accelerates" << endl;
    }
    
};

class Bicycle : public Vehicle {
public:

    void accelerate() override {
        cout << "Bicycle accelerates" << endl;
    }
};

int main() {
    Vehicle* vehicle1 = new Car();
    Vehicle* vehicle2 = new Bicycle();
    
    vehicle1 -> accelerate();
    vehicle2 -> accelerate();

    vehicle1 -> stop();
    vehicle2 -> stop();

    delete vehicle1;
    delete vehicle2;

    return 0;
}