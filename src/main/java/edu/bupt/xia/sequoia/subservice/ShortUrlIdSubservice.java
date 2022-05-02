package edu.bupt.xia.sequoia.subservice;

import edu.bupt.xia.sequoia.exception.OutOfIdLimitException;

/**
 * @author xiahui
 * @date 2022/5/1 14:13
 */
public interface ShortUrlIdSubservice {
    /**
     * 生成唯一ID
     *
     * @return 唯一ID
     * @throws OutOfIdLimitException ID超限异常
     */
    long generate() throws OutOfIdLimitException;
}
