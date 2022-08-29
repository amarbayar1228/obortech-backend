package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orgusers")
public class OrgUsersEntity {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "orgId")
    private String orgId;

    @Column(name = "userToken")
    private String userToken;

    @Column(name = "insentive")
    private float insentive;


    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }



    public float getInsentive() {
        return insentive;
    }

    public void setInsentive(float insentive) {
        this.insentive = insentive;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
