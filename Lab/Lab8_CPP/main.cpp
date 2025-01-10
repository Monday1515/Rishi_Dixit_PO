#include <iostream>
#include <string>
#include <windows.h> 

using namespace std;

class Vehicle {
public:
    virtual void build() = 0;
    virtual ~Vehicle() {} 
};

class Car : public Vehicle {
public:
    void build() override {
        cout << "Car production starts\n";
        Sleep(2000);
        cout << "Car production finished\n";
    }
};

class Bicycle : public Vehicle {
public:
    void build() override {
        cout << "Bicycle production starts\n";
        Sleep(2000);
        cout << "Bicycle production finished\n";
    }
};

class VehicleFactory {
public:
    static Vehicle* create(const string& x) {
        if (x == "car") {
            return new Car();
        } 
        else if (x == "bicycle") {
            return new Bicycle();
        } 
        else {
            return nullptr;
        }
    }
};

int main() {
    Vehicle* car = VehicleFactory::create("car");
    car->build();
    delete car;


    Vehicle* bicycle = VehicleFactory::create("bicycle");
    bicycle->build();
    delete bicycle;

}