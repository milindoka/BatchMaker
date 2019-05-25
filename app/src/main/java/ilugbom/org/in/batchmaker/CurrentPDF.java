package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CurrentPDF
{
    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}
    ArrayList<String> CheckedNumbers=new ArrayList<String>();//creating new generic arraylist

    Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);


    void Save(String pdfFileNameWithPath) throws FileNotFoundException, DocumentException
    {

        int tot=CheckedNumbers.size();
        MA.froll=CheckedNumbers.get(0);
        MA.lroll=CheckedNumbers.get(tot-1);


        //  String rootDir= Environment.getExternalStorageDirectory().getPath();
        //  String fnem=rootDir+"/"+pdffilename+".pdf";
        File myFile = new File(pdfFileNameWithPath);

        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);
        document.setMargins(50, 30, 15, 2);
        PdfWriter.getInstance(document, output);
        document.open();

        BoxedHeader.AddBoxedText(document,MA.PD.Index);
        if(MA.PD.Type.toUpperCase().contains("PRAC"))
        {

            PracticalHeader.Add(document,
                    MA.PD.Zone,
                    MA.PD.MonthYear,
                    MA.PD.BatchNo,
                    MA.PD.Date,
                    MA.PD.BatchTime,
                    MA.PD.School,
                    MA.PD.Index,
                    MA.PD.Strim,
                    MA.PD.Standard,
                    MA.PD.Subject,
                    MA.PD.SubjectCode,
                    MA.PD.Medium,
                    MA.PD.Type,
                    MA.PD.BatchCreator,
                    MA.PD.BatchSession,
                    MA.froll,
                    MA.lroll);
            PracticalBody.Add(document, MA.PD.BatchSession, CheckedNumbers);
            PracticalFooter.Add(document);
        }

        if(MA.PD.Type.toUpperCase().contains("ORAL"))
        {

            OralHeader.Add(document,
                    MA.PD.Zone,
                    MA.PD.MonthYear,
                    MA.PD.BatchNo,
                    MA.PD.Date,
                    MA.PD.BatchTime,
                    MA.PD.School,
                    MA.PD.Index,
                    MA.PD.Strim,
                    MA.PD.Standard,
                    MA.PD.Subject,
                    MA.PD.SubjectCode,
                    MA.PD.Medium,
                    MA.PD.Type,
                    MA.PD.BatchCreator,
                    MA.PD.BatchSession,
                    MA.froll,
                    MA.lroll);
            OralBody.Add(document, CheckedNumbers);
            OralFooter.Add(document);
        }


        //  size=CheckedNumbers.size();
      //  AddBoxedText(document);
      //  AddHeader(document);
      //  AddBody(document);
      //  AddFooter(document);
        document.close();



    }
}
