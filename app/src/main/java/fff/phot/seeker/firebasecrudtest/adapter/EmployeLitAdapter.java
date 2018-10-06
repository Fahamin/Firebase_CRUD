package fff.phot.seeker.firebasecrudtest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import fff.phot.seeker.firebasecrudtest.R;
import fff.phot.seeker.firebasecrudtest.model.Employee;

public class EmployeLitAdapter extends BaseAdapter {

    private Context context;
    List<Employee> employeeList;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("CRUD_OPERATION_1");

    public EmployeLitAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_layout_employee, null);
        }

        TextView tvename, tvedept, tvesalary, tvejoindate;

        ImageButton btnedit, btndelet;

        tvename = convertView.findViewById(R.id.textViewName);
        tvedept = convertView.findViewById(R.id.textViewDepartment);
        tvesalary = convertView.findViewById(R.id.textViewSalary);
        tvejoindate = convertView.findViewById(R.id.textViewJoiningDate);

        btndelet = convertView.findViewById(R.id.buttonDeleteEmployee);
        btnedit = convertView.findViewById(R.id.buttonEditEmployee);


        tvename.setText(employeeList.get(position).getName());
        tvedept.setText(employeeList.get(position).getDept());
        tvesalary.setText(employeeList.get(position).getSalary());
        tvejoindate.setText(employeeList.get(position).getJoinDate());

        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletEmploye(position);
            }

        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmploye(position);
            }


        });
        return convertView;
    }

    private void updateEmploye(final int pos) {
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.update_popup, null);
        ab.setView(view);
        ab.setCancelable(false);

        final String name,salary,dept;

//recive present value
        name =employeeList.get(pos).getName();
        salary =employeeList.get(pos).getSalary();
        dept =employeeList.get(pos).getDept();
        final String joinDate=employeeList.get(pos).getJoinDate();
        final  String user_mode = employeeList.get(pos).getUser_id();


        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextSalary = view.findViewById(R.id.editTextSalary);
        final Spinner spinnerDepartment = view.findViewById(R.id.spinnerDepartment);

        /*editTextName.setText();
        editTextSalary.setText(String.valueOf(employee.getSalary()));*/

        final  AlertDialog dialog = ab.create();
        dialog.show();

        view.findViewById(R.id.btnUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = editTextName.getText().toString().trim();
                String newSalary = editTextSalary.getText().toString().trim();
                String newDept = spinnerDepartment.getSelectedItem().toString();

                if (newName.isEmpty()) {
                    editTextName.setError("Name can't be blank");
                    editTextName.requestFocus();
                    return;
                }

                if (newSalary.isEmpty()) {
                    editTextSalary.setError("Salary can't be blank");
                    editTextSalary.requestFocus();
                    return;
                }

                reference.child(user_mode).setValue(new Employee(newName,newDept,joinDate,newSalary,user_mode)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(context, " update employe", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(context, " not update data", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }

                });
            }
        });


    }

    private void deletEmploye(final int position) {

        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("Are you sure you want to delete this?");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference.child(employeeList.get(position).getUser_id()).removeValue();

                notifyDataSetChanged();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
    }
}
