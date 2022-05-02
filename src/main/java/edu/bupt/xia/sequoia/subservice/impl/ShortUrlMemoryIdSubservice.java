package edu.bupt.xia.sequoia.subservice.impl;

import edu.bupt.xia.sequoia.exception.OutOfIdLimitException;
import edu.bupt.xia.sequoia.subservice.ShortUrlIdSubservice;
import org.springframework.stereotype.Service;

/**
 * @author xiahui
 * @date 2022/5/1 14:31
 */
@Service
public class ShortUrlMemoryIdSubservice implements ShortUrlIdSubservice {
    /**
     * 根据原域名生成唯一ID
     *
     * @return 唯一ID
     * @throws OutOfIdLimitException ID超限异常
     */
    @Override
    public long generate() throws OutOfIdLimitException {
        if (ShortUrlMemoryIdSubservice.isOutOfLimit) {
            throw new OutOfIdLimitException();
        }

        long id;
        synchronized (ShortUrlMemoryIdSubservice.idLock) {
            if (ShortUrlMemoryIdSubservice.isOutOfLimit) {
                throw new OutOfIdLimitException();
            }

            id = ShortUrlMemoryIdSubservice.nextId;
            ShortUrlMemoryIdSubservice.nextId += 1;

            if (ShortUrlMemoryIdSubservice.nextId < id) {
                ShortUrlMemoryIdSubservice.isOutOfLimit = true;
                throw new OutOfIdLimitException();
            }
        }
        return id;
    }

    /**
     * 自增ID的起点
     */
    private static final long BEGIN = 20220501;
    private static long nextId = BEGIN;
    private static boolean isOutOfLimit = false;
    private static final Object idLock = new Object();
}
