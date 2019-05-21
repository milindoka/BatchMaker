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



public class PreferenceDialog
{

    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;
    String Zone="Mumbai Divisional Board, Vashi,Navi Mumbai - 400703";
    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";
    String Medium="English",MonthYear="Feb-2020";
    String tempstr;

     void SetPreferrenceDlg(final Context context)
    {
        final Dialog myDialog;
        myDialog =  new Dialog(context);
        myDialog.setTitle("Save Batch Preferences");
        myDialog.setContentView(R.layout.setpreferencesdlg);
        myDialog.setCancelable(true);
        myDialog.getWindow().getAttributes().width = LayoutParams.FILL_PARENT;



        final EditText FZone = (EditText) myDialog.findViewById(R.id.EB_ZONE);
        FZone.setText(Zone);

        final EditText FMonthyear = (EditText) myDialog.findViewById(R.id.EB_MONTHYEAR);
        FMonthyear.setText(MonthYear);

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

        final EditText FMedium = (EditText) myDialog.findViewById(R.id.EB_MEDIUM);
        FMedium.setText(Medium);

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

                tempstr=FZone.getText().toString(); Zone=tempstr;
                tempstr=FMonthyear.getText().toString(); MonthYear=tempstr;
                tempstr=FSchool.getText().toString(); School=tempstr;
                tempstr=FIndex.getText().toString();  Index=tempstr;
                tempstr=FStrim.getText().toString();  Strim=tempstr;
                tempstr=FStandard.getText().toString();  Standard=tempstr;
                tempstr=FSubject.getText().toString();  Subject=tempstr;
                tempstr=FSubcode.getText().toString(); SubjectCode=tempstr;
                tempstr=FMedium.getText().toString(); Medium=tempstr;
                tempstr=FType.getText().toString();  Type=tempstr;
                tempstr=FEmail1.getText().toString();  Email1=tempstr;
                tempstr=FEmail2.getText().toString();  Email2=tempstr;
                tempstr=FBatchcreator.getText().toString(); BatchCreator=tempstr;

                SharedPreferences settings = context.getSharedPreferences("BATCHMAKER-PREF", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Zone",Zone);
                editor.putString("MonthYear",MonthYear);
                editor.putString("School",School);
                editor.putString("Index", Index);
                editor.putString("Strim", Strim);
                editor.putString("Standard", Standard);
                editor.putString("Subject", Subject);
                editor.putString("SubjectCode", SubjectCode);
                editor.putString("Medium",Medium);
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

    Zone=settings.getString("Zone",Zone);
    MonthYear=settings.getString("MonthYear",MonthYear);
    School=settings.getString("School",School);
    Index=settings.getString("Index", Index);
    Strim=settings.getString("Strim", Strim);
    Standard=settings.getString("Standard", Standard);
    Subject=settings.getString("Subject", Subject);
    SubjectCode=settings.getString("SubjectCode", SubjectCode);
    Medium=settings.getString("Medium", Medium);
    Type=settings.getString("Type", Type);
    Email1=settings.getString("Email1", Email2);
    Email2=settings.getString("Email2", Email2);
    BatchCreator=settings.getString("BatchCreator", BatchCreator);
}


}
