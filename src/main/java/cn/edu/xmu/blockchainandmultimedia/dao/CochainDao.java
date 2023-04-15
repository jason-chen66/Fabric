package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.CertificationWorkDetailed;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkDetailed;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
@RefreshScope
public class CochainDao {

    private WorkDao workDao;

    @Autowired
    @Lazy
    CochainDao(WorkDao workDao){
        this.workDao = workDao;
    }
    public void insert(CertificationWorkDetailed certificationWorkDetailed){
        WorkDetailed workDetailed = new WorkDetailed();
        //丢失用户信息的保存方式，后续更改
        BeanUtils.copyProperties(workDetailed, certificationWorkDetailed);

        workDao.insert(workDetailed);
        return;
    }
}
