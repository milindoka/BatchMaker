package ilugbom.org.in.batchmaker;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreferenceDialog
{
    String college="School/College";
    String teacher="Name";
    String subject="Subject";
    String email="Email";


    public void showPreferenceDialog(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Set Preferences");
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.preferences_dialog);
        dialog.show();

        final EditText etCollege = dialog.findViewById(R.id.college);
        final EditText etTeacher = dialog.findViewById(R.id.teacher);
        final EditText etSubject = dialog.findViewById(R.id.subject);
        final EditText etEmail = dialog.findViewById(R.id.email);
        /// set current values
        etCollege.setText(college);
        etTeacher.setText(teacher);
        etSubject.setText(subject);
        etEmail.setText(email);
        ////////
        Button mBtn_cancel = dialog.findViewById(R.id.btn_cancel);
        Button mBtn_ok = dialog.findViewById(R.id.btn_ok);

        mBtn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        mBtn_ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                college=etCollege.getText().toString();
                teacher=etTeacher.getText().toString();
                subject=etSubject.getText().toString();
                email=etEmail.getText().toString();
               // SaveDivisionsInPrefs();
                dialog.dismiss();
            }
        });
    }

}
