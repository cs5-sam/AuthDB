package android.example.authsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


////////////////////////////////////////////////////
//////////////// REGISTER ACTIVITY/////////////////////
////////////////////////////////////////////////////

    EditText username, password,email,country,dob;
    RadioGroup gender;
    Button register, cancel;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        country = findViewById(R.id.country);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);
        databaseHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String emailValue = email.getText().toString();
                String countryValue = country.getText().toString();
                String dobValue = dob.getText().toString();
                RadioButton checkBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkBtn.getText().toString();

                if(usernameValue.length() > 1){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("username",usernameValue);
                    contentValues.put("password",passwordValue);
                    contentValues.put("email",emailValue);
                    contentValues.put("country",countryValue);
                    contentValues.put("dob",dobValue);
                    contentValues.put("gender",genderValue);
                    databaseHelper.insertUser(contentValues);
                    Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Enter the Values!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
