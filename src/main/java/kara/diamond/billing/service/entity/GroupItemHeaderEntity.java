package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupitemheader")
public class GroupItemHeaderEntity {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private int status;

    @Column(name = "description")
    private String description;

    @Column(name = "cnt")
    private int cnt;
    @Column(name = "others")
    private String others;
    @Column(name = "itemPriceTotal")
    private int itemPriceTotal;



    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getItemPriceTotal() {
        return itemPriceTotal;
    }

    public void setItemPriceTotal(int itemPriceTotal) {
        this.itemPriceTotal = itemPriceTotal;
    }
}
