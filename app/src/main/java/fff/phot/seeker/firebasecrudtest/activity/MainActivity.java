package fff.phot.seeker.firebasecrudtest.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fff.phot.seeker.firebasecrudtest.R;
import fff.phot.seeker.firebasecrudtest.model.Employee;

public class MainActivity extends AppCompatActivity {

    EditText etName, etsalary;
    Spinner etDept;
    TextView textView;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        etName = findViewById(R.id.editTextName);
        etsalary = findViewById(R.id.editTextSalary);
        etDept = findViewById(R.id.spinnerDepartment);
        // textView=findViewById(R.id.textViewViewEmployees);
        initvariblemethod();

    }

    private void initvariblemethod() {
        reference = FirebaseDatabase.getInstance().getReference("CRUD_OPERATION_1");
    }

    public void SaveData(View view) {
//call empty constructor
        Employee e = new Employee();


        String ename, edept, esalary, ejoindate;
        //double salary;
        ename = etName.getText().toString().trim();
        esalary = etsalary.getText().toString().trim();
        /// salary = Double.valueOf(esalary);
        edept = etDept.getSelectedItem().toString();

        //current date seting
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        ejoindate = sdf.format(cal.getTime());

        if (ename.isEmpty()) {
            etName.setError("PLS ENTER NAME");
            etName.requestFocus();
            return;
        }
        if (esalary.isEmpty()) {
            etsalary.setError("PLS ENTER SALARY");
            etsalary.requestFocus();
            return;
        }

        String user_id = reference.push().getKey();

        Employee employee = new Employee(ename, edept, ejoindate, esalary, user_id);


        reference.child(user_id).setValue(employee).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "sussecful save", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "error data save", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void viewData(View view) {

        startActivity(new Intent(this, EmployelistActivity.class));
    }
}
