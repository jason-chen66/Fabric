package cn.edu.xmu.blockchainandmultimedia.dao;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Work;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkDetailed;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkAuthorMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkDetailedMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.WorkMapper;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkAuthorPo;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkDetailedPo;
import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkPo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RefreshScope
public class WorkDao {
    private WorkMapper workMapper;
    private WorkAuthorMapper workAuthorMapper;
    private WorkDetailedMapper workDetailedMapper;
    private AuthorDao authorDao;
    private WorkAuthorDao workAuthorDao;

    @Autowired
    @Lazy
    public WorkDao(WorkMapper workMapper, WorkAuthorMapper workAuthorMapper, WorkDetailedMapper workDetailedMapper, AuthorDao authorDao, WorkAuthorDao workAuthorDao){
        this.workMapper = workMapper;
        this.workAuthorMapper = workAuthorMapper;
        this.workDetailedMapper = workDetailedMapper;
        this.authorDao = authorDao;
        this.workAuthorDao = workAuthorDao;
    }

    public Work getWorkBo(WorkPo po){
        Work bo = Work.builder()
                .id(po.getId())
                .workName(po.getWorkName())
                .updateTime(po.getUpdateTime())
                .status(po.getStatus())
                .build();
        return bo;
    }

    public WorkDetailed getWorkDetailedBo(WorkDetailedPo po){
        WorkDetailed bo = WorkDetailed.builder()
                .id(po.getId())
                .workName(po.getWorkName())
                .workDescription(po.getWorkDescription())
                .workCategory(po.getWorkCategory())
                .finishTime(po.getFinishTime())
                .finishPlace(po.getFinishPlace())
                .publishStatus(po.getPublishStatus())
                .rightsObtain(po.getRightsObtain())
                .workNature(po.getWorkNature())
                .publicNotice(po.getPublicNotice())
                .rightBelonging(po.getRightBelonging())
                .workAuthorDao(this.workAuthorDao)
                .build();
        return bo;
    }

    public Work findWorkById(Long id) {
        WorkPo workPo = workMapper.findSimpleWork(id);
        Work work = Work.builder()
                .id(workPo.getId())
                .workName(workPo.getWorkName())
                .updateTime(workPo.getUpdateTime())
                .status(workPo.getStatus())
                .build();
        return work;
    }

    public List<Work> retrieveWorkByAuthorId(Long authorId, Integer page, Integer pageSize){
        if (authorId == null)
            return null;

        //List<WorkPo> workPos = workMapper.retrieveSimpleWorkByMainAuthorId(authorId, page, pageSize);

        //需求变更，可查所有作品
        List<Work> works = workAuthorDao.retrieveWorksByAuthorId(authorId);

        return new ArrayList<>(works.subList(page*pageSize+1, page*pageSize+1+pageSize));
    }

    public WorkDetailed retrieveWorkDetailedById(Long workId){
        WorkDetailedPo workDetailedPo =  workDetailedMapper.findById(workId).get();
        return getWorkDetailedBo(workDetailedPo);
    }

    public void insert(WorkDetailed workDetailed){
        WorkDetailedPo workDetailedPo;
        Optional<WorkDetailedPo> opt = workDetailedMapper.findById(workDetailed.getId());
        if(opt.isPresent())
            workDetailedPo = opt.get();
        else workDetailedPo = new WorkDetailedPo();

        BeanUtils.copyProperties(workDetailedPo, workDetailed);
        workDetailedMapper.save(workDetailedPo);

        // 更改作者信息
        List<Author> authors = workDetailed.getAuthors();
        for(Author author : authors){
            WorkAuthorPo workAuthorPo = workAuthorDao.findByWorkIdAndAuthorId(workDetailed.getId(), author.getId());
            if(workAuthorPo == null){
                workAuthorPo = new WorkAuthorPo();
                workAuthorPo.setWorkId(workDetailed.getId());
                workAuthorPo.setAuthorId(author.getId());
                workAuthorDao.insert(workAuthorPo);
            }
        }

    }
}

