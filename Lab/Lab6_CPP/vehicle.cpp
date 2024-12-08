#include <iostream>  // rozwiązanie poprawne
#include <vector>
#include <memory>

using namespace std;

class Vehicle {
public:
    virtual void accelerate() {
        cout << "vehicle accelerates" <<endl;
    }

    virtual void stop() {
        cout << "vehicle stops" <<endl;
    }

    virtual ~Vehicle() {};
};

class Car : public Vehicle {
public:
    void accelerate() override {
        cout << "car accelerates" << endl;
    }

    void stop() override {
        cout << "car stops" << endl;
    }

     ~Car() {}
};

class Bicycle : public Vehicle {
public:
    void accelerate() override {
        cout << "bicycle accelerates" << endl;
    }

    void stop() override {
        cout << "bicycle stops" << endl;
    }

    ~Bicycle() {}
};

class Motorbike : public Vehicle {
public:
    void accelerate() override {
        cout << "motorbike accelerates" << endl;
    }

    void stop() override {
        cout << "motorbike stops" << endl;
    }

    ~Motorbike() {}
};

class Scooter : public Vehicle {
public:
    void accelerate()  {
        cout << "scooter accelerates" << endl;
    }

    void stop() {
        cout << "scooter stops" << endl;
    }
};

int main() {

    std::vector<std::unique_ptr<Vehicle>> vehicles;

    vehicles.push_back(make_unique<Car>());
    vehicles.push_back(make_unique<Bicycle>());
    vehicles.push_back(make_unique<Motorbike>());
    vehicles.push_back(make_unique<Scooter>());

    int i;

    for (i = 0; i<4; i++) {
        vehicles[i]->accelerate();
        vehicles[i]->stop();
    }

    return 0;
}
