#include <iostream> // trudno przypisać te rozwiązania do poszczególnych zadań; na przyszłość, proszę wyraźnie wskazywać numery (!) zadań

using namespace std;

class Engine {
public:
    void accelerate() {
        cout << "car accelerates" << endl;
    }
};

class Electric {
public:
    void charge() {
        cout << "car charges the battery" << endl;
    }
};

class Hybrid : public Engine, public Electric{

};

int main() {
    Hybrid hybrid1;

    hybrid1.accelerate();
    hybrid1.charge();
    return 0;
}
