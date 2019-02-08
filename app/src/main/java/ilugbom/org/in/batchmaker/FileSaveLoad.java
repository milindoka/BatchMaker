package ilugbom.org.in.batchmaker;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

    boolean modified=false,NewNow=false,selectall=false,end=false,OpenNow=false;
    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";

   String FileNameWithPath="/sdcard/ztest.bch";


    void SaveList()
    {

    //    if(!AlreadyPicked) { PickRoutine();  }

        int i;
    //    modified=false;
        String tmpStr;
        String txtData = "\n";

        txtData+="School        : ";txtData+=School;       txtData+='\n';
        txtData+="Index         : ";txtData+=Index;        txtData+='\n';
        txtData+="Stream        : ";txtData+=Strim;        txtData+='\n';
        txtData+="Standard      : ";txtData+=Standard;     txtData+='\n';
        txtData+="Subject       : ";txtData+=Subject;      txtData+='\n';
        txtData+="Subject Code  : ";txtData+=SubjectCode;  txtData+='\n';
        txtData+="Type          : ";txtData+=Type;         txtData+='\n';
        txtData+="Batch Number  : ";txtData+=BatchNo;      txtData+='\n';
        txtData+="Batch Creater : ";txtData+=BatchCreator; txtData+='\n';
        txtData+="Email1        : ";txtData+=Email1;       txtData+='\n';
        txtData+="Email2        : ";txtData+=Email2;       txtData+='\n';
        txtData+="Date          : ";txtData+=Date;         txtData+="\n";
        txtData+="Time          : ";txtData+=BatchTime;    txtData+='\n';
        txtData+="Session       : ";txtData+=BatchSession; txtData+="\n";
        txtData+="\n";
        txtData+="=== Reserved Line ====\n";
        txtData+="\n";
        txtData+="Seat Nos :\n";
        txtData+="\n";


        for(i=0;i<MA.initItemList.size();i++)
        { boolean temp=MA.initItemList.get(i).isChecked();
            if(temp)
            { //show(Roll.get(i));
                txtData+=MA.initItemList.get(i).getItemText();
                txtData+='\n';
            }
        }


        //String fnem=Subject.toUpperCase().substring(0,3)+"-"+BatchNo+"-"+BatchCreator+".bch";

        ///  FileNameWithPath is already filled from SaveBatchList()

        try {
            File myFile = new File(FileNameWithPath);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtData);
            myOutWriter.close();
            fOut.close();
            modified=false;
            show("Saved on SD card");

      //      if(end) finish();
      //      end=false;OpenNow=false;
      //      if(NewNow) GetNewRoll();

        } catch (Exception e) {
            Toast.makeText(MA, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }



    void LoadBatch(String fylenamewithpath)
    {
        try
        {

            File myFile = new File(fylenamewithpath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";


            aDataRow=myReader.readLine(); /// blank line separator

            String temp[],stemp;

            stemp= myReader.readLine();
            temp=stemp.split(":");
            School=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Index=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Strim=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Standard=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Subject=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            SubjectCode=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Type=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchNo=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchCreator=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email1=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email2=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Date=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchTime=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchSession=temp[1].trim();

            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// ==== Reserved line ===
            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// Seat Nos: Tag
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
                        dto.setChecked(true); break;

                    }
            }
            //AlreadyPicked=false;
            MA.listViewDataAdapter.notifyDataSetChanged();

       //     FileNameWithPath=fylenamewithpath;
       //     int start=fylenamewithpath.lastIndexOf("/");

           // FileName=fylenamewithpath.substring(start+1);

            //	 PickRoutine();


           // mainListView.setSelection(0);
           // UpdateTitle(); ///To reset filename and counter display
           // ((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();
            show("Loaded From SD Card");
           // modified=false;
        }
        catch (Exception e)
        {
            Toast.makeText(MA,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }










}
