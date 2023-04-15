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
public class Author implements Serializable {
    private Long id;
    private Byte authorCategory;
    private String authorName;
    private Byte signature;
    private Byte authorRights;

    // 用户类别
    public static final Integer PERSONAL = 0;
    public static final Integer ENTERPRISE =1;

    // 用户签名
    public static final Integer REAL_NAME = 0;
    public static final Integer ANOTHER_NAME = 1;
    public static final Integer ANONYMOUS = 2;

    //著作人权利
    public static final Integer PUBLICATION_RIGHT = 0;
    public static final Integer AUTHORIZATION_RIGHT = 1;
    public static final Integer MODIFICATION_RIGHT = 2;
    public static final Integer INTEGRITY_PROTECTION_RIGHT = 3;
    public static final Integer REPRODUCTION_RIGHT = 4;
    public static final Integer DISTRIBUTION_RIGHT = 5;
    public static final Integer RENTAL_RIGHT = 6;
    public static final Integer EXHIBITIONS_RIGHT = 7;
    public static final Integer PERFORMANCE_RIGHT = 8;
    public static final Integer SCREENING_RIGHT = 9;
    public static final Integer BROADCASTING_RIGHT = 10;
    public static final Integer INFORMATION_NETWORK_DISSEMINATION_RIGHT = 11;
    public static final Integer FILMING_RIGHT = 12;
    public static final Integer ADAPTATION_RIGHT = 13;
    public static final Integer TRANSLATION_RIGHT = 14;
    public static final Integer OTHER_RIGHTS = 15;

    //发表状态
    public static final Integer PUBLISHED = 0;
    public static final Integer NOT_PUBLISHED = 1;

    //权利取得方式
    public static final Integer ORIGINAL = 0;
    public static final Integer INHERIT = 1;
    public static final Integer TRANSFER = 2;

    //创作性质
    public static final Integer ORIGINAL_CREATED =0;
    public static final Integer ADAPTED = 1;
    public static final Integer TRANSLATED = 2;
    public static final Integer NOTED = 3;
    public static final Integer ARRANGED = 4;

    //是否允许公示
    public static final Integer PUBLICITY_ALLOWED = 0;
    public static final Integer PUBLICITY_NOT_ALLOWED = 1;

    //权利归属方式
    public static final Integer PERSONAL_RIGHT_BELONGING = 0;
    public static final Integer GROUP_RIGHT_BELONGING = 1;

}
