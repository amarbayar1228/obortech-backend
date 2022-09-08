package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupitemdetail")
public class GroupItemDetailEntity {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "groupItemHeaderPkId")
    private Long groupItemHeaderPkId;

    @Column(name = "itemPkId")
    private  Long itemPkId;

    @Column(name = "itemPriceD")
    private  int itemPriceD;

    @Column(name = "itemCnt")
    private  int itemCnt;

    @Column(name = "img")
    private byte[] img;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getGroupItemHeaderPkId() {
        return groupItemHeaderPkId;
    }

    public void setGroupItemHeaderPkId(Long groupItemHeaderPkId) {
        this.groupItemHeaderPkId = groupItemHeaderPkId;
    }

    public Long getItemPkId() {
        return itemPkId;
    }

    public void setItemPkId(Long itemPkId) {
        this.itemPkId = itemPkId;
    }

    public int getItemPriceD() {
        return itemPriceD;
    }

    public void setItemPriceD(int itemPriceD) {
        this.itemPriceD = itemPriceD;
    }

    public int getItemCnt() {
        return itemCnt;
    }

    public void setItemCnt(int itemCnt) {
        this.itemCnt = itemCnt;
    }
}
