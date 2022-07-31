package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class GroupItemDetail {
    private String pkId;
    private String groupItemHeaderPkId;
    private String itemPkId;

}
