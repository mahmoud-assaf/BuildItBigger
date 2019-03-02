import android.support.test.espresso.IdlingResource;

import com.udacity.gradle.builditbigger.MainActivityFragment;

/**
 * Created by Freeware Sys on 3/17/2018.
 * idling resource for espresso
 */

public class AsyncTaskIdlingResource implements IdlingResource {
    private ResourceCallback  resourceCallback;

    @Override
    public String getName() {
        return AsyncTaskIdlingResource.class.getName();

    }

    @Override
    public boolean isIdleNow() {
        boolean idle =!(MainActivityFragment.asyncTaskResult.equals(""));  // empty ?
        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;

    }
}
