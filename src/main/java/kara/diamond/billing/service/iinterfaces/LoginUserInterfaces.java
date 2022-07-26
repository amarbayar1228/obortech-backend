package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.request.LoginUser;

import java.util.List;
import java.util.Map;

public interface LoginUserInterfaces {
    public String saveUser(LoginUser loginUser) throws Exception;
   public Map<String, String> signInUser(LoginUser loginUser) throws Exception;
    public String updateUser2(LoginUser loginUser) throws Exception;

    public String updateUserAdmin(LoginUser loginUser) throws Exception;
//    public String deleteItem(String pkId) throws Exception;
    public List<LoginUser> userInfo(LoginUser loginUser) throws  Exception;

    public  List<LoginUser> userConfirmList(LoginUser loginUser) throws  Exception;
    public  List<LoginUser> confirmUserReqCancel(LoginUser loginUser) throws  Exception;
}
