package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.User;
import cn.edu.xmu.blockchainandmultimedia.mapper.UserMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.UserPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
@RefreshScope
public class UserDao {
    private UserMapper userMapper;
    private AuthorDao authorDao;

    @Autowired
    @Lazy
    public UserDao(UserMapper userMapper, AuthorDao authorDao){
        this.userMapper = userMapper;
        this.authorDao = authorDao;
    }

    public User insert(User user){
        UserPo userPo = UserPo.builder()
                .loginName(user.getLoginName())
                .password(user.getPassword())
                .mobile(user.getMobile())
                .authorId(user.getAuthorId())
                .build();

        UserPo retPo = userMapper.save(userPo);
        User ret = new User();
        BeanUtils.copyProperties(ret, retPo);
        return ret;
    }

    public User findByLoginName(String loginName){
        UserPo po = userMapper.findByLoginName(loginName);
        User user = new User();
        BeanUtils.copyProperties(user, po);
        return user;
    }

    public User findByAuthorId(Long authorId){
        UserPo po = userMapper.findByAuthorId(authorId);
        User user = new User();
        BeanUtils.copyProperties(user, po);
        return user;
    }
}
