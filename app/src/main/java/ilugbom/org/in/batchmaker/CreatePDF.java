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

/**
 * Created by milind on 10/2/19.
 */

public class CreatePDF
{
    Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";




  public  void SetHeaderFileds(String school,String index,String strim,
                         String standard,String subject,String subjectcode,
                         String type,String batchno,String batchcreator,String email1,
                         String email2,String date,String batchtime,String batchsession)
    {
        BatchNo=batchno;
        Date=date;
        BatchTime=batchtime;
        School=school;
        Index=index;
        Strim=strim;
        Standard=standard;
        Subject=subject;
        SubjectCode=subjectcode;
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

        AddBoxedText(document);

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



        String indexno="31.04.005";
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


}
