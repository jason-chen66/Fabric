package cn.edu.xmu.blockchainandmultimedia.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class SimpleWorkDto {
    private Long id;
    private String name;
    private String updateTime;
    private Byte status;
}
