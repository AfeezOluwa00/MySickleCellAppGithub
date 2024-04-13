package com.example.mysicklecellapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterPage extends AppCompatActivity {

    private EditText editTextRegisterEmail, editTextRegisterPwd, editTextRegisterConfirmPwd, editTextRegisterName, editTextNumber;
    private Spinner spinnerSickleCellType, spinnerDefaultCity;
    private ProgressBar progressBar;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Toast.makeText(RegisterPage.this, "You can now Register", Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.progressBar);
        editTextRegisterEmail = findViewById(R.id.email);
        editTextRegisterPwd = findViewById(R.id.password);
        editTextRegisterConfirmPwd = findViewById(R.id.confirmPassword);
        editTextRegisterName = findViewById(R.id.name);
        editTextNumber = findViewById(R.id.number);
        datePicker = findViewById(R.id.datePicker);
        spinnerSickleCellType = findViewById(R.id.sickleCellType);
        spinnerDefaultCity = findViewById(R.id.defaultCity);

        // Set up the spinners for Sickle Cell Type and Default City
        ArrayAdapter<CharSequence> sickleCellTypeAdapter = ArrayAdapter.createFromResource(this, R.array.sickle_cell_types_array, android.R.layout.simple_spinner_item);
        sickleCellTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSickleCellType.setAdapter(sickleCellTypeAdapter);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDefaultCity.setAdapter(cityAdapter);

        Button buttonRegister = findViewById(R.id.RegisterButton);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve values from the UI components
                String textEmail = editTextRegisterEmail.getText().toString();
                String textPassword = editTextRegisterPwd.getText().toString();
                String textConfirmedPassword = editTextRegisterConfirmPwd.getText().toString();
                String textName = editTextRegisterName.getText().toString();
                String textNumber = editTextNumber.getText().toString();

                // Additional code to validate and process the new fields
                if (TextUtils.isEmpty(textNumber)) {
                    Toast.makeText(RegisterPage.this, "Please enter Number", Toast.LENGTH_LONG).show();
                    editTextNumber.setError("Number is required");
                    editTextNumber.requestFocus();
                    return;
                }

                // Example: Get the selected date from DatePicker
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                // Additional code to process the date, for example:
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String textDateOfBirth = dateFormat.format(calendar.getTime());
                // Do something with the date...

                // Get selected values from spinners
                String textSickleCellType = spinnerSickleCellType.getSelectedItem().toString();
                String textDefaultCity = spinnerDefaultCity.getSelectedItem().toString();

                // Save user details to Firebase Database
                saveUserToDatabase(textEmail, textPassword, textName, textNumber, textDateOfBirth, textSickleCellType, textDefaultCity);
            }
        });
    }

    private void saveUserToDatabase(String email, String password, String name, String number,
                                    String dateOfBirth, String sickleCellType, String defaultCity) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterPage.this, "User has been created", Toast.LENGTH_LONG).show();

                    // Get the user ID from the AuthResult
                    String userId = task.getResult().getUser().getUid();

                    // Get a reference to the "Users" node
                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");

                    // Create a new node under "Users" with the user ID
                    DatabaseReference userNodeRef = usersRef.child(userId);

                    // Save user details as key-value pairs
                    userNodeRef.child("email").setValue(email);
                    userNodeRef.child("password").setValue(password);
                    userNodeRef.child("name").setValue(name);
                    userNodeRef.child("number").setValue(number);
                    userNodeRef.child("dateOfBirth").setValue(dateOfBirth);
                    userNodeRef.child("sickleCellType").setValue(sickleCellType);
                    userNodeRef.child("defaultCity").setValue(defaultCity);

                    // Inform the user that registration is successful or handle errors
                    Toast.makeText(RegisterPage.this, "Registration successful", Toast.LENGTH_LONG).show();
                } else {
                    // Handle the case where user creation failed
                    Toast.makeText(RegisterPage.this, "User creation failed. Please try again", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
