package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.iinterfaces.LoginUserInterfaces;
import kara.diamond.billing.service.model.request.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
//        String jpql = "SELECT username, password FROM LoginUser";
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
                user.put("username", obj.getUsername());
                user.put("token", uuid.toString());
                user.put("firstname", obj.getFirstname());
                user.put("lastname", obj.getLastname());
                user.put("email", obj.getFirstname());
                lue.setExp(exp);
                lue.setToken(uuid.toString());
                update(lue);

//                LoginUser loginUser2 = new LoginUser();
//                loginUser2.setEmail(obj.getEmail());
//                loginUser2.setUsername(obj.getUsername());
//                loginList.add(loginUser2);
            }
        }

            //return loginList;
        return user;
        }

}
