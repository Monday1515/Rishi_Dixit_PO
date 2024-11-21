class Employee {
    protected String position;
    protected float salary;

    public Employee(String position, float salary) {
        this.position = position;
        this.salary = salary;
    }

    public void showData() {
        System.out.println("Position: " + position);
        System.out.println("Salary: " + salary);
    }
}

class Teacher extends Employee {
    public Teacher(String position, float salary) {
        super(position, salary); 
    }
}

class Administration extends Employee {
    public Administration(String position, float salary) {
        super(position, salary); 
    }
}


public class Main {
    public static void main(String[] args) {

        Teacher teacher1 = new Teacher("Nauczyciel Matematyki", 5220.50f);
        Administration administration1 = new Administration("Technik", 4500.75f);

        teacher1.showData();
        administration1.showData();
    }
}
