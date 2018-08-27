package com.creations.livebox.datasources;

import io.reactivex.Observable;

/**
 * @author Sérgio Serra on 25/08/2018.
 * Criations
 * sergioserra99@gmail.com
 */
public interface RemoteDataSource<T> {
    Observable<T> fetch();
}
