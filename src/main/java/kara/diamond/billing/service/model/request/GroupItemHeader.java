package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class GroupItemHeader {
    private String pkId;
    private String title;
    private int status;
    private String description;
}
