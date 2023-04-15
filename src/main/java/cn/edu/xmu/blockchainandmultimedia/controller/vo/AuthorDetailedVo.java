package cn.edu.xmu.blockchainandmultimedia.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDetailedVo {
    private Long id;
    private Byte authorCategory;
    private String authorName;
    private Byte identityCategory;
    private String identityNumber;
    private Byte signature;
    private String addressDetailed;
    private String phoneNumber;
    private String Email;
    private String identityPhoto;
    private Byte authorRights;
}
