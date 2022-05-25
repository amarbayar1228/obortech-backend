package kara.diamond.billing.service.logic;


import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.EmployeeEntity;
import kara.diamond.billing.service.entity.Users;
import kara.diamond.billing.service.iinterfaces.EmployeeInterfaces;
import kara.diamond.billing.service.model.request.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeLogic  extends BaseDatabaseService implements EmployeeInterfaces {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeLogic.class);
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveEmployee(Employee emp) throws Exception {
        //post
        String result = "" ;
        try {
            EmployeeEntity employee1 = new EmployeeEntity();
            employee1.setPkId(NumericHelper.generateKey());
            employee1.setFirstName(emp.getFirstName());
            employee1.setLastName(emp.getLastName());
            employee1.setAge(emp.getAge());
            employee1.setPhoneNumber(emp.getPhoneNumber());
            insert(employee1);
            result = "Амжилттай хадгаллаа. ";
        }catch (Exception e){
            throw  getDatabaseException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateEmployee(Employee employee) throws Exception {
        String result = "amjiltgui";
        try {
            //update hiih ued
            EmployeeEntity employeeData = getByPKey(EmployeeEntity.class, Long.parseLong(employee.getPkId()) );
            employeeData.setPhoneNumber(employee.getPhoneNumber());
            employeeData.setFirstName(employee.getFirstName());
            employeeData.setAge(employee.getAge());
            employeeData.setLastName(employee.getLastName());
            update(employeeData);
            result = "amjilttai";
        } catch (Exception e) {
            getDatabaseException(e);
        }

        return result;
    }

    public List<EmployeeEntity> getAllEmployee() throws Exception {
        List<EmployeeEntity> employeeEntity;
        String jpql = "SELECT a FROM EmployeeEntity a";
        employeeEntity = getByQuery(EmployeeEntity.class, jpql);
        return employeeEntity;
    }

}
