package edu.bupt.xia.sequoia.controller;

import edu.bupt.xia.sequoia.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiahui
 * @date 2022/5/1 13:01
 */
@RestController
@RequestMapping("/short/url")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    /**
     * 短域名存储接口：接受长域名信息，返回短域名信息
     *
     * @param originUrl 原域名
     * @return 短域名
     */
    @PostMapping("/save")
    public String save(String originUrl) {
        return shortUrlService.save(originUrl);
    }

    /**
     * 短域名读取接口：接受短域名信息，返回长域名信息
     *
     * @param shortUrl 短域名
     * @return 原域名
     */
    @GetMapping("/query")
    public String query(String shortUrl) {
        return shortUrlService.query(shortUrl);
    }
}
