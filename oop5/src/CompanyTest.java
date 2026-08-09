import oop5.*;

public class CompanyTest {

    public static void main(String[] args) {

        Employee[] company = new Employee[5];
        company[0] = new Employee("Homer", 123, 1000, 8, 360 );
        company[1] = new Employee("Carl", 345, 2000, 8, 360 );
        company[2] = new Manager("Simmons", 678, 12000, 8, 360, 2000 );
        company[3] = new Employee("Lenny", 765, 2000, 8, 60 );
        company[4] = new Manager("Smithers", 998, 13500, 8, 360, 4000 );

        //print whole payroll
        System.out.println("all plain employees + managers:");
        int plainEmployeeCount = 0;
        int managerCount = 0;
        for (int i = 0; i < company.length; i++) {
            if(company[i] instanceof Manager) {
                managerCount++;
            } else {
                plainEmployeeCount++;
            }
        }
        System.out.println("Employees " + plainEmployeeCount);
        System.out.println("Managers " + managerCount);

        //smithers deserves a break
        ((Manager)company[4]).workLessHours(4);//carefull

        //hours
        System.out.println("working hours report:");
        for (int i = 0; i < company.length; i++) {
            System.out.println(company[i].getName() + " - hours:" + company[i].getDailyHours());
        }
        System.out.println("--------------");
        //calculate vacation period
        System.out.println("vacations report:");
        for(int i = 0; i < company.length; i++) {
            System.out.println(company[i].getName() + " - " + " Days off:" + company[i].calculateVacationPeriod());
        }
        System.out.println("--------------");
        //who has a bonus?? only managers
        System.out.println("bonus report:");
        for (int i = 0; i < company.length; i++) {
            // i=0, i=1, i=2 ...
            //((Manager)company[2]).getBonus(); // oops!
            if(company[i] instanceof Manager) {
                System.out.println(company[i].getName() + " - " + ((Manager)company[i]).getBonus());
            }
        }
    }
}