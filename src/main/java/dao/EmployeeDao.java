package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {
    private List <Employee> employees;

    public List<Employee> findBySurname(String surname, Session session)
    {
        employees = getEmployees(session, "from Employee where surname=" + surname);
        return employees;
    }

    public List<Employee> findByEmail(String email, Session session)
    {
        employees = getEmployees(session, "from Employee where email= " + email);
        return employees;
    }

    public List<Employee> findById(int id, Session session)
    {
        employees = getEmployees(session, "from Employee where id=" + id);
        return employees;
    }

    public List<Employee> findAllEmployees(Session session)
    {
        employees = getEmployees(session, "from Employee");
        return employees;
    }

    public String add(Employee employee, Session session) {
        try {
            session.save(employee);
            session.getTransaction().commit();
            return "Zapisano użytkownika w bazie";
        } catch (Exception ex) {
            return "Nie udało się zapisać użytkownika w bazie. " + ex.getMessage();
        }
    }

    public String update(int id, Employee employeeNew, Session session) {
        try {
            Employee employee = session.get(Employee.class, id);
            employee.setName(employeeNew.getName());
            employee.setSurname(employeeNew.getSurname());
            employee.setAge(employeeNew.getAge());
            employee.setNrPhone(employeeNew.getNrPhone());
            employee.setEmail(employeeNew.getEmail());

            session.update(employee);
            session.getTransaction().commit();
            return "Aktualizacja przebiegła pomyślnie. ";
        } catch (Exception ex) {
            return "Nie udało się zaktualizować danych użytkownika. " + ex.getMessage();
        }
    }
    public String delete(int id, Session session) {
        try {
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
            return "Użytkownik został usunięty. ";
        } catch (Exception ex) {
            return "Nie udało się usunąć użytkownika. " + ex.getMessage();
        }
    }

    private List <Employee> getEmployees(Session session, String queryString) {
        Query query = session.createQuery(queryString);
        List<Employee> employees = query.list();

        return employees;
    }

}
