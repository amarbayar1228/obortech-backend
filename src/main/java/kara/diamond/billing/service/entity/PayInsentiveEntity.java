package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payinsentive")
public class PayInsentiveEntity {

    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "userPkId")
    private Long userPkId;

    @Column(name = "date")
    private String date;

    @Column(name = "fee")
    private float fee;

    @Column(name = "status")
    private  int status;


    @Column(name = "payMethod")
    private  int payMethod;

    @Column(name = "orgId")
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getUserPkId() {
        return userPkId;
    }

    public void setUserPkId(Long userPkId) {
        this.userPkId = userPkId;
    }
}
