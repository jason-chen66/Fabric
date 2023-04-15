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
public class User implements Serializable {
    private Long id;
    private String loginName;
    private String password;
    private String mobile;
    private Long authorId;
}
