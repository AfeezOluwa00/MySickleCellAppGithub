package com.example.mysicklecellapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyCalenderActivity extends AppCompatActivity {

    private TextInputEditText editTextDate;
    private MaterialAutoCompleteTextView dropdownAppointmentTime;
    private MaterialAutoCompleteTextView dropdownAppointmentType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calender);

        // Initialize views
        editTextDate = findViewById(R.id.editTextDate);
        dropdownAppointmentTime = findViewById(R.id.dropdownAppointmentTime);
        dropdownAppointmentType = findViewById(R.id.dropdownAppointmentType);

        // Set appointment time dropdown
        String[] appointmentTimes = generateAppointmentTimes();
        ArrayAdapter<String> adapterAppointmentTime = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                appointmentTimes
        );
        dropdownAppointmentTime.setAdapter(adapterAppointmentTime);

        // Set appointment type dropdown
        ArrayAdapter<CharSequence> adapterAppointmentType = ArrayAdapter.createFromResource(
                this,
                R.array.appointment_types,
                android.R.layout.simple_dropdown_item_1line
        );
        dropdownAppointmentType.setAdapter(adapterAppointmentType);
    }

    public void showDatePicker(View view) {
        // Implement date picker logic here
        // This can open a DatePickerDialog or any other method to select a date
    }

    public void saveAppointment(View view) {
        // Retrieve selected values
        String selectedDate = editTextDate.getText().toString();
        String selectedTime = dropdownAppointmentTime.getText().toString();
        String selectedType = dropdownAppointmentType.getText().toString();

        // Save the data to the database under the "appointments" node
        // You can use Firebase Realtime Database or any other database mechanism
        // Implement your database saving logic here

        Toast.makeText(this, "Appointment saved!", Toast.LENGTH_SHORT).show();
    }

    // Helper method to generate appointment times
    private String[] generateAppointmentTimes() {
        // Implement logic to generate appointment times in 30-minute intervals
        // For example, generate times from 9:00 AM to 5:00 PM
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);

        int numIntervals = (17 - 9) * 2; // 9 AM to 5 PM

        String[] times = new String[numIntervals];
        for (int i = 0; i < numIntervals; i++) {
            times[i] = sdf.format(calendar.getTime());
            calendar.add(Calendar.MINUTE, 30);
        }

        return times;
    }
}

