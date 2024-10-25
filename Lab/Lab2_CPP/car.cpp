#include <iostream>
#include <string>

using namespace std;

class Samochód {
    string marka;
    string model;
    int rokProdukcji;

public:

    Samochód(string Marka, string Model, int RokProdukcji) {
        marka = Marka;
        model = Model;
        rokProdukcji = RokProdukcji;
    }

    Samochód() {
        marka = "Audi";
        model = "A4";
        rokProdukcji = 2018;
    }

    ~Samochód() {
        cout << "Destruktor wywołany dla: " << marka << " " << model << endl;
    }


    void printInfo() {
        cout << marka << endl;
        cout << model << endl;
        cout << rokProdukcji << endl << endl;

    }
};
 
int main() {
    Samochód samochód1("Toyota", "Corolla", 2020);
    samochód1.printInfo();

    Samochód samochód2;
    samochód2.printInfo();
    return 0;
}