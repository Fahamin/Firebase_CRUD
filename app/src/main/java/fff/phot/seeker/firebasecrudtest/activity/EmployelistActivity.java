package fff.phot.seeker.firebasecrudtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fff.phot.seeker.firebasecrudtest.adapter.EmployeLitAdapter;
import fff.phot.seeker.firebasecrudtest.model.Employee;
import fff.phot.seeker.firebasecrudtest.R;

public class EmployelistActivity extends AppCompatActivity {

    DatabaseReference reference;
    ListView employeeListView;
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employlist);

        initViewmethod();
        initVariblemethod();
        loadEmployeData();

    }
    private void initViewmethod() {

        employeeListView = findViewById(R.id.listviewEmplyId);

    }
    private void initVariblemethod() {
        reference = FirebaseDatabase.getInstance().getReference("CRUD_OPERATION_1");

    }

    private void loadEmployeData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // clear list after editing
               // employeeList.clear();

                // modify data to iterable mode use for each loop
                Iterable<DataSnapshot> allemployees = dataSnapshot.getChildren();
                for (DataSnapshot single_employee : allemployees) {
                    // retrive single children from employee database
                    Employee employee_children = single_employee.getValue(Employee.class);
                    employeeList.add(employee_children);
                }
                //adapter set for custom list view
                EmployeLitAdapter adapter = new EmployeLitAdapter(EmployelistActivity.this, employeeList);
                employeeListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // any error we found
                Log.e("read_error", databaseError.getMessage());
            }
        });
    }


}
