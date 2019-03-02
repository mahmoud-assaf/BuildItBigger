import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;

import com.udacity.gradle.androidjokeslibrary.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.MainActivityFragment;
import com.udacity.gradle.builditbigger.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import android.support.test.espresso.intent.rule.IntentsTestRule;



import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.intended;
/**
 * Created by mahmoud on 4/23/2018.
 */

public class AsyncTaskTest {
    @Rule
    //important IntentsTestRule instead of activitytestrule to check for opening activity
    public IntentsTestRule<MainActivity> mainActivityActivityTestRule=new IntentsTestRule<MainActivity>(MainActivity.class);
    private AsyncTaskIdlingResource idlingResource;



    @Before
    public void registerIntentServiceIdlingResource() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new AsyncTaskIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void clickButton_ShoeActivity(){
        onView(withId(R.id.showJoke)).perform( click());
        //intended(hasComponent(DisplayJokeActivity.class.getName()));
        assertNotEquals("", MainActivityFragment.asyncTaskResult);



    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }


}
