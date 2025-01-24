
interface Discount {
    double discount(double price);
}

class RegularDiscount implements Discount {
    @Override
    public double discount(double price) {
        return price * 0.9;
    }
}

class SeasonalDiscount implements Discount {
    @Override
    public double discount(double price) {
        return price * 0.8;
    }
}

public class OCP {
    public static void putDiscount(Discount discount, double price) {
        System.out.println(discount.discount(price));
    }

    public static void main(String[] args) {
        double price = 50.0;

        RegularDiscount regularDiscount = new RegularDiscount();
        SeasonalDiscount seasonalDiscount = new SeasonalDiscount();

        putDiscount(regularDiscount, price);

        putDiscount(seasonalDiscount, price);
    }
}
