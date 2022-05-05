package edu.bupt.xia.sequoia.controller;

import edu.bupt.xia.sequoia.SequoiaApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiahui
 * @date 2022/5/2 16:31
 */
@SpringBootTest(classes = SequoiaApplication.class)
class ShortUrlControllerTests {
    private final ShortUrlController shortUrlController;

    @Autowired
    ShortUrlControllerTests(ShortUrlController shortUrlController) {
        super();

        this.shortUrlController = shortUrlController;
    }

    @Test
    void save() {
        String shortUrl = shortUrlController.save("https://zhuanlan.zhihu.com/p/363864068");
        Assertions.assertEquals("1M8DA", shortUrl);
    }
}
