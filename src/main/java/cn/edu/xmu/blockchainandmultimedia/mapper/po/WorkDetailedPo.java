package cn.edu.xmu.blockchainandmultimedia.mapper.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkDetailedPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workName;
    private String workDescription;
    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
    private String updateTime;
    private Byte status;
    private Long mainAuthorId;
}
