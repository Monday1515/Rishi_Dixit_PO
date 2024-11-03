#include <iostream>   // oba rozwiazania poprawne
#include <vector>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

class Parrot {
private:
    vector<string> phrase;
public:
    Parrot(string Phrase) {
        phrase.push_back(Phrase);
    }

    void say(int repetition = 1) {
        if (!phrase.empty()) {
            for (int i = 0; i < repetition; i++) {
                int random = rand() % phrase.size();
                cout << phrase[random] << endl;
            }
        }
    }

    void addPhrase(string newPhrase) {

        phrase.push_back(newPhrase);

    }


};


int main() {

    srand(time(0));
    Parrot Parrot("Hello World"); 
    Parrot.say(1);


    Parrot.addPhrase("Hello World");
    Parrot.addPhrase("Hello");
    
    Parrot.say(1);
    Parrot.say(1);
    return 0;
}
