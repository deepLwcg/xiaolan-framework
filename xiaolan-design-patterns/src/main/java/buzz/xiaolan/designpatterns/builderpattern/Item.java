package buzz.xiaolan.designpatterns.builderpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 22:06
 * @Description Item
 */
public interface Item {

    /**
     * 名称
     *
     * @return java.lang.String
     * @author Wang Chenguang
     */
    public String name();
    /**
     * 包装
     *
     * @return buzz.xiaolan.designpatterns.builderpattern.Packing
     * @author Wang Chenguang
     */
    public Packing packing();
    /**
     * 价钱
     *
     * @return float
     * @author Wang Chenguang
     */
    public float price();

}
