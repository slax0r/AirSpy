package com.mac.airspy;

import android.app.Activity;
import com.mac.airspy.content.ObjectSource;
import com.mac.airspy.content.source.TestObjectSource;
import roboguice.inject.ContextSingleton;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Maciej on 2014-10-12.
 */

@ContextSingleton
public class ObjectSourceManager extends BaseApplicationComponent {

    private ScheduledExecutorService executor;

    private List<ARObject> objects;

    private ObjectSource objectSource = new TestObjectSource();

    private UpdateObjectsCommand currentUpdateCommand;

    public void start() {
        setState(objects == null ? ComponentState.STARTING : ComponentState.READY);

        currentUpdateCommand = new UpdateObjectsCommand(objectSource);
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(currentUpdateCommand, 0, 20, TimeUnit.SECONDS);
    }

    public void stop() {
        setState(ComponentState.STOPPED);

        currentUpdateCommand.cancel();

        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
    }

    public List<ARObject> getObjects() {
        return objects;
    }

    private class UpdateObjectsCommand implements Runnable {
        private final ObjectSource objectSource;

        private boolean cancelled;

        private UpdateObjectsCommand(ObjectSource objectSource) {
            this.objectSource = objectSource;
        }

        @Override
        public void run() {
            if (!cancelled) {
                objects = objectSource.getObjects();

                setStateOnUiThread();

            }
        }

        public void cancel() {
            cancelled = true;
        }
    }

    private void setStateOnUiThread() {
        Activity activity = (Activity) ctx;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setState(ComponentState.READY);
            }
        });
    }
}
