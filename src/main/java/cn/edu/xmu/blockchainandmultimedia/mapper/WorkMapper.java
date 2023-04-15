package cn.edu.xmu.blockchainandmultimedia.mapper;

import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkPo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMapper extends JpaRepository<WorkPo, Long> {
    @Transactional
    @Query(value = "SELECT work_name as workName, update_time as updateTime, status as status FROM `work` WHERE id = ?1", nativeQuery = true)
    WorkPo findSimpleWork(Long id);

    @Transactional
    @Query(value = "SELECT work_name as workName, update_time as updateTime, status as status FROM `work` WHERE main_author_id = ?1 LIMIT ?2, ?3", nativeQuery = true)
    List<WorkPo> retrieveSimpleWorkByMainAuthorId(Long id, Integer pageTimesPageSize, Integer pageSize);

}
