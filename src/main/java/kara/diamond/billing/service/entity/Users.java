package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;

import javax.persistence.*;

@Entity
@Table(name ="users")
public class Users extends Operation {
    @Id
    @Column(name = "pkId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private int phone;

    public Users() {
        super();
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
