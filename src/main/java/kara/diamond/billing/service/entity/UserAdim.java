package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userAdmin")
public class UserAdim {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "userId")
    private long userId;

    @Column(name = "adminId")
    private  long adminId;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

}
