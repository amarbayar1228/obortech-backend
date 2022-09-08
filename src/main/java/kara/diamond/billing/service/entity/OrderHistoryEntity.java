package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderhistory")
public class OrderHistoryEntity extends Operation {
    @Id
    @Column(name = "pkId")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pkId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "orderId")
    private Long orderId;

    @Column(name = "title")
    private String title;

    @Column(name = "userPkid")
    private String userPkid;


    @Column(name = "orgId")
    private String orgId;
    @Column(name = "insentStatus")
    private int insentStatus;
    @Column(name = "paymentMethod")
    private int paymentMethod;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "state")
    private String state;
    @Column(name = "itemId")
    private Long itemId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getInsentStatus() {
        return insentStatus;
    }

    public void setInsentStatus(int insentStatus) {
        this.insentStatus = insentStatus;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserPkid() {
        return userPkid;
    }

    public void setUserPkid(String userPkid) {
        this.userPkid = userPkid;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Column(name = "cnt")
    private int cnt;

    public Long getPkId() {
        return pkId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
