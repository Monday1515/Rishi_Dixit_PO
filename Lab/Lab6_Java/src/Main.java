import java.util.ArrayList;

public class Main {

    abstract static class Vehicle {
        public abstract void accelerate();
        public abstract void stop();
    }

    static class Car extends Vehicle {
        @Override
        public void accelerate() {
            System.out.println("car accelerates");
        }

        @Override
        public void stop() {
            System.out.println("car stops");
        }
    }

    static class Bicycle extends Vehicle {
        @Override
        public void accelerate() {
            System.out.println("bicycle accelerates");
        }

        @Override
        public void stop() {
            System.out.println("bicycle stops");
        }
    }

    static class Motorbike extends Vehicle {
        @Override
        public void accelerate() {
            System.out.println("motorbike accelerates");
        }

        @Override
        public void stop() {
            System.out.println("motorbike stops");
        }
    }

    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car());
        vehicles.add(new Bicycle());
        vehicles.add(new Motorbike());

        for (Vehicle vehicle : vehicles) {
            vehicle.accelerate();
            vehicle.stop();
        }
    }
}
