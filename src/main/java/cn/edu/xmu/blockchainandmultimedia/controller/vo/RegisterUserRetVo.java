package cn.edu.xmu.blockchainandmultimedia.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterUserRetVo {
    private Long id;
    private String userName;
    private String password;
    private String mobile;
}