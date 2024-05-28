package listproblems;

import common.layer.models.Employee;
import services.DummyDataService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ListExample1 {
    public static void main(String[] args) throws ParseException {
        List<String> list = Arrays.asList("Rajat"); //This method returns a non-resizable List backed by the array.
        //list.add("Zade");

        DummyDataService service = new DummyDataService();

        List<Employee> list1 = new ArrayList<>();
        List<Employee> list2 = new ArrayList<>();
        list1.add(Employee.builder().id(101).name("Rajat").department("IT").salary(220000.0).dateOfJoining(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-01")).build());
        list2.add(Employee.builder().id(101).name("Rajat").department("IT").salary(220000.0).dateOfJoining(new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-01")).build());
        Set<Employee> set = new HashSet<>(list2);

        List<Employee> list3 = list1.stream().filter(set::contains).collect(Collectors.toList());
        System.out.println(list3);

        Properties properties = new Properties();
        try (InputStream input = ListExample1.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
            String property = properties.getProperty("my.property");
            System.out.println("Property value: " + property);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
