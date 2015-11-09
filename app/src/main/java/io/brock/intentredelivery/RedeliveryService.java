package io.brock.intentredelivery;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Sample Redelivery service. Android will try to redeliver the intent to start this service.
 * 
 * Android will attempt redelivery continuously using exponential backoff unless the service crashes twice.
 */
public class RedeliveryService extends IntentService {
    private static final String SERVICE_NAME = "RedeliveryService";

    public RedeliveryService() {
        super(SERVICE_NAME);

        /**
         * This guarantees that intents are redelivered if it the service fails to start or crashes
         */
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(SERVICE_NAME, "onHandleIntent called");

        long sleepTime = TimeUnit.SECONDS.toMillis(10); // 10 seconds

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Log.i(SERVICE_NAME, "Sleep interrupted");
        }

        // throw runtime exception so that android attempts to restart the service
        throw new RuntimeException("Service crashed!");
    }
}
