#include <iostream>

using namespace std;

class Parrot {
private:
    string phrase;
public:
    Parrot(string Phrase) {
        phrase = Phrase;
    }

    void say(int repetition){
        for (int i=0; i< repetition; i++) {
            cout << phrase << endl;
        }
    }

    void change(string newPhrase) {
        phrase = newPhrase;
    }
};

int main() {

    Parrot Parrot("Hello World"); 
    Parrot.say(1);

    Parrot.change("Hello");
    Parrot.say(3);
    
    return 0;
}