package com.bar.common.redis;

public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
