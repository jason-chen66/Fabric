package cn.edu.xmu.blockchainandmultimedia.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Data
@Builder
public class CertificationWorkDetailed implements Serializable {
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
    private String evidence;
}
