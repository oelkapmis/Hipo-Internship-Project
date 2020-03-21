package com.example.hipo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddingActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var nameTV: TextView
    lateinit var ageTV: TextView
    lateinit var locationTV: TextView
    lateinit var githubTV: TextView
    lateinit var positionTV: TextView
    lateinit var yearsTV: TextView
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)
        bindViews()
    }

    override fun onClick(view: View?) {

        //When user clicks 'Submit' button, we check if all prompts are filled properly
        //If it is, then addMember() function is called to add this new member to the list
        //Toast a success message to provide feedback to the user
        //Finish this activity and return home screen
        if(isFormFilled()){

            addMember()
            successMessage()
            finish()
        }
        else{
            failMessage()
        }

    }

    private fun isFormFilled():Boolean{

        // Whole 'when-block' is given as return value for this function
        //For each textView, we check if its isEmpty() == true,
        //If it is, then 'when-block' returns false and isFormFilled() as well.
        //Otherwise, true.
        return when(true) {

            nameTV.text.toString().trim().isEmpty() -> false
            ageTV.text.toString().isEmpty() -> false
            locationTV.text.toString().trim().isEmpty() -> false
            githubTV.text.toString().trim().isEmpty() -> false
            positionTV.text.toString().trim().isEmpty() -> false
            yearsTV.text.toString().isEmpty() -> false
            else -> true
        }
    }

    private fun addMember(){

        val member = Member()
        member.mName = nameTV.text.toString()
        member.mAge = ageTV.text.toString().toInt()
        member.mLocation = locationTV.text.toString()
        member.mGithub = githubTV.text.toString()
        member.Hipo().mPosition = positionTV.text.toString()
        member.Hipo().mYears = yearsTV.text.toString().toInt()

        /*
            Here, I need to add the above member object to my hipo.json file,
            and thus it can be appeared on the members list (listView).
            But I couldn't succeed that yet.
            I will continue researching how to add an object to my JSON file.
        */
    }

    private fun bindViews(){

        nameTV = findViewById(R.id.form_name)
        ageTV = findViewById(R.id.form_age)
        locationTV = findViewById(R.id.form_location)
        githubTV = findViewById(R.id.form_github)
        positionTV = findViewById(R.id.form_position)
        yearsTV = findViewById(R.id.form_years)
        submitButton = findViewById(R.id.submitButton)
        submitButton.setOnClickListener(this)

    }

    private fun successMessage(){
        Toast.makeText(this@AddingActivity,"Successful!", Toast.LENGTH_SHORT).show()
    }

    private fun failMessage(){
        Toast.makeText(this@AddingActivity,"All blanks must be filled!", Toast.LENGTH_SHORT).show()
    }
}

