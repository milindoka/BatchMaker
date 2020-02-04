package ilugbom.org.in.batchmaker;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DiskInOut
{
    static String Zone="Mumbai Divisional Board, Vashi,Navi Mumbai - 400703",
            MonthYear="Feb-2021";
    static String  BatchNo="01",Date="",BatchTime="",School="SIWS College",
            Index="J-31.04.005",CenterNo="3207",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",
            Medium="English",Type="Practical",BatchCreator="MO",BatchSession="";

    static String froll,lroll;
    static ArrayList<String> tempRoll = new ArrayList<>();

    static void LoadBatch(String fylenamewithpath)
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

            tempRoll.removeAll(tempRoll);
            while ((aDataRow = myReader.readLine()) != null)

            {
                tempRoll.add(aDataRow);
            }

            myReader.close();

            int tot=tempRoll.size();
            froll=tempRoll.get(0);
            lroll=tempRoll.get(tot-1);

            //   MA.show(froll);MA.show(lroll);

            //    CreateOnePDF();
            //show("Loaded From SD Card");

        }
        catch (Exception e)
        {
        //  Toast.makeText(d.getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }







}
