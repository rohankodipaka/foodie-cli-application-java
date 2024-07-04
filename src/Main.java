import com.javaproject.foodiecliapplication.repository.CustomerRepository;
import com.javaproject.foodiecliapplication.ui.Menu;
import com.javaproject.foodiecliapplication.util.CsvReader;

public class Main {
    public static void main(String[] args) {

        /* CsvReader csvReader = new CsvReader();
        System.out.println(csvReader.readCustomerFromCsv()); */

        // CustomerRepository customerRepository = new CustomerRepository();
        // System.out.println(customerRepository.getAllCustomers());

        // 1. check customer registration and login.
        Menu menu = new Menu();
        menu.displayMainMenu();

    }
}
