package cn.edu.xmu.blockchainandmultimedia.dao.bo;

import cn.edu.xmu.blockchainandmultimedia.dao.WorkAuthorDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Data
@Builder
public class WorkDetailed implements Serializable {
    private Long id;
    private String workName;
    private String workDescription;

    @Setter
    @JsonIgnore
    private WorkAuthorDao workAuthorDao;

    @Setter
    private List<Author> authors;

    public List<Author> getAuthors(){
        if(null == this.authors && null != workAuthorDao){
            this.authors = this.workAuthorDao.retrieveAuthorsByWorkId(id);
        }
        return this.authors;
    }

    private Byte workCategory;
    private String finishTime;
    private String finishPlace;
    private Byte publishStatus;
    private Byte rightsObtain;
    private Byte workNature;
    private Byte publicNotice;
    private Byte rightBelonging;
}
