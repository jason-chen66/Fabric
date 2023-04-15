package cn.edu.xmu.blockchainandmultimedia.service.dto;

import cn.edu.xmu.blockchainandmultimedia.dao.bo.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDetailedDto {
    private Long id;
    private String workName;
    private String workDescription;
    private List<Author> authors;
    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
}
