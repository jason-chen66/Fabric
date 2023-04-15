package cn.edu.xmu.blockchainandmultimedia.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorVo {
    private Long id;
    private Byte authorCategory;
    private String authorName;
    private Byte signature;
    private Byte authorRights;
}
