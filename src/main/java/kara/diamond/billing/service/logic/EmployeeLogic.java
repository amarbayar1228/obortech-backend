package kara.diamond.billing.service.logic;


import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.EmployeeEntity;
import kara.diamond.billing.service.iinterfaces.EmployeeInterfaces;
import kara.diamond.billing.service.model.request.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeLogic  extends BaseDatabaseService implements EmployeeInterfaces {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveEmployee(Employee emp) throws Exception {
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


}
