package buzz.xiaolan.designpatterns.abstractfactorypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:18
 * @Description Red
 */
@Slf4j
public class Red implements Color{
    @Override
    public void fill() {
        log.info("Inside Red::fill() method");
    }
}
