package buzz.xiaolan.poi.commands;

import buzz.xiaolan.poi.ppt.PptProcessor;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/24
 * @Description GeneratePptCommands
 */
@Slf4j
@ShellComponent
public class GeneratePptCommands {

    @ShellMethod(key = "generate-ppt")
    public String generatePpt(
            @ShellOption(defaultValue = "E:\\tmp") String path,
            @ShellOption(defaultValue = "") String name
    ) {
        val dir = FileUtil.file(path);
        if (!dir.exists()) {
            FileUtil.mkdir(dir);
        }
        if (StringUtils.isBlank(name)) {
            name = String.format("%s.pptx", DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_FORMAT));
        }
        val pptFile = FileUtil.file(dir, name);
        PptProcessor processor = new PptProcessor();
        return String.format("PPT生成状态：[%s] 文件：[%s]", processor.generatePpt(pptFile), pptFile.getPath());
    }
}
