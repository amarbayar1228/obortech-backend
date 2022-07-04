package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.LoginUser;

import java.util.Map;

public interface LoginUserInterfaces {
    public String saveUser(LoginUser loginUser) throws Exception;
   public Map<String, String> signInUser(LoginUser loginUser) throws Exception;
//    public String deleteItem(String pkId) throws Exception;
}
