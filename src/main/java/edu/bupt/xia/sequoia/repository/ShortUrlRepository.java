package edu.bupt.xia.sequoia.repository;

import edu.bupt.xia.sequoia.exception.OutOfStoreLimitException;

/**
 * @author xiahui
 * @date 2022/5/1 14:21
 */
public interface ShortUrlRepository {
    /**
     * 保存原域名和短域名的对应关系
     *
     * @param originUrl 原域名
     * @param shortUrl  短域名
     * @throws OutOfStoreLimitException 超出存储器容量限制
     */
    void save(String originUrl, String shortUrl) throws OutOfStoreLimitException;

    /**
     * 根据短域名查询原域名
     *
     * @param shortUrl 短域名
     * @return 原域名
     */
    String query(String shortUrl);
}
