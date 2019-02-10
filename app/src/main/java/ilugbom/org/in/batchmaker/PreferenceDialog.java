package ilugbom.org.in.batchmaker;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;


public class PreferenceDialog
{




    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;
    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";

    String tempstr;
    /*
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
*/

     void SetPreferrenceDlg(final Context context)
    {
        final Dialog myDialog;
        myDialog =  new Dialog(context);
        myDialog.setTitle("Save Batch Preferences");
        myDialog.setContentView(R.layout.setpreferencesdlg);
        myDialog.setCancelable(true);
        myDialog.getWindow().getAttributes().width = LayoutParams.FILL_PARENT;




        final EditText FSchool = (EditText) myDialog.findViewById(R.id.EB_SCHOOL);
        FSchool.setText(School);


        final EditText FIndex = (EditText) myDialog.findViewById(R.id.EB_INDEX);
        FIndex.setText(Index);

        final EditText FStrim = (EditText) myDialog.findViewById(R.id.EB_STRIM);
        FStrim.setText(Strim);

        final EditText FStandard = (EditText) myDialog.findViewById(R.id.EB_STANDARD);
        FStandard.setText(Standard);

        final EditText FSubject = (EditText) myDialog.findViewById(R.id.EB_SUBJECT);
        FSubject.setText(Subject);

        final EditText FSubcode = (EditText) myDialog.findViewById(R.id.EB_SUBCODE);
        FSubcode.setText(SubjectCode);

        final EditText FType = (EditText) myDialog.findViewById(R.id.EB_TYPE);
        FType.setText(Type);

        final EditText FEmail1 = (EditText) myDialog.findViewById(R.id.EB_EMAIL1);
        FEmail1.setText(Email1);

        final EditText FEmail2 = (EditText) myDialog.findViewById(R.id.EB_EMAIL2);
        FEmail2.setText(Email2);

        final EditText FBatchcreator = (EditText) myDialog.findViewById(R.id.EB_BATCHCREATOR);
        FBatchcreator.setText(BatchCreator);

        Button buttoncancel = (Button) myDialog.findViewById(R.id.BtnCancel);
        buttoncancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v)
            {myDialog.dismiss();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });

        Button buttonok = (Button) myDialog.findViewById(R.id.BtnUpdate);
        buttonok.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {

                tempstr=FSchool.getText().toString(); School=tempstr;
                tempstr=FIndex.getText().toString();  Index=tempstr;
                tempstr=FStrim.getText().toString();  Strim=tempstr;
                tempstr=FStandard.getText().toString();  Standard=tempstr;
                tempstr=FSubject.getText().toString();  Subject=tempstr;
                tempstr=FSubcode.getText().toString(); SubjectCode=tempstr;
                tempstr=FType.getText().toString();  Type=tempstr;
                tempstr=FEmail1.getText().toString();  Email1=tempstr;
                tempstr=FEmail2.getText().toString();  Email2=tempstr;
                tempstr=FBatchcreator.getText().toString(); BatchCreator=tempstr;

                SharedPreferences settings = context.getSharedPreferences("BATCHMAKER-PREF", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("School",School);
                editor.putString("Index", Index);
                editor.putString("Strim", Strim);
                editor.putString("Standard", Standard);
                editor.putString("Subject", Subject);
                editor.putString("SubjectCode", SubjectCode);
                editor.putString("Type", Type);
                editor.putString("Email1", Email2);
                editor.putString("Email2", Email2);
                editor.putString("BatchCreator", BatchCreator);
                editor.commit();


                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FSchool.getWindowToken(),0);

                myDialog.dismiss();

            }
        });



        myDialog.show();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


void LoadPreferrences(final Context context)
{
    ////load preferences
    SharedPreferences settings = context.getSharedPreferences("BATCHMAKER-PREF", 0);
    School=settings.getString("School",School);
    Index=settings.getString("Index", Index);
    Strim=settings.getString("Strim", Strim);
    Standard=settings.getString("Standard", Standard);
    Subject=settings.getString("Subject", Subject);
    SubjectCode=settings.getString("SubjectCode", SubjectCode);
    Type=settings.getString("Type", Type);
    Email1=settings.getString("Email1", Email2);
    Email2=settings.getString("Email2", Email2);
    BatchCreator=settings.getString("BatchCreator", BatchCreator);



}





}
