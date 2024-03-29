package buzz.xiaolan.poi.ppt;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.*;

import java.awt.geom.Rectangle2D;
import java.io.*;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/24
 * @Description Processor for generating ppt
 */
@Slf4j
public class PptProcessor {

    public boolean generatePpt(File pptFile) {

        try (XMLSlideShow xmlSlideShow = new XMLSlideShow()) {
            XSLFSlide slide = xmlSlideShow.createSlide();

            XSLFTextBox textBox = slide.createTextBox();
            textBox.setText("Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World Hello World");
            textBox.setAnchor(new Rectangle2D.Double(100, 100, 200, 20));

            xmlSlideShow.write(new FileOutputStream(pptFile));
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", pptFile.getAbsolutePath(), e);
        } catch (IOException e) {
            log.error("IO Exception: {}", e.getMessage(), e);
        }
        return false;
    }
}
