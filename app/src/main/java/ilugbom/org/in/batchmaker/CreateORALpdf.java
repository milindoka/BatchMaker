package ilugbom.org.in.batchmaker;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by milind on 21/05/19.
 */

public class CreateORALpdf
{
    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}

    Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);
   // ArrayList<String> CheckedNumbers=new ArrayList<String>();//creating new generic arraylist
    String Zone="Mumbai Divisional Board, Vashi,Navi Mumbai - 400703",MonthYear="Feb-2021";
    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",
            Medium="English",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";

    int size;
    public  void SetHeaderFileds(String zone,String monthyear,String school,String index,String strim,
                                 String standard,String subject,String subjectcode,String medium,
                                 String type,String batchno,String batchcreator,String email1,
                                 String email2,String date,String batchtime,String batchsession)
    {   Zone=zone;
        MonthYear=monthyear;
        BatchNo=batchno;
        Date=date;
        BatchTime=batchtime;
        School=school;
        Index=index;
        Strim=strim;
        Standard=standard;
        Subject=subject;
        SubjectCode=subjectcode;
        Medium=medium;
        Type=type;
        Email1=email1;
        Email2=email2;
        BatchCreator=batchcreator;
        BatchSession=batchsession;
    }

    void SingleBatchPdf(String pdfFileNameWithPath) throws FileNotFoundException,DocumentException
    {


        //  String rootDir= Environment.getExternalStorageDirectory().getPath();
        //  String fnem=rootDir+"/"+pdffilename+".pdf";
        File myFile = new File(pdfFileNameWithPath);

        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);
        document.setMargins(50, 30, 15, 2);
        PdfWriter.getInstance(document, output);
        document.open();
        size=MA.CP.CheckedNumbers.size();
        AddBoxedText(document);
        AddHeader(document);
        AddBody(document);
        AddFooter(document);
        document.close();
    }






    void AddBoxedText(Document document) throws DocumentException
    {
        PdfPTable OuterTable5 = new PdfPTable(4);
        OuterTable5.setWidthPercentage(95);
        PdfPCell cell = new PdfPCell(new Phrase(" ",normal)); //create cell object
        cell.setBorder(PdfPCell.NO_BORDER);
        OuterTable5.addCell(cell);
        // OuterTable5.addCell(cell); //first two empty

        cell = new PdfPCell(new Phrase("College Index Number",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
//		   cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

        OuterTable5.addCell(cell);

        String indexno=Index;
        int len=indexno.length();
        PdfPTable table = new PdfPTable(len);
        float totalwidth=12 * len;
        table.setTotalWidth(totalwidth);
        table.setLockedWidth(true);




        for(int i=0;i<len;i++)
        { String temp="";
            temp+=indexno.charAt(i);
            cell = new PdfPCell(new Phrase(temp,normal));
            cell.setPaddingBottom(5f);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);
        }

        PdfPCell cellfortable = new PdfPCell();
        cellfortable.setPadding(0);
        cellfortable.setBorder(PdfPCell.NO_BORDER);
        cellfortable.addElement(table);

        OuterTable5.addCell(cellfortable);

        cell = new PdfPCell(new Phrase(" ",normal)); //fourth object
        cell.setBorder(PdfPCell.NO_BORDER);
        OuterTable5.addCell(cell);

        document.add(OuterTable5);


    }



    void AddHeader(Document document) throws DocumentException
    {PdfPTable table = new PdfPTable(1);



        PdfPCell cell = new PdfPCell(new Phrase("Maharashtra State Board of Secondary & Higher Secondary Education",normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase(Zone,normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("HSC - "+Type+"-"+MonthYear,normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("ORAL EXAM/PROJECT",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("FORM No. 3A",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        //////////   table row
        float col3[]= {4,2,2};
        PdfPTable table2 = new PdfPTable(col3);
        table2.setWidthPercentage(95);
        cell = new PdfPCell(new Phrase("School/College/Center : "+School,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Batch No : "+BatchNo,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        //////////   table row

        cell = new PdfPCell(new Phrase("Subject : "+Subject,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Subject No : "+SubjectCode,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Mediam : "+Medium,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


//////////table row

        cell = new PdfPCell(new Phrase("Seat No's From : "+MA.CP.CheckedNumbers.get(0)+" - "+MA.CP.CheckedNumbers.get(size-1),
                normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Date :"+Date,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Time : "+BatchTime,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

//////////table row

        cell = new PdfPCell(new Phrase("Extra Seat No's: ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase(" ",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        table2.setSpacingAfter(8f);


        document.add(table);
        document.add(table2);

    }




    void AddBody(Document document) throws DocumentException
    {

        float col[]={4,8,25,13};

        //////////   TITLE ROW

        PdfPTable table2 = new PdfPTable(col);
        table2.setWidthPercentage(95);
        PdfPCell cell = new PdfPCell(new Phrase("Sr No",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPaddingBottom(5f);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Seat No",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Name of the Candidate",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


        cell = new PdfPCell(new Phrase("Student's Signature",normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


//////////table rows
        //  int size = MA.initItemList.size();


        String srno;
        String seatno;
        String session;
        for(int i=0;i<32;i++)

        { // boolean temp=MA.initItemList.get(i).isChecked();
            //  if(!temp) continue;
            srno=String.format("%d",i+1);
            if(i<size)
            { seatno=MA.CP.CheckedNumbers.get(i);
                session=" "; ///No session for oral exam
            }
            else
            { seatno=" ";
                session=" ";
                srno=" ";
            }

            cell = new PdfPCell(new Phrase(srno,normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPaddingBottom(5f);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(seatno,normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            //cell.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(session,normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ",normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);
        }



        document.add(table2);

    }


    void AddFooter(Document document) throws DocumentException
    {  float footcolwid[]= {8,6};
        PdfPTable table = new PdfPTable(footcolwid);
        table.setWidthPercentage(95);
        table.setSpacingBefore(40f);

        PdfPCell cell = new PdfPCell(new Phrase("Supervisor's/Teacher's Name",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Conductor/Principal/Head Master Signature",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Signature",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("And Stamp",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);

        table.setSpacingAfter(8f);

        ////////////////////////// TABLE ROW


        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(95);


        cell = new PdfPCell(new Phrase("Note : To be kept by Center/School/College Conductor " +
                "for one year after the declaration of the result.",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        document.add(table);
        document.add(table2);
    }

}


