#include <iostream>

using namespace std;

// Zadanie 2 i 3 - Wykonanie zadania 1 jest takie same z tym, że metoda accelerate() nie jest metodą wirtualną czysto abstrakcyjną

class Vehicle1 {
public:
    virtual void accelerate() = 0;
    virtual ~Vehicle1() {};
};

class Car1 : public Vehicle1 {
public:
    void accelerate() override {
        cout << "Car accelerates" << endl;
    }
};

class Bicycle1 : public Vehicle1 {
public:
    void accelerate() override {
        cout << "Bicycle accelerates" << endl;
    }
};

// Zadanie 5

class Vehicle2 {
protected:
    string brand;
    string model;

public:
    Vehicle2(string Brand, string Model) {
        brand = Brand;
        model = Model;
    }
};

class Car2 : protected Vehicle2 {
public:
    Car2(string Brand, string Model) : Vehicle2(Brand, Model) {}

    void brandModel(string Brand, string Model) {
        brand = Brand;
        model = Model;
    }
    
    void showData() {
        cout << brand << endl;
        cout << model << endl;
    }
};

class Vehicle3 {
private:
    string brand;
    string model;

public:
    Vehicle3(string Brand, string Model) {
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

// Zadanie 6

class Car3 : private Vehicle3 {
public:
    Car3(string Brand, string Model) : Vehicle3(Brand, Model) {}

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
    // Użycie klas do zadania 2-3
    Vehicle1* vehicle3 = new Car1();
    Vehicle1* vehicle4 = new Bicycle1();
    
    vehicle3->accelerate();
    vehicle4->accelerate();

    delete vehicle3;
    delete vehicle4;

    cout << endl;

    // Użycie klas do zadanie 5 
    Car2 car1("Toyota", "Corolla");
    car1.showData();

    car1.brandModel("Audi", "A4");
    car1.showData();

    // Car dziedziczy atrybuty chronione brand i model z klasy Vehicle
    // Próba wywołania brand oraz model bezpośrednio w main() powoduje błąd kompilacji
    // cout << car1.brand << endl; 
    // cout << car1.model << endl;

    cout << endl;

    //Użycie klas do zadania 6

    Car3 car2("Toyota", "Corolla");
    car2.showData();

    car2.brandModel("Audi", "A4");
    car2.showData();

    // Próba wywołania brand oraz model bezpośrednio w main() powoduje błąd kompilacji
    // Metody setBrand i setModel pozwalają na modyfikację prywatnych atrybutów brand i model
    // cout << car1.brand << endl; 
    // cout << car1.model << endl;

    return 0;
}
