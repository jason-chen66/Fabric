package cn.edu.xmu.blockchainandmultimedia.mapper;

import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkAuthorPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkAuthorMapper extends JpaRepository<WorkAuthorPo, Long> {
    List<WorkAuthorPo> findByAuthorId(Long authorId);

    List<WorkAuthorPo> findByWorkId(Long workId);

    WorkAuthorPo findByWorkIdAndAuthorId(Long workId, Long authorId);


}
