package edu.bupt.xia.sequoia.controller;

import edu.bupt.xia.sequoia.exception.OutOfLengthException;
import edu.bupt.xia.sequoia.exception.OutOfStoreLimitException;
import edu.bupt.xia.sequoia.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler({OutOfLengthException.class, OutOfStoreLimitException.class})
    public ResponseEntity<String> saveError() {
        return new ResponseEntity<>("空间不足无法完成短域名存储", HttpStatus.BAD_REQUEST);
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
