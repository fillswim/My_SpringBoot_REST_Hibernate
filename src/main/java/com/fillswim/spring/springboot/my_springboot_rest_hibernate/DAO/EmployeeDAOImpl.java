package com.fillswim.spring.springboot.my_springboot_rest_hibernate.DAO;

import com.fillswim.spring.springboot.my_springboot_rest_hibernate.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository()
public class EmployeeDAOImpl implements EmployeeDAO{

    private final EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);

    }

    @Override
    public Employee getEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);

        return employee;
    }


    @Override
    public List<Employee> getAllEmployees() {

        Session session = entityManager.unwrap(Session.class);
        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        return employees;
    }

    @Override
    public void deleteEmployee(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("delete from Employee where id =: employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();


    }
}
