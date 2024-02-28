package buzz.xiaolan.designpatterns.builderpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 22:09
 * @Description Wrapper
 */
@Slf4j
public class Wrapper implements Packing{
    @Override
    public String pack() {
        return "Wrapper";
    }
}
