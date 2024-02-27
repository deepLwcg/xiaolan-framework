package buzz.xiaolan.designpatterns.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:18
 * @Description Blue
 */
@Slf4j
public class Blue implements Color {
    @Override
    public void fill() {
        log.info("Inside Blue::fill() method.");
    }
}
