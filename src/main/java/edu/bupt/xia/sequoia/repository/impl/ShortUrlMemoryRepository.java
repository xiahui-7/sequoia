package edu.bupt.xia.sequoia.repository.impl;

import edu.bupt.xia.sequoia.exception.OutOfStoreLimitException;
import edu.bupt.xia.sequoia.repository.ShortUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiahui
 * @date 2022/5/1 14:33
 */
@Repository
public class ShortUrlMemoryRepository implements ShortUrlRepository {
    private Map<String, String> memoryStore;
    private boolean isOutOfLimit;

    public ShortUrlMemoryRepository() {
        super();

        memoryStore = new HashMap<>();
        isOutOfLimit = false;
    }

    /**
     * 保存原域名和短域名的对应关系
     *
     * @param originUrl 原域名
     * @param shortUrl `短域名
     * @throws OutOfStoreLimitException  超出存储器容量限制
     */
    @Override
    public void save(String originUrl, String shortUrl) throws OutOfStoreLimitException {
        if (isOutOfLimit) {
            throw new OutOfStoreLimitException();
        }

        try {
            memoryStore.put(shortUrl, originUrl);
        } catch (OutOfMemoryError e) {
            isOutOfLimit = true;
            throw new OutOfStoreLimitException();
        }
    }

    /**
     * 根据短域名查询原域名
     *
     * @param shortUrl  短域名
     * @return 原域名
     */
    @Override
    public String query(String shortUrl) {
        return memoryStore.get(shortUrl);
    }
}