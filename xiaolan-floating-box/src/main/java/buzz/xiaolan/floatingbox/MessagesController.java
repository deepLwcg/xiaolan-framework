package buzz.xiaolan.floatingbox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/10 02:39
 * @Description MessagesController
 */
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    @GetMapping
    public String getMessages() {
        return "Hello World!";
    }

}
