package ilugbom.org.in.batchmaker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.view.ViewGroup.LayoutParams;

import com.itextpdf.text.DocumentException;

/**
 * Created by milind on 8/2/19.
 */



public class FileSaveLoad
{


    /////////////Show Msg Functions /////////////////////////////////////
    public void show(int tempnum)
    {
        Toast.makeText(MA,String.valueOf(tempnum),Toast.LENGTH_SHORT).show();
    }

    public void show(String tempstring)
    {
        Toast.makeText(MA,tempstring,Toast.LENGTH_SHORT).show();
    }

//////////////////////////////////////////////////////////////////


    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}

  //  boolean NewNow=false,selectall=false,end=false,OpenNow=false;
    String Zone="Mumbai Divisional Board, Vashi,Navi Mumbai - 400703",
           MonthYear="Feb-2021", BatchNo="01",Date="",BatchTime="",
           School="SIWS College",Index="J-31.04.005", CenterNo="3207",
           Strim="Science",Standard="HSC", Subject="Mathematics",
           SubjectCode="40",Medium="English",Type="Practical",
           BatchCreator="MO",BatchSession="";

   String FileNameWithPath="";
   String PDFNameWithPath="";
   String PDFChartNameWithPath="";

    void SaveList()
    {
if(MA.checkmarkCount>32) { MA.show("Batch Strength > 32");
                           MA.show("Reduce Strength");
                           return;}
    //    if(!AlreadyPicked) { PickRoutine();  }

        int i;
    //    modified=false;
        String tmpStr;
        String txtData = "\n";

        txtData+="Zone          § ";txtData+=MA.PD.Zone;     txtData+='\n';
        txtData+="Month & Year  § ";txtData+=MA.PD.MonthYear;txtData+='\n';
        txtData+="School        § ";txtData+=MA.PD.School;   txtData+='\n';
        txtData+="Index         § ";txtData+=MA.PD.Index;    txtData+='\n';
        txtData+="Center No     § ";txtData+=MA.PD.CenterNo; txtData+='\n';
        txtData+="Stream        § ";txtData+=MA.PD.Strim;    txtData+='\n';
        txtData+="Standard      § ";txtData+=MA.PD.Standard; txtData+='\n';
        txtData+="Subject       § ";txtData+=MA.PD.Subject;  txtData+='\n';
        txtData+="Subject Code  § ";txtData+=MA.PD.SubjectCode;    txtData+='\n';
        txtData+="Medium        § ";txtData+=MA.PD.Medium;   txtData+='\n';
        txtData+="Type          § ";txtData+=MA.PD.Type;     txtData+='\n';
        txtData+="Batch Number  § ";txtData+=BatchNo;        txtData+='\n';
        txtData+="Batch Creater § ";txtData+=MA.PD.BatchCreator;   txtData+='\n';
        txtData+="Date          § ";txtData+=Date;           txtData+="\n";
        txtData+="Time          § ";txtData+=BatchTime;      txtData+='\n';
        txtData+="Session       § ";txtData+=BatchSession;   txtData+="\n";
        txtData+="\n";
        txtData+="=== Reserved Line ====\n";
        txtData+="\n";
        txtData+="Seat Nos §\n";
        txtData+="\n";

        MA.PGEN.CheckedNumbers.removeAll(MA.PGEN.CheckedNumbers);
        for(i=0;i<MA.initItemList.size();i++)
        { boolean temp=MA.initItemList.get(i).isChecked();
            if(temp)
            { //show(Roll.get(i));
                txtData+=MA.initItemList.get(i).getItemText();
                MA.PGEN.CheckedNumbers.add(MA.initItemList.get(i).getItemText());
                txtData+='\n';
            }
        }


        try {
            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtData);
            myOutWriter.close();
            fOut.close();
            MA.modified=false;
            show("Saved on SD card");

            MA.fab.setBackgroundTintList(MA.getResources().getColorStateList(R.color.colorGreen));
            MA.FC.setText("");

        } catch (Exception e) {
            Toast.makeText(MA, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }


        try
          {
            MA.PGEN.CreateCurrentPDF(PDFNameWithPath);
           }
           catch (FileNotFoundException e)
           { e.printStackTrace(); }
           catch (DocumentException e)
           {
            e.printStackTrace();
            }


        show("PDF Sheet Created");
        if (MA.PD.Subject.toUpperCase().contains("CHEMISTRY"))
        {

            try {
                MA.PGEN.CreateCurrentChart(PDFChartNameWithPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            show("PDF Chem Chart Created");

        }

        if (    MA.PD.Subject.toUpperCase().contains("ENGLISH") ||
                MA.PD.Subject.toUpperCase().contains("MARATHI") ||
                MA.PD.Subject.toUpperCase().contains("HINDI") ||
                MA.PD.Subject.toUpperCase().contains("TAMIL")
           )
        {

            try {
                MA.PGEN.CreateCurrentChart(PDFChartNameWithPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            show("PDF Oral Chart Created");

        }
if(MA.endnow) MA.finish();


    }



    void LoadBatch(String fylenamewithpath)
    {  MA.checkmarkCount=0;
        try
        {

            File myFile = new File(fylenamewithpath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";


            aDataRow=myReader.readLine(); /// blank line separator

            String temp[],stemp;


            stemp= myReader.readLine();
            temp=stemp.split("§");
            Zone=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            MonthYear=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            School=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split("§");
            Index=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            CenterNo=temp[1].trim();



            stemp= myReader.readLine();
            temp=stemp.split("§");
            Strim=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            Standard=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            Subject=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            SubjectCode=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            Medium=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            Type=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            BatchNo=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            BatchCreator=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            Date=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split("§");
            BatchTime=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split("§");
            BatchSession=temp[1].trim();

            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// ==== Reserved line ===
            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// Seat Nos§ Tag
            aDataRow=myReader.readLine(); /// blank line separator

            MA.tempRoll.removeAll(MA.tempRoll);
            while ((aDataRow = myReader.readLine()) != null)

            {
                MA.tempRoll.add(aDataRow);
            }

            myReader.close();

            int tot=MA.tempRoll.size();
//            PickCounter=tot;
            MA.froll=MA.tempRoll.get(0);
            MA.lroll=MA.tempRoll.get(tot-1);

            //show(froll);show(lroll);

            MA.LO.FillList(MA.froll,MA.lroll);   ///This removes all arrays and refill them


            int totroll=MA.Roll.size();
            int i,j;

            for(i=0;i<totroll;i++)
            { //Planet planet = listAdapter.getItem(i);
                ListViewItemDTO dto = MA.initItemList.get(i);
                for(j=0;j<tot;j++)
                    if(MA.Roll.get(i).contains(MA.tempRoll.get(j)))
                    {
                        dto.setChecked(true);
                        MA.checkmarkCount++;break;

                    }
            }
            //AlreadyPicked=false;
            MA.listViewDataAdapter.notifyDataSetChanged();
            show("Loaded From SD Card");
           // modified=false;
        }
        catch (Exception e)
        {
            Toast.makeText(MA,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


     void ShowHeaderDlg()
    {
        final Dialog myDialog;
        myDialog =  new Dialog(MA);

        myDialog.setTitle("Batch Details");
        myDialog.setContentView(R.layout.headerdlg);
        myDialog.setCancelable(true);
        myDialog.getWindow().getAttributes().width = LayoutParams.FILL_PARENT;


        final EditText FBatchNo = (EditText) myDialog.findViewById(R.id.EB_BNO);
        FBatchNo.setSingleLine();
        FBatchNo.setInputType(InputType.TYPE_CLASS_PHONE);
        //FBatchNo.setHint("Batch No 01,02,03 etc...");

        FBatchNo.setText(BatchNo);

        final EditText FTime = (EditText) myDialog.findViewById(R.id.EB_TIME);
        FTime.setText(BatchTime);

        final EditText FSession = (EditText) myDialog.findViewById(R.id.EB_SESSION);
        FSession.setText(BatchSession);


        final EditText FDate = (EditText) myDialog.findViewById(R.id.EB_DET);
        FDate.setText(Date);

        Button buttoncancel = (Button) myDialog.findViewById(R.id.BCancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {myDialog.dismiss();
                InputMethodManager imm = (InputMethodManager) MA.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });
        Button buttonok = (Button) myDialog.findViewById(R.id.BCreate);
        buttonok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String tempstr="";
                String ins1=FBatchNo.getText().toString();

                if(ins1.length()!=0 )
                {
                    int batchnumber = new Integer(ins1);
                    tempstr=String.format("%02d", batchnumber);
                    BatchNo=tempstr;
                }
                else  BatchNo="01";
                tempstr=FDate.getText().toString();  Date=tempstr;
                tempstr=FTime.getText().toString();  BatchTime=tempstr;
                tempstr=FSession.getText().toString();  BatchSession=tempstr;

                MA.modified=true;


                InputMethodManager imm = (InputMethodManager) MA.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(FBatchNo.getWindowToken(),0);
                myDialog.dismiss();
            }
        });

        myDialog.show();
        InputMethodManager imm = (InputMethodManager) MA.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }

     void OpenFileDialog()
    {	String tempstr;
        //OpenNow=false;
        String rootDir=Environment.getExternalStorageDirectory().getPath();

        List<String> listItems = new ArrayList<String>();
        final  ArrayList<String> filepath = new ArrayList<String>();

        File mfile=new File(rootDir);
        File[] list=mfile.listFiles();
        String tempupper;
        for(int i=0;i<mfile.listFiles().length;i++)
        {
            tempstr=list[i].getAbsolutePath();
            tempupper=tempstr.toUpperCase();
            if(tempupper.endsWith(".BCH") ) {

                filepath.add(list[i].getAbsolutePath());
                listItems.add(list[i].getName());

               // listItems.add(list[i].getAbsolutePath());
            }
        }

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(MA);
        builder.setTitle("Select File To Open...");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int item)
            {String ttt= (String) filepath.get(item);
                LoadBatch(ttt);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


void SaveDirect()
{
    if(MA.checkmarkCount==0) { MA.show("No Selection"); return;}

    int len=0;

    String ShortSubject="";
    if(MA.PD.Subject.length()>3) ShortSubject=MA.PD.Subject.toUpperCase().substring(0,3);
    else ShortSubject=MA.PD.Subject.toUpperCase();

    String ShortBatchCreator="";
    if(BatchCreator.length()>2) ShortBatchCreator=MA.PD.BatchCreator.toUpperCase().substring(0,2);
    else ShortBatchCreator=MA.PD.BatchCreator.toUpperCase();

    String FirstSeat=GetFirstSeatNo();
    String FirstSeat4="";
    len=FirstSeat.length();
    if(len>4) FirstSeat4=FirstSeat.substring(len-4,len);
    else FirstSeat4=FirstSeat;

    String LastSeat=GetLastSeatNo();
    String LastSeat4="";
    len=LastSeat.length();
    if(len>4) LastSeat4=LastSeat.substring(len-4,len);
    else LastSeat4=LastSeat;


    FileNameWithPath=Environment.getExternalStorageDirectory().getPath();
    PDFNameWithPath=FileNameWithPath;
    PDFChartNameWithPath=FileNameWithPath;
    String fylenem=ShortSubject+"-"+BatchNo+"-"+ShortBatchCreator+"-"+FirstSeat4+"--"+LastSeat4;

    FileNameWithPath+="/"+fylenem+".bch";
    PDFNameWithPath+="/"+fylenem+".pdf";
    PDFChartNameWithPath+="/"+fylenem+"-Chart.pdf";
    SaveList();
}

String GetFirstSeatNo()
{ String temp="";

    int size = MA.initItemList.size();
    for(int i=0;i<size;i++)
    {
        ListViewItemDTO dto = MA.initItemList.get(i);

        if(dto.isChecked()) return dto.getItemText();
    }
///else return empty string which will be an exception only

return temp;
}

String GetLastSeatNo()
{String temp="";

    int size = MA.initItemList.size();
    for(int i=size-1;i>=0;i--)
    {
        ListViewItemDTO dto = MA.initItemList.get(i);

        if(dto.isChecked()) return dto.getItemText();
    }
///else return empty string which will be an exception only

    return temp;

}



     void SendList()
    {

        ArrayList<Uri> uris = new ArrayList<Uri>();
        String[] filePaths = new String[] {FileNameWithPath,PDFNameWithPath};

        for (String file : filePaths)
        {
            File fileIn = new File(file);
            if(!fileIn.exists()) { Msg.Show("Missing Batch File",MA); return;}
         // Uri u = Uri.fromFile(fileIn);  Before Orio bug fix
            Uri u = FileProvider.getUriForFile(MA,
                    BuildConfig.APPLICATION_ID + ".provider", fileIn);
            uris.add(u);
        }

        String emailsubject=Subject.trim()+"-"+BatchNo.trim()+"-"+BatchCreator.trim();
        Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,emailsubject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Attached Batch ...");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uris);

        String E1=MA.PD.Email1.trim();
        String E2=MA.PD.Email2.trim();
        if(E1.length()==0 && E2.length()==0) {show("Specify Email(s) in Preferrences "); return; }
        String[] emailList={"",""};
        if(E1.length()!=0) emailList[0]=MA.PD.Email1;
        if(E2.length()!=0) emailList[1]=MA.PD.Email2;
        sendIntent.putExtra(Intent.EXTRA_EMAIL,emailList);
        sendIntent.setType("text/plain");
        MA.startActivity(Intent.createChooser(sendIntent, "Send Mail"));

    }



    void SendCombinedList()
    {

        ArrayList<Uri> uris = new ArrayList<Uri>();

        String rootDir=Environment.getExternalStorageDirectory().getPath();
        String combinedpath=rootDir+"/Combined.pdf";
        String[] filePaths = new String[] {combinedpath};

        for (String file : filePaths)
        {
            File fileIn = new File(file);
         //   Uri u = Uri.fromFile(fileIn); // old  before Orio 8.1.0 bug fix
            if(!fileIn.exists()) { Msg.Show("Missing Combined.pdf",MA); return;}
            Uri u = FileProvider.getUriForFile(MA,
                    BuildConfig.APPLICATION_ID + ".provider", fileIn);
            uris.add(u);
        }

        String emailsubject=Subject.trim()+"-"+BatchNo.trim()+"-"+BatchCreator.trim();
        Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,emailsubject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Attached Batch ...");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uris);

        String E1=MA.PD.Email1.trim();
        String E2=MA.PD.Email2.trim();
        if(E1.length()==0 && E2.length()==0) {show("Specify Email(s) in Preferrences "); return; }
        String[] emailList={"",""};
        if(E1.length()!=0) emailList[0]=MA.PD.Email1;
        if(E2.length()!=0) emailList[1]=MA.PD.Email2;
        sendIntent.putExtra(Intent.EXTRA_EMAIL,emailList);
        sendIntent.setType("text/plain");
        MA.startActivity(Intent.createChooser(sendIntent, "Send Mail"));
    }

}
