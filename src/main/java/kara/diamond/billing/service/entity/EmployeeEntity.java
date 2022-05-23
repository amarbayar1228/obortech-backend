package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class EmployeeEntity extends Operation {

    @Id
    @Column(name = "pkId")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "age")
    private int age;

    @Column(name = "phoneNumber")
    private int phoneNumber;

    public EmployeeEntity() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
