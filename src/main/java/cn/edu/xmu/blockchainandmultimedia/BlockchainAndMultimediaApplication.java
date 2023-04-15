package cn.edu.xmu.blockchainandmultimedia;

import cn.edu.xmu.blockchainandmultimedia.util.SelectiveUpdateJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={"cn.edu.xmu.blockchainandmultimedia"})
@EnableJpaRepositories(
        value = "cn.edu.xmu.blockchainandmultimedia.util",
        basePackages = "cn.edu.xmu.blockchainandmultimedia.mapper",
        repositoryBaseClass = SelectiveUpdateJpaRepositoryImpl.class
)
public class BlockchainAndMultimediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainAndMultimediaApplication.class, args);
    }

}
