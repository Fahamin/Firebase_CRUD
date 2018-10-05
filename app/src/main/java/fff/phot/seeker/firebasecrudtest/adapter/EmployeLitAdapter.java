package fff.phot.seeker.firebasecrudtest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fff.phot.seeker.firebasecrudtest.R;
import fff.phot.seeker.firebasecrudtest.model.Employee;

public class EmployeLitAdapter extends BaseAdapter {

    private Context context;
    List<Employee> employeeList ;

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

     /*   btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletEmploye(position);
            }

        });*/
        return convertView;
    }


    private void deletEmploye(final int position) {

        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("Are you sure you want to delete this?");
     ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             employeeList.remove(position);
             notifyDataSetChanged();
         }
     }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             dialog.cancel();
         }
     }).show() ;
    }
}
