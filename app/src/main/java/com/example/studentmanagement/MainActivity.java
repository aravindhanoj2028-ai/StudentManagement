
package com.example.studentmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etRoll, etCourse;
    Button btnAdd, btnDisplay;
    TextView tvNames;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etRoll = findViewById(R.id.etRoll);
        etCourse = findViewById(R.id.etCourse);

        btnAdd = findViewById(R.id.btnAdd);
        btnDisplay = findViewById(R.id.btnDisplay);

        tvNames = findViewById(R.id.tvNames);

        db = new DatabaseHelper(this);

        btnAdd.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String roll = etRoll.getText().toString();
            String course = etCourse.getText().toString();

            if (name.isEmpty() || roll.isEmpty()
                    || course.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all details",
                        Toast.LENGTH_SHORT).show();

            } else {

                boolean result =
                        db.addStudent(name, roll, course);

                if (result) {

                    Toast.makeText(this,
                            "Student Added Successfully",
                            Toast.LENGTH_SHORT).show();

                    etName.setText("");
                    etRoll.setText("");
                    etCourse.setText("");

                } else {

                    Toast.makeText(this,
                            "Failed to Add Student",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDisplay.setOnClickListener(v -> {

            String students = db.getAllStudents();

            tvNames.setText(students);
        });
    }
}
