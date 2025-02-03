public class TestPayroll {
    public static void main(String[] args) {
        // Create a Payroll object
        Payroll payroll = new Payroll();

        // Test size() method
        System.out.println("Initial size: " + payroll.size() + " (Expected: 0) " + (payroll.size() == 0 ? "Passed" : "Failed"));

        // Test add_employee() method
        Employee emp1 = new Employee();
        emp1.name = "John Doe";
        emp1.ID = 1;
        emp1.salary = 10000;
        payroll.add_employee(emp1);

        Employee emp2 = new Employee();
        emp2.name = "Jane Doe";
        emp2.ID = 2;
        emp2.salary = 20000;
        payroll.add_employee(emp2);

        System.out.println("Size after adding employees: " + payroll.size() + " (Expected: 2) " + (payroll.size() == 2 ? "Passed" : "Failed"));

        // Test print() method
        System.out.println("Payroll after adding employees:");
        payroll.print();

        // Test find_employee() method
        try {
            int index = payroll.find_employee("Jane Doe");
            System.out.println("Jane Doe found at index: " + index + " (Expected: 1) " + (index == 1 ? "Passed" : "Failed"));
        } catch (EmployeeNotFoundException e) {
            System.out.println("Employee not found, Failed");
        }

        // Test remove_employee() method
        try {
            payroll.remove_employee(0);
            System.out.println("Size after removing employee 0: " + payroll.size() + " (Expected: 1) " + (payroll.size() == 1 ? "Passed" : "Failed")); // Expected: 1
        } catch (EmployeeIndexException e) {
            System.out.println("Invalid index");
        }

        System.out.println("Payroll after removing employee 0:");
        payroll.print();

        // Test copy_payroll() method
        Payroll payrollCopy = new Payroll();
        payrollCopy.copy_payroll(payroll);
        System.out.println("Copied payroll:");
        payrollCopy.print();

        // Test add_payroll() method
        Payroll anotherPayroll = new Payroll();
        Employee emp3 = new Employee();
        emp3.name = "Jack Doe";
        emp3.ID = 3;
        emp3.salary = 30000;
        anotherPayroll.add_employee(emp3);
        Employee emp4 = new Employee();
        emp4.name = "Jill Doe";
        emp4.ID = 4;
        emp4.salary = 40000;
        anotherPayroll.add_employee(emp4);

        payroll.add_payroll(anotherPayroll);
        System.out.println("Payroll after adding another payroll:");
        payroll.print();
    }
}
