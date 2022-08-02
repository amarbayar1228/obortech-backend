package kara.diamond.billing.service.model.request;

import lombok.Data;

import java.util.List;
@Data
public class GrouptRequest {
    private String pkId;
    private String title;
    private String description;
    private int status;

    private List<GroupItemDetail> groupDetail;

}
