package cn.edu.xmu.blockchainandmultimedia.mapper.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "work_author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkAuthorPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workId;
    private Long authorId;
}
