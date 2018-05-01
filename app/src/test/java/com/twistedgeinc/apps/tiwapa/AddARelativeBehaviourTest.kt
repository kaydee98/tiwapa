package com.twistedgeinc.apps.tiwapa


import android.content.Intent
import android.widget.*
import com.twistedgeinc.apps.tiwapa.family.AddRelativeActivity
import com.twistedgeinc.apps.tiwapa.family.MyFamilyActivity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication


@RunWith(RobolectricTestRunner::class)
class AddARelativeBehaviourTest {

    private var activity = Robolectric.setupActivity(AddRelativeActivity::class.java)
    private var PARENT_RELATIVE_TYPE_SELECTION = 1
    private var INVALID_RELATIVE_TYPE_SELECTION = 0

    private var firstNameET: EditText? = null
    private var lastNameET: EditText? = null
    private var middleNameET: EditText? = null
    private var genderButton: RadioGroup? = null
    private var dateOfBirthET: EditText? = null
    private var dateOfDeathET: EditText? = null
    private var cancelButton: Button? = null
    private var addRelativeButton: Button? = null
    private var relativeTypeSpinner: Spinner? = null

    @Before
    fun setUp(){

        activity.apply {
            firstNameET = findViewById(R.id.firstName_EditText)
            middleNameET = findViewById(R.id.middleName_EditText)
            lastNameET = findViewById(R.id.lastName_EditText)
            genderButton = findViewById(R.id.gender_RadioGroup)
            dateOfBirthET = findViewById(R.id.dateOfBirth_EditText)
            dateOfDeathET = findViewById(R.id.dateOfDeath_EditText)
            cancelButton = findViewById(R.id.cancel_Button)
            addRelativeButton = findViewById(R.id.addRelative_Button)
            relativeTypeSpinner = findViewById(R.id.relativeTypes_Spinner)
        }


    }

    @Test
    fun editTextFields_shouldHaveHints() {

        assertThat(firstNameET!!.hint.toString(), `is`("First Name"))
        assertThat(middleNameET!!.hint.toString(), `is`("Middle Name"))
        assertThat(lastNameET!!.hint.toString(), `is`("Last Name"))
        assertThat(dateOfBirthET!!.hint.toString(), `is`("MM/DD/YYYY or About YYYY"))
        assertThat(dateOfDeathET!!.hint.toString(), `is`("MM/DD/YYYY or About YYYY"))
    }

    @Test
    fun clickCancel_shouldGoToMyFamilyActivity() {
        cancelButton!!.performClick()
        assertIntent(MyFamilyActivity::class.java)
    }

    @Test
    fun shouldNotAddRelativeWhenWithInvalidFormData() {

        val genderRB = activity.findViewById<RadioButton>(R.id.male_RadioButton)
        whenRequiredFormDataFieldsAreBlank()

        addRelativeButton!!.performClick()

        assertThat(firstNameET!!.error?.toString(), `is`( "First Name cannot be blank"))
        assertThat(lastNameET!!.error?.toString(), `is`( "Last Name cannot be blank"))
        assertThat(genderRB.error?.toString(), `is`(""))

        assertIntent(null)
    }

    private fun whenRequiredFormDataFieldsAreBlank() {

        firstNameET!!.setText("")
        lastNameET!!.setText("")
        genderButton!!.clearCheck()


    }

    @Test
    fun shouldNotAddRelativeWhenTypeOfRelativeNotSelected() {

        relativeTypeSpinner!!.setSelection(INVALID_RELATIVE_TYPE_SELECTION)
        addRelativeButton!!.performClick()
        assertThat(activity.findViewById<TextView>(R.id.relativeTypeLabel).error?.toString(), `is`(""))
        assertIntent(null)

    }

    private fun assertIntent( expectedIntentClass: Class<*>?) {

        val expectedIntent = when (expectedIntentClass != null) {
            true -> Intent(activity, expectedIntentClass)
            false -> null
        }
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        Assert.assertEquals(expectedIntent?.component, actualIntent?.component)
    }


}