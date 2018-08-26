package com.creations.livebox.rx;

import android.util.Log;

import com.creations.livebox.Logger;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author Sérgio Serra on 25/08/2018.
 * Criations
 * sergioserra99@gmail.com
 * <p>
 * Taken from # https://stackoverflow.com/questions/22066481/rxjava-can-i-use-retry-but-with-delay
 */
public class RetryWithDelay implements Function<Observable<Throwable>, Observable<?>> {

    private static final String TAG = "RetryWithDelay";

    private final int maxRetries;
    private final long retryDelayMillis;
    private int retryCount;

    RetryWithDelay(final int maxRetries, final long retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> apply(Observable<Throwable> attempts) {
        return attempts.flatMap((Function<Throwable, ObservableSource<Long>>) throwable -> {
            if (++retryCount < maxRetries) {
                Logger.d(TAG, "Retry for the " + retryCount + " time");
                // When this Observable calls onNext, the original
                // Observable will be retried (i.e. re-subscribed).
                return Observable.timer(retryDelayMillis, TimeUnit.MILLISECONDS);
            }

            // Max retries hit. Just pass the error along.
            return Observable.error(throwable);
        });
    }

}
