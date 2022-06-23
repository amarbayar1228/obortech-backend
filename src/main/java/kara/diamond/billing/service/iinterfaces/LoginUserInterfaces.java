package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.request.LoginUser;

public interface LoginUserInterfaces {
    public String saveUser(LoginUser loginUser) throws Exception;
//     public String signInUser(LoginUser loginUser) throws Exception;
//    public String deleteItem(String pkId) throws Exception;
}
