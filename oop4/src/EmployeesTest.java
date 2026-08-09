import oop4.Employee;
import oop4.Person;

public class EmployeesTest {
    public static void main(String[] args) {

        Person someone = new Person("Guido", 123);
        System.out.println(someone);
        String text = "this is an person " + someone;
        System.out.println(text);

        System.out.println(someone.greet());
        System.out.println("-----");
        //p i of type Person, but holds an Employee instance
        Person p = new Employee("Nicholas", 456, 1000);
        System.out.println(p.greet());

        //System.out.println(p.getSalary()); // oops, compilation error
        System.out.println(); // gain access to Employees methods

        //cast is not always possible
        //System.out.println(((Employee) someone).getVacationPeriod()); // runtime error: someone is a Person, not an Employee
        if(someone instanceof Employee) { //instanceof operator
            System.out.println(((Employee) someone).getVacationPeriod());
        }
        if(p instanceof Employee) {
            System.out.println(((Employee) p).getVacationPeriod());
        }

        //EMployee e = new Person();//compilation error: NOT all Person instances are employees
    }
}