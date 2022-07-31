package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.GroupItemDetail;
import kara.diamond.billing.service.model.request.GroupItemHeader;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.response.GroupBusinessModel;

import java.util.List;

public interface ItemInterfaces {
    public String saveItem(Item item) throws Exception;
    public String updateItem(Item item) throws Exception;
    public String saveGroupItem(GroupItemHeader groupItemHeader ) throws Exception;
//    public String orderItemSave(Item item) throws  Exception;
    public String deleteItem(String pkId) throws Exception;

    public List<GroupBusinessModel> getGroupItems() throws  Exception;

}
