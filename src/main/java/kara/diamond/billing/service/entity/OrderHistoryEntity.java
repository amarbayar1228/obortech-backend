package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderHistory")
public class OrderHistoryEntity extends Operation {
    @Id
    @Column(name = "pkId")
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long pkId;

    @Column(name = "title")
    private String title;

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
