package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.Item;

public interface ItemInterfaces {
    public String saveItem(Item item) throws Exception;
    public String updateItem(Item item) throws Exception;
//    public String orderItemSave(Item item) throws  Exception;
    public String deleteItem(String pkId) throws Exception;

}
