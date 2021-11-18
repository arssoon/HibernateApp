import dao.EmployeeDao;
import entity.Employee;
import factory.HibernateFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class App {
//    static Logger log = Logger.getLogger(Logger.class.getName());

    public static void main(String[] args) {

        try {
            SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
            Session session = sessionFactory.openSession();

            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> employees;
            System.out.println("Baza danych PRACOWNICY");
            System.out.println("OPCJE: " + "\n" +
                    "1. Wykaz wszystkich pracowników" + "\n" +
                    "2. Pobranie pracownika po adresie email" + "\n" +
                    "3. Pobieranie pracownika po nazwisku" + "\n" +
                    "4. Pobieranie pracownika po id" + "\n" +
                    "5. Dodanie pracownika do bazy" + "\n" +
                    "6. Aktualizacja istniejącego pracownika" + "\n" +
                    "7. Usuwanie pracownika z bazy" + "\n" +
                    "8. Wyjscie"
            );

            while (true) {
                Scanner scan = new Scanner(System.in);
                session.beginTransaction();
                Employee employee = new Employee();

                System.out.println("********************************************");
                System.out.print("Twoj wybor : ");
                int option = scan.nextInt();

                switch (option) {
                    case 1: {
                        employees = employeeDao.findAllEmployees(session);
                        showEmployees(employees);
                        session.getTransaction().commit();
                        break;
                    }
                    case 2: {
                        System.out.print("Pobranie pracownika po adresie email. \nPodaj email : ");
                        employees = employeeDao.findByEmail("'" + scan.next() + "'", session);
                        showEmployees(employees);
                        session.getTransaction().commit();
                        break;
                    }
                    case 3: {
                        System.out.print("Pobranie pracownika po nazwisku. \nPodaj nazwisko : ");
                        employees = employeeDao.findBySurname("'" + scan.next() + "'", session);
                        showEmployees(employees);
                        session.getTransaction().commit();
                        break;
                    }
                    case 4: {
                        System.out.print("Pobranie pracownika po id. Podaj id : ");
                        employees = employeeDao.findById(scan.nextInt(), session);
                        showEmployees(employees);
                        session.getTransaction().commit();
                        break;
                    }
                    case 5: {
                        System.out.println("Dodawanie pracownika. Wpisz potrzebne pola.");
                        inputDataEmployee(employee, scan);

                        System.out.println(employeeDao.add(employee, session));
                        break;
                    }
                    case 6: {
                        System.out.println("Aktualizowanie pracownika. Wpisz potrzebne pola.");
                        System.out.print("Id : ");
                        int id = scan.nextInt();
                        inputDataEmployee(employee, scan);
                        System.out.println(employeeDao.update(id, employee, session));
                        break;
                    }
                    case 7: {
                        System.out.println("Usuwanie po ID. Podaj ID : ");
                        int id = scan.nextInt();
                        System.out.println(employeeDao.delete(id, session));
                        break;
                    }
                    case 8:
                        session.close();
                        sessionFactory.close();
                        return;
                    default: {
                        System.out.println("Wybrano zla opcje.");
                        session.close();
                        sessionFactory.close();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Nie uzyskano połączenia z baza." + ex.getMessage());
//            log.error("Nie uzyskano połączenia z baza." + ex.getMessage());
        }
    }

    private static void showEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e.toString());
        }
    }

    private static void inputDataEmployee(Employee employee, Scanner scan) {
        System.out.print("Imie : ");
        employee.setName(scan.next());
        System.out.print("Nazwisko : ");
        employee.setSurname(scan.next());
        System.out.print("Wiek : ");
        employee.setAge(scan.nextInt());
        System.out.print("Nr telefonu : ");
        employee.setNrPhone(scan.next());
        System.out.print("Email : ");
        employee.setEmail(scan.next());
    }
}