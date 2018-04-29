package com.twistedgeinc.apps.tiwapa.family

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.twistedgeinc.apps.tiwapa.R
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener


class AddRelativeActivity : AppCompatActivity(), OnItemSelectedListener {

    private var firstNameET: EditText? = null
    private var lastNameET: EditText? = null
    private var dateOfBirthET: EditText? = null
    private var genderRG: RadioGroup? = null
    private var typeOfRelative = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_relative)
        initView()

    }

    private fun initView() {

        firstNameET = findViewById(R.id.firstName_EditText)
        lastNameET = findViewById(R.id.lastName_EditText)
        dateOfBirthET = findViewById(R.id.dateOfBirth_EditText)
        genderRG = findViewById(R.id.gender_RadioGroup)

        val cancelButton = findViewById<Button>(R.id.cancel_Button)
        cancelButton.setOnClickListener( { cancelButtonOnClick()})

        val addRelativeButton = findViewById<Button>(R.id.addRelative_Button)
        addRelativeButton.setOnClickListener( { addRelativeOnClick()})

        val relativeTypeSpinner = findViewById<Spinner>(R.id.relativeTypes_Spinner)
        relativeTypeSpinner.onItemSelectedListener = this@AddRelativeActivity

        val relativeTypesAdapter = ArrayAdapter.createFromResource(this,
                R.array.relativeTypes_array, android.R.layout.simple_spinner_item)

        relativeTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        relativeTypeSpinner.adapter = relativeTypesAdapter
    }

    //Override Functions
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        typeOfRelative = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    //Click Listeners
    private fun cancelButtonOnClick(){
        val myFamilyActivity = Intent(this, MyFamilyActivity::class.java)
        startActivity(myFamilyActivity)
        finish()
    }

    private fun addRelativeOnClick() {

        if (!isValidFormData()) {
            return
        }

        //TODO: Add Relative to Database

        val myFamilyActivity = Intent(this, MyFamilyActivity::class.java)
        startActivity(myFamilyActivity)
        finish()

    }

    //Private Functions
    private fun isValidFormData(): Boolean {
        var bValid = true

        if (firstNameET!!.text.isEmpty()) {
            firstNameET!!.error = "First Name cannot be blank"
            bValid = false
        }

        if(lastNameET!!.text.isEmpty()) {
            lastNameET!!.error = "Last Name cannot be blank"
            bValid = false
        }

        if (genderRG!!.checkedRadioButtonId == -1){
            findViewById<RadioButton>(R.id.male_RadioButton).error = ""
            findViewById<RadioButton>(R.id.female_RadioButton).error = ""

            bValid = false
        }

        if (typeOfRelative == 0) {
            findViewById<TextView>(R.id.relativeTypeLabel).error = ""
            bValid = false
        }

        return bValid
    }
}


