package cn.edu.xmu.blockchainandmultimedia.mapper;

import cn.edu.xmu.blockchainandmultimedia.mapper.po.WorkDetailedPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDetailedMapper extends JpaRepository<WorkDetailedPo, Long> {

}
