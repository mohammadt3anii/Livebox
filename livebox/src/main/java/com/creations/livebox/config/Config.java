package com.creations.livebox.config;


import com.creations.livebox.datasources.disk.DiskLruDataSource;
import com.creations.livebox.datasources.disk.DiskPersistentDataSource;

import io.reactivex.internal.functions.ObjectHelper;

public final class Config {

    private DiskLruDataSource.Config mLruConfig;
    private DiskPersistentDataSource.Config mPersistentConfig;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Config lruCacheConfig(DiskLruDataSource.Config diskLruConfig) {
        ObjectHelper.requireNonNull(diskLruConfig, "Lru disk cache config cannot be null");
        mLruConfig = diskLruConfig;
        return this;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Config persistentCacheConfig(DiskPersistentDataSource.Config diskCacheConfig) {
        ObjectHelper.requireNonNull(diskCacheConfig, "Persistent disk cache config cannot be null");
        mPersistentConfig = diskCacheConfig;
        return this;
    }

    public DiskLruDataSource.Config getLruConfig() {
        return mLruConfig;
    }

    public DiskPersistentDataSource.Config getPersistentConfig() {
        return mPersistentConfig;
    }
}
