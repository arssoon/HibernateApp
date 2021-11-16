import dao.EmployeeDao;
import entity.Employee;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
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
            entity.Employee employee = new entity.Employee();
            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            System.out.println("********************************************");
            System.out.print("Twoj wybor : ");
            int option = scan.nextInt();
            switch (option) {
                case 1: {
                    /******   POBIERANIE WSZYSTKICH PRACOWNIKOW   ******/
                    employeeDao.findAllEmployees();
                    break;
                }
                case 2: {
                    /******   POBIERANIE PRACOWNIKA PO ADRESIE EMAIL   ******/
                    System.out.print("Pobranie pracownika po adresie email. \nPodaj email : ");
                    employeeDao.findByEmail("'" + scan2.nextLine() + "'");
                    break;
                }
                case 3: {
                    /******   POBIERANIE PRACOWNIKA PO NAZWISKU   ******/
                    System.out.print("Pobranie pracownika po nazwisku. \nPodaj nazwisko : ");
                    employeeDao.findBySurname("'" + scan2.nextLine() + "'");
                    break;
                }
                case 4: {
                    /******   POBIERANIE PO ID   ******/
                    System.out.print("Pobranie pracownika po id. Podaj id : ");
                    employeeDao.findById(scan.nextInt());
                    break;
                }
                case 5: {
                    /******   DODAWANIE   ******/
                    System.out.println("Dodawanie pracownika. Wpisz potrzebne pola.");
                    dataEmployee(employee, scan, scan2);

                    employeeDao.add(employee);
                    break;
                }
                case 6: {
                    /******   AKTUALIZOWANIE   ******/
                    System.out.println("Aktualizowanie pracownika. Wpisz potrzebne pola.");
                    System.out.print("Id : ");
                    int id = scan.nextInt();
                    dataEmployee(employee, scan, scan2);
                    employeeDao.update(id, employee);
                    break;
                }
                case 7: {
                    /******   USUWANIE   ******/
                    System.out.println("Usuwanie po ID. Podaj ID : ");
                    int id = scan.nextInt();
                    employeeDao.delete(id);
                    break;
                }
                case 8:
                    return;
                default: {
                    System.out.println("Wybrano zla opcje.");
                }
            }
        }
    }

    private static void dataEmployee(Employee employee, Scanner scan, Scanner scan2) {
        System.out.print("Imie : ");
        employee.setName(scan2.nextLine());
        System.out.print("Nazwisko : ");
        employee.setSurname(scan2.nextLine());
        System.out.print("Wiek : ");
        employee.setAge(scan.nextInt());
        System.out.print("Nr telefonu : ");
        employee.setNrPhone(scan2.nextLine());
        System.out.print("Email : ");
        employee.setEmail(scan2.nextLine());
    }
}