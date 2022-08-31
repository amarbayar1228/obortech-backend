package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loginUser")
public class LoginUserEntity {
    @Id
    @Column(name = "pkId")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "username")
    private String username;
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "exp")
    private long exp;

    @Column(name = "token")
    private String token;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private int phone;

    @Column(name = "state")
    private int state;

    @Column(name = "userToken")
    private String userToken;
    @Column(name = "isAdmin")
    private String isAdmin;

    @Column(name = "isSuperAdmin")
    private int isSuperAdmin;
    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;
    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(int isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }



    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
