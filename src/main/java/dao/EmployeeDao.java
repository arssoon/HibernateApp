package dao;

import entity.Employee;
import factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {

    public void add(Employee employee) {
        HibernateFactory hibernateFactory = new HibernateFactory();
        Session session = hibernateFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(employee);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public void findBySurname(String surname)
    {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where prac_nazwisko=" + surname);
        showDataEmployees(sessionFactory, session, query);
    }

    public void findByEmail(String email)
    {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where prac_email= " + email);
        showDataEmployees(sessionFactory, session, query);
    }

    public void findById(int id)
    {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where prac_id=" + id);
        showDataEmployees(sessionFactory, session, query);
    }

    public void findAllEmployees()
    {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee");
        showDataEmployees(sessionFactory, session, query);
    }

    public void update(int id, Employee employeeNew) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);

        employee.setName(employeeNew.getName());
        employee.setSurname(employeeNew.getSurname());
        employee.setAge(employeeNew.getAge());
        employee.setNrPhone(employeeNew.getNrPhone());
        employee.setEmail(employeeNew.getEmail());

        session.update(employee);

        session.getTransaction().commit();
        sessionFactory.close();
    }
    public void delete(int id) {
        SessionFactory sessionFactory = HibernateFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        session.delete(employee);

        session.getTransaction().commit();
        sessionFactory.close();
    }

    private void showDataEmployees(SessionFactory sessionFactory, Session session, Query query) {
        List<Employee> employees = query.list();
        for (Employee employee : employees) {
            System.out.println(employee.getId() + " " +
                    employee.getName() + " " +
                    employee.getSurname() + " " +
                    employee.getAge() + " " +
                    employee.getNrPhone() + " " +
                    employee.getEmail()
            );
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }

}
