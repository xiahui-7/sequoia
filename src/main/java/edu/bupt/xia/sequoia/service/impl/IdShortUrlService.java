package edu.bupt.xia.sequoia.service.impl;

import edu.bupt.xia.sequoia.exception.OutOfIdLimitException;
import edu.bupt.xia.sequoia.exception.OutOfStoreLimitException;
import edu.bupt.xia.sequoia.repository.ShortUrlRepository;
import edu.bupt.xia.sequoia.subservice.ShortUrlIdSubservice;
import edu.bupt.xia.sequoia.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiahui
 * @date 2022/5/1 13:31
 */
@Service
public class IdShortUrlService implements ShortUrlService {
    private final ShortUrlIdSubservice shortUrlIdSubservice;
    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public IdShortUrlService(ShortUrlIdSubservice shortUrlIdSubservice, ShortUrlRepository shortUrlRepository) {
        this.shortUrlIdSubservice = shortUrlIdSubservice;
        this.shortUrlRepository = shortUrlRepository;
    }

    /**
     * 短域名存储接口：接受长域名信息，返回短域名信息
     *
     * @param originUrl 原域名
     * @return 短域名
     */
    @Override
    public String save(String originUrl) throws OutOfIdLimitException, OutOfStoreLimitException {
        long id = shortUrlIdSubservice.generate();
        String shortUrl = transform(id);
        if (shortUrl.length() > IdShortUrlService.SHORT_URL_MAX_LEN) {
            throw new OutOfIdLimitException();
        }
        shortUrlRepository.save(originUrl, shortUrl);
        return shortUrl;
    }

    /**
     * 短域名读取接口：接受短域名信息，返回长域名信息
     *
     * @param shortUrl 短域名
     * @return 原域名
     */
    @Override
    public String query(String shortUrl) {
        return shortUrlRepository.query(shortUrl);
    }

    private String transform(long id) {
        StringBuilder buf = new StringBuilder();
        int scale = IdShortUrlService.DIGITS.length;
        while (id > 0) {
            long remainder = id % scale;
            buf.append(IdShortUrlService.DIGITS[(int) remainder]);
            id /= scale;
        }
        return buf.toString();
    }

    private static final char[] DIGITS = {
            '0', 'A', 'a', 'K', 'k', 'U', 'u',
            '1', 'B', 'b', 'L', 'l', 'V', 'v',
            '2', 'C', 'c', 'M', 'm', 'W', 'w',
            '3', 'D', 'd', 'N', 'n', 'X', 'x',
            '4', 'E', 'e', 'O', 'o', 'Y', 'y',
            '5', 'F', 'f', 'P', 'p', 'Z', 'z',
            '6', 'G', 'g', 'Q', 'q',
            '7', 'H', 'h', 'R', 'r',
            '8', 'I', 'i', 'S', 's',
            '9', 'J', 'j', 'T', 't'
    };

    private static final int SHORT_URL_MAX_LEN = 8;
}
