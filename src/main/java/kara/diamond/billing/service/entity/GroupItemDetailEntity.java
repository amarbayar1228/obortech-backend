package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupItemDetail")
public class GroupItemDetailEntity {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "groupItemHeaderPkId")
    private Long groupItemHeaderPkId;

    @Column(name = "itemPkId")
    private  Long itemPkId;

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
}
