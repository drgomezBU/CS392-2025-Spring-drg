/*
 * The Payroll class consists of a set of Employees, stored in the people
 * variable. The constructor will allocate an array of some positive size to
 * people. This size is remembered in the private member variable maximum_size.
 * Later, a private member function can double the size of the payroll as
 * needed. (We will not worry about decreasing the size of the payroll.) Only
 * the initial part of the array will store actual Employees: you should store
 * the number of actual Employees in the private variable current_size.
 */
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    private Employee people[];
    private int maximum_size;
    private int current_size;

    /*
     * --default constructor; Allocate a payroll of some positive size (you decide
     * how large) to the people variable, set the maximum_size appropriately, and
     * the current_size to zero.
     */
    public Payroll() {
        maximum_size = 5;
        current_size = 0;

        people = new Employee[maximum_size];
    }

    /*
     * --return current_size.
     */
    public int size() {
        return current_size;
    }

    /*
     * --print the Employees currently on the Payroll, one per line.
     */
    public void print() {
        for (Employee employee : people) {
            if (employee == null) break;
            System.out.println(
                    "ID Number : " + employee.ID + ", Name :" + employee.name + ", Salary: " + employee.salary);
        }
    }

    /*
     * --add a new Employee to the payroll, making more room on the payroll if
     * necessary.
     */
    public void add_employee(Employee newbie) {
        // Check if adding a new employee will exceed the size of the array
        // Increase size of array if needed
        if (current_size + 1 > maximum_size)
            double_size();

        // Assign new employee a location in the array
        // Then increment the running employee count
        people[current_size] = newbie;
        current_size++;
    }

    /*
     * --remove Employee in people[i], shifting other Employees over to fill its
     * place (or just replacing it with the last Employee: remove is not guaranteed
     * to keep order). Throw an exception if there is no entry at position i.
     */
    public void remove_employee(int i) throws EmployeeIndexException {
        // Check if int i is a valid index
        if (i >= current_size || i < 0)
            throw new EmployeeIndexException();

        // Updates the array by shifting all values right of index i one index to the
        // left
        for (int j = i; j < current_size - 1; j++)
            people[j] = people[j + 1];
        people[current_size-1] = null;

        // Decement current_size
        current_size--;
    }

    /*
     * --return the first i such that people[i].name == target_name. Throw an
     * exception if no such i is found.
     */
    public int find_employee(String name) throws EmployeeNotFoundException {
        for (int i = 0; i < people.length; i++) {
            try {
                if (people[i].name.equals(name))
                    return i;
            } catch (Exception e) {
                if (people[i] == null)
                    break;
            }
        }
        throw new EmployeeNotFoundException();
    }

    /*
     * --combine two Payrolls into one. Do not worry about repeated items.
     */
    public void add_payroll(Payroll source) {
        while (source.current_size + current_size > maximum_size)
            double_size();
        for (int i = 0; i < source.current_size; i++)
            people[current_size + i] = source.people[i];
        current_size += source.current_size;
    }

    /*
     * --assign one Payroll to another.
     */
    public void copy_payroll(Payroll source) {
        maximum_size = source.maximum_size;
        current_size = source.current_size;
        people = source.people;
    }

    /*
     * --double the size of people[] and maximum_size
     */
    private void double_size() {
        maximum_size *= 2;
        Employee temp_array[] = new Employee[maximum_size];
        for (int i = 0; i < people.length; i++) {
            temp_array[i] = people[i];
        }
        people = temp_array;
    }
}
