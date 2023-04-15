package cn.edu.xmu.blockchainandmultimedia.service;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.AuthorVo;
import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedModifyVo;
import cn.edu.xmu.blockchainandmultimedia.dao.WorkDao;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.Work;
import cn.edu.xmu.blockchainandmultimedia.dao.bo.WorkDetailed;
import cn.edu.xmu.blockchainandmultimedia.service.dto.PageDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.SimpleWorkDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkDetailedDto;
import cn.edu.xmu.blockchainandmultimedia.service.dto.WorkStatusDto;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnNo;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkManagementService {

    private WorkDao workDao;

    @Autowired
    public WorkManagementService(WorkDao workDao){
        this.workDao = workDao;
    }

    /**
     * 查看自己作品简略信息
     *
     * @param
     * @return
     */
    @Transactional
    public PageDto<SimpleWorkDto> retrieveWorkByAuthorId(Long id, Integer page, Integer pageSize){
        List<Work> works= workDao.retrieveWorkByAuthorId(id, page, pageSize);

        List<SimpleWorkDto> simpleWorkDtos = new ArrayList<>();
        for(int i=0; i<pageSize;i++){
            Work work = works.get((page-1)*pageSize + i);

            SimpleWorkDto simpleWorkDto = new SimpleWorkDto();
            simpleWorkDto.setId(work.getId());
            simpleWorkDto.setName(work.getWorkName());
            simpleWorkDto.setUpdateTime(work.getUpdateTime());
            simpleWorkDto.setStatus(work.getStatus());

            simpleWorkDtos.add(simpleWorkDto);
        }
        return new PageDto<>(simpleWorkDtos, page, pageSize);
    }

    /**
     * 查看自己作品详细信息
     *
     * @param
     * @return
     */
    @Transactional
    public WorkDetailedDto retrieveWorkDetailedById(Long workId){
        WorkDetailed workDetailed = workDao.retrieveWorkDetailedById(workId);

        WorkDetailedDto workDetailedDto = WorkDetailedDto.builder()
                .id(workDetailed.getId())
                .workName(workDetailed.getWorkName())
                .workDescription(workDetailed.getWorkDescription())
                .authors(workDetailed.getAuthors())
                .workCategory(workDetailed.getWorkCategory())
                .finishTime(workDetailed.getFinishTime())
                .finishPlace(workDetailed.getFinishPlace())
                .publishStatus(workDetailed.getPublishStatus())
                .rightsObtain(workDetailed.getRightsObtain())
                .workNature(workDetailed.getWorkNature())
                .publicNotice(workDetailed.getPublicNotice())
                .build();

        return workDetailedDto;
    }

    /**
     * 修改自己作品详细信息
     *
     * @param
     * @return
     */
    public ReturnObject updateWorkDetailedById(Long workId, WorkDetailedModifyVo body){
        WorkDetailed workDetailed = WorkDetailed.builder()
                .id(workId)
                .workName(body.getWorkName())
                .workDescription(body.getWorkDescription())
                .workCategory(body.getWorkCategory())
                .finishTime(body.getFinishTime())
                .finishPlace(body.getFinishPlace())
                .publishStatus(body.getPublishStatus())
                .rightsObtain(body.getRightsObtain())
                .workNature(body.getWorkNature())
                .publicNotice(body.getPublicNotice())
                .build();

        List<Author> authors = new ArrayList<>();
        for(AuthorVo authorVo : body.getAuthors()){
            Author author = Author.builder()
                    .id(authorVo.getId())
                    .authorCategory(authorVo.getAuthorCategory())
                    .authorName(authorVo.getAuthorName())
                    .signature(authorVo.getSignature())
                    .authorRights(authorVo.getAuthorRights())
                    .build();
            authors.add(author);
        }
        workDetailed.setAuthors(authors);

        workDao.insert(workDetailed);
        return new ReturnObject(ReturnNo.OK);
    }

    /**
     * 查看自己作品证书状态
     *
     * @param
     * @return
     */
    public WorkStatusDto findCertificateStatus(Long workId){
        WorkStatusDto workStatusDto = new WorkStatusDto();
        Work work = workDao.findWorkById(workId);
        workStatusDto.setStatus(work.getStatus());

        return workStatusDto;
    }

    /**
     * 生成证书/查看证书
     * TODO
     * @param
     * @return
     */
    public String generateCertificate(Long workId){
        return null;
    }

}
