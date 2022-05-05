package edu.bupt.xia.sequoia.subservice.impl;

import edu.bupt.xia.sequoia.subservice.ShortUrlIdSubservice;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

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
     */
    @Override
    public long generate() {
        return ShortUrlMemoryIdSubservice.NEXT_ID.getAndIncrement();
    }

    /**
     * 自增ID的起点
     */
    private static final long BEGIN = 20220501;
    private static final AtomicLong NEXT_ID = new AtomicLong(BEGIN);
}
