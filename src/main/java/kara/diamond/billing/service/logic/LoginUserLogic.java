package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.CompanyEntity;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.iinterfaces.LoginUserInterfaces;
import kara.diamond.billing.service.model.request.Company;
import kara.diamond.billing.service.model.request.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.*;

@Service
public class LoginUserLogic extends BaseDatabaseService implements LoginUserInterfaces {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserLogic.class);

    long unixTime = Instant.now().getEpochSecond();
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(LoginUser loginUser) throws Exception {
        String result = "";
        UUID uuid = UUID.randomUUID();
        try {
            LoginUserEntity loginUserEntity=  new LoginUserEntity();
            loginUserEntity.setPkId(NumericHelper.generateKey());
            loginUserEntity.setFirstname(loginUser.getFirstname());
            loginUserEntity.setLastname(loginUser.getLastname());
            loginUserEntity.setUsername(loginUser.getUsername());
            loginUserEntity.setIsSuperAdmin(loginUser.getIsSuperAdmin());
            loginUserEntity.setExp(unixTime + 60 * 60 * 24 * 30);
            loginUserEntity.setEmail(loginUser.getEmail());
            loginUserEntity.setToken(uuid.toString());
            loginUserEntity.setPassword(loginUser.getPassword());
            insert(loginUserEntity);
            result = "Амжилттай хадгалалаа.";

        } catch (Exception e) {

            throw getDatabaseException(e);
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, String> signInUser(LoginUser loginUser) throws Exception{


        List<LoginUserEntity> loginUserEntity;
        UUID uuid = UUID.randomUUID();
        String jpql = "SELECT a FROM LoginUserEntity a where a.username = '"+loginUser.getUsername()+"' and a.password = '"+loginUser.getPassword()+"'";
        List<LoginUser> loginList = new ArrayList<>();

        Map<String, String> user = new HashMap<>();
        loginUserEntity = getByQuery(LoginUserEntity.class, jpql);
        if(loginUserEntity.size() < 1){
            user.put("msg", "username or password invalid");
        }else{
            for (LoginUserEntity obj : loginUserEntity) {

                LoginUserEntity lue = getByPKey(LoginUserEntity.class, Long.parseLong(obj.getPkId().toString()));
                System.out.println(lue.getUsername());
                System.out.println(obj.getPkId());
                Long exp =unixTime + 60 * 60 * 24 * 30;
                user.put("pkId", obj.getPkId().toString());
                user.put("username", obj.getUsername());
                user.put("state",  Integer.toString(obj.getState()));
                user.put("phone",  Integer.toString(obj.getPhone()));
                user.put("token", uuid.toString());
                user.put("firstname", obj.getFirstname());
                user.put("address", obj.getAddress());
                user.put("isSuperAdmin", Integer.toString(obj.getIsSuperAdmin()));
                user.put("lastname", obj.getLastname());
                user.put("email", obj.getEmail());
//
                lue.setExp(exp);
                lue.setToken(uuid.toString());
                update(lue);
            }
        }
        return user;
        }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateUser2(LoginUser loginUser) throws Exception{
        String result = "amjiltgui";
        try {
            System.out.println("updateUser2");
            LoginUserEntity loginUserUpdate123 = getByPKey(LoginUserEntity.class, Long.parseLong(loginUser.getPkId().toString()));
            loginUserUpdate123.setPhone(loginUser.getPhone());
            loginUserUpdate123.setAddress(loginUser.getAddress());
            loginUserUpdate123.setState(loginUser.getState());
            update(loginUserUpdate123);
            result = "amjilttai";
        }catch (Exception e){
            System.out.println("Ex : "+e);
            getDatabaseException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateUserAdmin(LoginUser loginUser) throws Exception{
        String result = "amjiltgui";
        try {
            System.out.println("updateUser2");
            LoginUserEntity loginUserUpdate123 = getByPKey(LoginUserEntity.class, Long.parseLong(loginUser.getPkId().toString()));

            loginUserUpdate123.setUserToken(loginUser.getUserToken());
            loginUserUpdate123.setState(loginUser.getState());
            update(loginUserUpdate123);
            result = "amjilttai";
        }catch (Exception e){
            System.out.println("Ex : "+e);
            getDatabaseException(e);
        }
        return result;
    }

    public List<LoginUser> getUser() throws  Exception{
        List<LoginUserEntity> loginUserEntity;
        String jpql = "SELECT a FROM LoginUserEntity a";
        List<LoginUser> loginUserList = new ArrayList<>();
        loginUserEntity = getByQuery(LoginUserEntity.class, jpql);
        for(LoginUserEntity obj2 : loginUserEntity){
            if(obj2.getState() == 1) {
                LoginUser loginUser2 = new LoginUser();
                loginUser2.setPkId(String.valueOf(obj2.getPkId()));
                loginUser2.setUsername(obj2.getUsername());
                loginUser2.setLastname(obj2.getLastname());
                loginUser2.setFirstname(obj2.getFirstname());
                loginUser2.setEmail(obj2.getEmail());
                loginUser2.setAddress(obj2.getAddress());
                loginUser2.setUserToken(obj2.getUserToken());
                loginUser2.setPhone(obj2.getPhone());
                loginUser2.setToken(obj2.getToken());
                loginUser2.setState(obj2.getState());
                loginUserList.add(loginUser2);
            }
        }
        return loginUserList;
    }

    public List<LoginUser> getAdmin() throws  Exception{
        List<LoginUserEntity> loginUserEntity;
        String jpql = "SELECT a FROM LoginUserEntity a";

        List<LoginUser> loginUserList = new ArrayList<>();
        loginUserEntity = getByQuery(LoginUserEntity.class, jpql);
        for(LoginUserEntity obj2 : loginUserEntity){
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee  "+obj2.getIsSuperAdmin());
            String too = "2";
            if(obj2.getIsSuperAdmin() ==  2) {
                System.out.println("ooooooooooooooooooooooooooooorloooooooooooooooooo ");
                LoginUser loginUser5 = new LoginUser();
                loginUser5.setPkId(String.valueOf(obj2.getPkId()));
                loginUser5.setUsername(obj2.getUsername());
                loginUser5.setLastname(obj2.getLastname());
                loginUser5.setFirstname(obj2.getFirstname());
                loginUser5.setEmail(obj2.getEmail());
                loginUser5.setAddress(obj2.getAddress());
                loginUser5.setUserToken(obj2.getUserToken());
                loginUser5.setPhone(obj2.getPhone());
                loginUser5.setToken(obj2.getToken());
                loginUser5.setState(obj2.getState());
                loginUserList.add(loginUser5);
            }
        }
        return loginUserList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<LoginUser> userConfirmList(LoginUser loginUser) throws Exception{

        String jpql = "SELECT a FROM LoginUserEntity a where a.userToken = '"+loginUser.getPkId()+"'";
        List<LoginUserEntity> loginUserEntities;
        loginUserEntities= getByQuery(LoginUserEntity.class, jpql);
        List<LoginUser> loginUserList = new ArrayList<>();
        for (LoginUserEntity obj : loginUserEntities){
            LoginUser loginUserD = new LoginUser();
            loginUserD.setPkId(String.valueOf(obj.getPkId()));
            loginUserD.setAddress(obj.getAddress());
            loginUserD.setUsername(obj.getUsername());
            loginUserD.setEmail(obj.getEmail());
            loginUserD.setState(obj.getState());
            loginUserD.setLastname(obj.getLastname());
            loginUserD.setFirstname(obj.getFirstname());
            loginUserD.setPhone(obj.getPhone());
            loginUserD.setState(obj.getState());
            loginUserList.add(loginUserD);
        }
        return loginUserList;
    }

    public List<LoginUser> userInfo(LoginUser loginUser) throws Exception{

        String jpql = "SELECT a FROM LoginUserEntity a where a.token = '"+loginUser.getToken()+"'";
        List<LoginUserEntity> loginUserEntity;
        loginUserEntity = getByQuery(LoginUserEntity.class, jpql);
        List<LoginUser> userList = new ArrayList<>();
        for (LoginUserEntity obj : loginUserEntity){
            LoginUser loginUser3 = new LoginUser();
            loginUser3.setPkId(String.valueOf(obj.getPkId()));
            loginUser3.setAddress(obj.getAddress());
            loginUser3.setFirstname(obj.getFirstname());
            loginUser3.setLastname(obj.getLastname());
            loginUser3.setState(obj.getState());
            loginUser3.setPhone(obj.getPhone());
            loginUser3.setEmail(obj.getEmail());
            loginUser3.setUserToken(obj.getUserToken());
            userList.add(loginUser3);
        }

        return userList;
    }
    public List<LoginUser> getUserTest() throws Exception{

           String jpql = "SELECT a FROM LoginUserEntity a";
            List<LoginUser> loginUserList = new ArrayList<>();
            List<LoginUserEntity> loginUserEntity = getByQuery(LoginUserEntity.class, jpql);
            for(LoginUserEntity obj : loginUserEntity){
                if(obj.getState() == 1){
                    LoginUser loginUser2 = new LoginUser();
                    loginUser2.setPkId(String.valueOf(obj.getPkId()));
                    loginUser2.setPhone(obj.getPhone());
                    loginUser2.setState(obj.getState());
                    loginUserList.add(loginUser2);
                }
                System.out.println("ene ==============> " + obj.getState());
            }
           return loginUserList;
    }


}
