package cn.edu.xmu.blockchainandmultimedia.mapper;

import cn.edu.xmu.blockchainandmultimedia.mapper.po.AuthorPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorMapper extends JpaRepository<AuthorPo, Long> {

}
