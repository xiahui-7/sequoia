package edu.bupt.xia.sequoia.service;

import edu.bupt.xia.sequoia.exception.OutOfIdLimitException;
import edu.bupt.xia.sequoia.exception.OutOfStoreLimitException;

/**
 * @author xiahui
 * @date 2022/5/1 13:15
 */
public interface ShortUrlService {
    /**
     * 短域名存储接口：接受长域名信息，返回短域名信息
     *
     * @param originUrl 原域名
     * @return 短域名
     */
    String save(String originUrl) throws OutOfIdLimitException, OutOfStoreLimitException;

    /**
     * 短域名读取接口：接受短域名信息，返回长域名信息
     *
     * @param shortUrl 短域名
     * @return 原域名
     */
    String query(String shortUrl);
}
