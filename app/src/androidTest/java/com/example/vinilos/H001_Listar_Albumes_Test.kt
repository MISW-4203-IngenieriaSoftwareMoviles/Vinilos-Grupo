package com.example.vinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.vinilos.ui.MainActivity
import org.junit.Rule
import org.junit.Test


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class H001_Listar_Albumes_Test {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

//    private lateinit var escenario: ActivityScenario<MainActivity>

//    @Before
//    fun setup(){
//        escenario = ActivityScenario.launch(MainActivity)
//        escenario.moveToState(Lifecycle.State.STARTED)
//    }

    @Test
    fun testBotonVisitanteTitulo(){
        onView(withId(R.id.btn_visitante)).perform(click())
        val titulo = "Menú Álbumes"
        // Validar el título
        onView(withId(R.id.titulo_albumes)).check(matches(withText(titulo)))
    }

    @Test
    fun testBotonVisitanteNombreAlbum(){
        onView(withId(R.id.btn_visitante)).perform(click())

        val nombreAlbum = "Buscando América"
        // Validar el título
        onView(withId(R.id.fragments_rv)).check(matches(withText(nombreAlbum)))
    }

}