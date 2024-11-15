#include <iostream>
#include <string>

using namespace std;

class Tablica {
private:
    int* tablica;
    int rozmiar;

public:
    Tablica(int rozmiar) : rozmiar(rozmiar) {
        tablica = new int[rozmiar];
    }

    Tablica() : rozmiar(10) {tablica = new int[rozmiar];}

    ~Tablica() {
        cout << "Destruktor wywołany dla tablicy o rozmiarze " << rozmiar << endl;
        delete[] tablica;
    }

    void add(int indeks, int wartosc) {
        if (indeks >= 0 && indeks < rozmiar) {
            tablica[indeks] = wartosc;
        }
    }

    void print() const {
        for (int i = 0; i < rozmiar; i++) {
            cout << tablica[i] << " ";
        }
        cout << endl;
    }

};

int main() { // Prosze tworzyc obiekty takze w pamieci dynamicznej

    Tablica tablica1;
    tablica1.add(0,1);
    tablica1.add(1,2);
    tablica1.add(2,3);
    tablica1.add(3,4);
    tablica1.add(4,5);
    tablica1.add(5,6);
    tablica1.add(6,7);
    tablica1.add(7,8);
    tablica1.add(8,9);
    tablica1.add(9,10);
    tablica1.print();

    Tablica tablica2(3);
    tablica2.add(0,11);
    tablica2.add(1,12);
    tablica2.add(2,13);
    tablica2.print();

    
    return 0;
}
