package ilugbom.org.in.batchmaker;

import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
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
}
