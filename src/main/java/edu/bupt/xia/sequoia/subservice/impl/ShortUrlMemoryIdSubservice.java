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
    private long nextId;
    private boolean isOutOfLimit;
    private final Object idLock;

    public ShortUrlMemoryIdSubservice() {
        super();

        this.nextId = BEGIN;
        this.isOutOfLimit = false;
        this.idLock = new Object();
    }

    /**
     * 根据原域名生成唯一ID
     *
     * @return 唯一ID
     * @throws OutOfIdLimitException ID超限异常
     */
    @Override
    public long generate() throws OutOfIdLimitException {
        if (isOutOfLimit) {
            throw new OutOfIdLimitException();
        }

        long id;
        synchronized (idLock) {
            if (isOutOfLimit) {
                throw new OutOfIdLimitException();
            }

            id = nextId;
            nextId += 1;

            if (nextId < id) {
                isOutOfLimit = true;
                throw new OutOfIdLimitException();
            }
        }
        return id;
    }

    /**
     * 自增ID的起点
     */
    private static final long BEGIN = 20220501;
}
