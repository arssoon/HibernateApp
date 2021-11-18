package entity;

import javax.persistence.*;

@Entity
@Table(name = "pracownicy")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prac_id")
    private int id;

    @Column(name = "prac_imie")
    private String name;

    @Column(name = "prac_nazwisko")
    private String surname;

    @Column(name = "prac_wiek")
    private int age;

    @Column(name = "prac_nr_telefonu")
    private String nrPhone;

    @Column(name = "prac_email")
    private String email;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNrPhone() {
        return nrPhone;
    }

    public void setNrPhone(String nrPhone) {
        this.nrPhone = nrPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "id = " + id + " | " +
                "name = " + name + " | " +
                "surname = " + surname + " | " +
                "age = " + age + " | " +
                "nr_phone = " + nrPhone  + " | " +
                "email = " + email;
    }
}
