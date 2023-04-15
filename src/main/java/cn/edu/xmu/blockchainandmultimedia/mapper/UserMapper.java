package cn.edu.xmu.blockchainandmultimedia.mapper;

import cn.edu.xmu.blockchainandmultimedia.mapper.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository<UserPo, Long> {
    UserPo findByLoginName(String loginName);

    UserPo findByAuthorId(Long authorId);


}
