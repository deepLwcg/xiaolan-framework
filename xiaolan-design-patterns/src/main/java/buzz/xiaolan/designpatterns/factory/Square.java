package buzz.xiaolan.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:47
 * @Description Square
 */
@Slf4j
public class Square implements Shape  {
    @Override
    public void draw() {
        log.info("Inside Square::draw() method");
    }
}
