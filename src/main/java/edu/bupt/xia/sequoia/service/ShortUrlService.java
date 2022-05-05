package edu.bupt.xia.sequoia.service;

import edu.bupt.xia.sequoia.exception.OutOfLengthException;
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
     * @throws OutOfLengthException     短域名长度超限
     * @throws OutOfStoreLimitException 超出存储器容量限制
     */
    String save(String originUrl) throws OutOfLengthException, OutOfStoreLimitException;

    /**
     * 短域名读取接口：接受短域名信息，返回长域名信息
     *
     * @param shortUrl 短域名
     * @return 原域名
     */
    String query(String shortUrl);
}
