package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.mapper.AuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.AuthorPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RefreshScope
public class AuthorDao {
    private AuthorMapper authorMapper;
    private WorkAuthorMapper workAuthorMapper;

    @Autowired
    @Lazy
    public AuthorDao(AuthorMapper authorMapper, WorkAuthorMapper workAuthorMapper){
        this.authorMapper = authorMapper;
        this.workAuthorMapper = workAuthorMapper;
    }

    public Author findById(Long id){
        AuthorPo authorPo=null;
        Optional<AuthorPo> opt = authorMapper.findById(id);
        if (opt.isPresent()){
            authorPo = opt.get();
        }else return null;

        Author author = new Author();
        BeanUtils.copyProperties(author, authorPo);
        return author;
    }

    public Author insert(Author author){
        AuthorPo authorPo = new AuthorPo();
        BeanUtils.copyProperties(authorPo, author);
        AuthorPo retPo = authorMapper.save(authorPo);

        Author ret = new Author();
        BeanUtils.copyProperties(ret, retPo);
        return ret;
    }
}

