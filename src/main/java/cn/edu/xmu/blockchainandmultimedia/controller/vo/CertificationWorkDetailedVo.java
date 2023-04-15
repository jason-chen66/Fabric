package cn.edu.xmu.blockchainandmultimedia.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificationWorkDetailedVo {
    private String workName;
    private String workDescription;
    private List<AuthorDetailedVo> authors;
    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
    private String evidence;
}
