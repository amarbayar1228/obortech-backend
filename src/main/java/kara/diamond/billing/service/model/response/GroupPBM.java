package kara.diamond.billing.service.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GroupPBM {
    String pkId;
    String description;
    String title;
    int status;
    List<GroupBusinessModel> gbm;

    public GroupPBM(String pkId, String title, String description, int status, List<GroupBusinessModel> gbm) {
        this.pkId = pkId;
        this.description = description;
        this.title = title;
        this.status = status;
        this.gbm = gbm;
    }

    public GroupPBM() {

    }
}
