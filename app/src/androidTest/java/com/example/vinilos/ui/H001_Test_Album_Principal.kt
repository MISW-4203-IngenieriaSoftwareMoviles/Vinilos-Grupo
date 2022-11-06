package com.example.vinilos.ui



import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.vinilos.R
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.hamcrest.Matchers.allOf




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class H001_Test_Album_Principal {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun testBotonVisitanteNombreAlbum(){

        onView(withId(R.id.btn_visitante)).perform(click())

        val nombreAlbum = "Buscando América"
        // Validar el título
        onView(withId(R.id.fragments_rv)).check(matches(withText(nombreAlbum)))
        Thread.sleep(2_000)
    }


    @Test fun testCreateStateFragment() {
        val scenario = launchFragmentInContainer<FirstPageFragment>()
        scenario.moveToState(Lifecycle.State.CREATED)
        Thread.sleep(2_000)
    }

    @Test fun testStartedStateFragment() {
        val scenario = launchFragmentInContainer<FirstPageFragment>()
        scenario.moveToState(Lifecycle.State.STARTED)
        Thread.sleep(2_000)
    }

    @Test fun testOnDestroyedStateFragment() {
        val scenario = launchFragmentInContainer<FirstPageFragment>()
        scenario.moveToState(Lifecycle.State.DESTROYED)
        Thread.sleep(2_000)
    }


    @Test
    fun testFirstFragmentMessage() {
        Thread.sleep(2_000)
        val button = onView(
            allOf(
                withId(R.id.btn_visitante), withText("SOY VISITANTE"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        Thread.sleep(2_000)
        button.check(matches(isDisplayed()))
        Thread.sleep(2_000)
    }








}