package buzz.xiaolan.security.config;

import com.baomidou.mybatisplus.extension.ddl.SimpleDdl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/30
 * @Description MysqlDdl
 */
@Slf4j
@Component
public class MysqlDdl extends SimpleDdl {

    @Override
    public List<String> getSqlFiles() {
        return List.of(
                "db/schema.sql",
                "db/data.sql"
        );
    }
}
