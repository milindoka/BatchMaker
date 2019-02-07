package ilugbom.org.in.batchmaker;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by milind on 7/2/19.
 */



public class ListOperations
{ private MainActivity MA;
  void SetMA(MainActivity MA){this.MA=MA;}
  int maxstrength=5000;
  String froll,lroll;
    public void FillList(String FirstSeat,String LastSeat)
    {
        MA.initItemList.removeAll(MA.initItemList);
        MA.Roll.removeAll(MA.Roll);
        MA.checkmark.removeAll(MA.checkmark);

        String temproll=FirstSeat;

        int i=0;
        for(i=0;i<maxstrength;i++)
        {   MA.Roll.add(temproll);
            MA.checkmark.add(Boolean.FALSE);
            ListViewItemDTO dto=new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(temproll);

            MA.initItemList.add(dto);
            if(temproll.contains(LastSeat)) break;
            temproll=Increment(temproll);
        }

        //((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();

    }

public void SelectAll()
{

    int size = MA.initItemList.size();
    for(int i=0;i<size;i++)
    {
        ListViewItemDTO dto = MA.initItemList.get(i);
        dto.setChecked(true);
    }

    MA.listViewDataAdapter.notifyDataSetChanged();

}


    public void SelectNone()
    {
      int size = MA.initItemList.size();
        for(int i=0;i<size;i++)
        {
            ListViewItemDTO dto = MA.initItemList.get(i);
            dto.setChecked(false);
        }

        MA.listViewDataAdapter.notifyDataSetChanged();

    }

  public void SelectReverse()
  {
      int size = MA.initItemList.size();
      for(int i=0;i<size;i++)
      {
          ListViewItemDTO dto = MA.initItemList.get(i);

          if(dto.isChecked())
          {
              dto.setChecked(false);
          }else {
              dto.setChecked(true);
          }
      }

      MA.listViewDataAdapter.notifyDataSetChanged();

  }


    private String Increment(String alphaNumericString)
    {  char[] an = alphaNumericString.toCharArray();
        int i = an.length - 1;
        while (true)
        {   if(an[i]<'0' || an[i]>'9') return new String(an);
            if (i <= 0)
                try { throw new Exception("Maxed out number!!!"); }
                catch (Exception e)
                { e.printStackTrace(); }
            an[i]++;

            if (an[i] - 1 == '9')
            {
                an[i] = '0';
                i--;
                continue;
            }


            return new String(an);
        }
    }



    void GetNewRoll()
    {
    //    NewNow=false; modified=false;
        final AlertDialog.Builder alert = new AlertDialog.Builder(MA);
        alert.setTitle("Create New Batch");


        final LinearLayout layout = new LinearLayout(MA);
        layout.setOrientation(LinearLayout.VERTICAL);

        InputFilter[] FilterArray = new InputFilter[2];
        FilterArray[0] = new InputFilter.LengthFilter(15);
        FilterArray[1] = new InputFilter.AllCaps();

        final TextView t1=new TextView(MA);//"First Seat Number :");
        t1.setText("  Enter First Seat Number");

        final EditText input1 = new EditText(MA);
        input1.setSingleLine();
        input1.setInputType(InputType.TYPE_CLASS_TEXT);
        //input1.setHint("First Seat No...");
        input1.setText(froll);
        input1.setFilters(FilterArray);


        final TextView t2=new TextView(MA);//"First Seat Number :");
        t2.setText("  Enter Last Seat Number");

        final EditText input2 = new EditText(MA);
        input2.setSingleLine();
        input2.setInputType(InputType.TYPE_CLASS_TEXT);
        input2.setText(lroll);
        //input2.setHint("Enter Last Seat No...");
        input2.setFilters(FilterArray);

        layout.addView(t1);
        layout.addView(input1);
        layout.addView(t2);
        layout.addView(input2);


        alert.setView(layout);


        alert.setPositiveButton("Create", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {

                froll = input1.getText().toString();
                lroll = input2.getText().toString();
                boolean found=false;


                String temproll=froll;
                FillList(froll,lroll);
             //   AlreadyPicked=false;
             //   FileNameWithPath=""; ///fresh file created

                MA.listViewDataAdapter.notifyDataSetChanged();

                InputMethodManager imm = (InputMethodManager) MA.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input1.getWindowToken(),0);
              //  modified=false;
              //  end=false;OpenNow=false;NewNow=false;

                //	!!! variable reset to force header update from teacher
                //BatchNo="00";Date="";BatchTime="";FileNameWithPath="";
                //  !!! Set Current Date if new file created after old file

              //  mainListView.setSelection(0);

              //  PickCounter=0;
              //  UpdateTitle(); ///To reset filename and counter display
              //  GetHeaderDlg();

            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                InputMethodManager imm = (InputMethodManager) MA.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input1.getWindowToken(),0);
                return;
            }
        });
        alert.show();
        InputMethodManager imm = (InputMethodManager) MA.getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }










}