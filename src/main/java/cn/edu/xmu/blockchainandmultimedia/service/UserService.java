package cn.edu.xmu.blockchainandmultimedia.service;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.LoginUserRetVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.LoginUserVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.RegisterUserRetVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.RegisterUserVo;
import cn.edu.xmu.blockchainandmultimedia.dao.AuthorDao;
import cn.edu.xmu.blockchainandmultimedia.dao.UserDao;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.User;
import jakarta.transaction.Transactional;
import org.example.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    private AuthorDao authorDao;
    private RegisterUser registerUser;

    @Autowired
    public UserService(UserDao userDao, AuthorDao authorDao){
        this.userDao = userDao;
        this.authorDao = authorDao;

        //非spring，无法自动绑定
        this.registerUser = new RegisterUser();
    }

    /**
     * 用户登录
     *
     * @param
     * @return
     */
    @Transactional
    public LoginUserRetVo login(LoginUserVo body){

        User user = userDao.findByLoginName(body.getUserName());
        if(user==null)
            throw new RuntimeException("Business: User NotExists");
        if(!user.getPassword().equals(body.getPassword()))
            throw new RuntimeException("Business: Password Wrong!");

        LoginUserRetVo loginUserRetVo = new LoginUserRetVo();
        loginUserRetVo.setId(user.getId());
        // 正常来说这里需要密码模块，返回一个token，并用这个token在系统的各处过验证
        // 这里为了方便，保留了接口但没有做；系统其它地方也没有启用验证
        loginUserRetVo.setToken("99999999999999");

        return loginUserRetVo;
    }

    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @Transactional
    public RegisterUserRetVo register(RegisterUserVo body) throws Exception {
        User user = User.builder()
                .loginName(body.getUserName())
                .password(body.getPassword())
                .mobile(body.getMobile())
                .build();

        //插入一个authorId
        Author author = Author.builder()
                .authorName(user.getLoginName())
                .build();
        author = authorDao.insert(author);

        //插入user
        user.setAuthorId(author.getId());
        user = userDao.insert(user);

        RegisterUserRetVo registerUserRetVo = new RegisterUserRetVo();
        registerUserRetVo.setId(user.getId());
        registerUserRetVo.setUserName(user.getLoginName());
        registerUserRetVo.setPassword(user.getPassword());
        registerUserRetVo.setMobile(user.getMobile());

        //链上注册
        try {
            registerUser.registerUser(body.getUserName());
        }catch (Exception e){
            //后处理待完整
            throw e;
        }

        return registerUserRetVo;
    }
}
