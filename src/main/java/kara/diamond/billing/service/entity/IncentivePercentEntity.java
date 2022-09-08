package kara.diamond.billing.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incentivepercent")
public class IncentivePercentEntity {
    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "incentPercent")
    private float incentPercent;

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public float getIncentPercent() {
        return incentPercent;
    }

    public void setIncentPercent(float incentPercent) {
        this.incentPercent = incentPercent;
    }

}
