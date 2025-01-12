#include <iostream> // poprawnie

using namespace std;

// Interfejs rabatu
class Discount {
public:
    virtual double discount(double price) const = 0;
    virtual ~Discount() = default;
};

// Regularny rabat
class RegularDiscount : public Discount {
public:
    double discount(double price) const override {
        return price = price * 0.9;
    }
};

// Sezonowy rabat
class SeasonalDiscount : public Discount {
public:
    double discount(double price) const override {
        return price = price * 0.8;
    }
};

void putDiscount(const Discount& discount, double& price) {
    cout << discount.discount(price) << endl;
}

int main() {
    double price = 50.0;

    RegularDiscount regularDiscount;
    SeasonalDiscount seasonalDiscount;

    putDiscount(regularDiscount, price);

    putDiscount(seasonalDiscount, price);
    
    return 0;
}
