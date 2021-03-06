package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class OralHeader
{

    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);



    static  void Add(Document document,
                               String Zone,
                               String MonthYear,
                               String BatchNo,
                               String Date,
                               String BatchTime,
                               String School,
                               String Index,
                               String Strim,
                               String Standard,
                               String Subject,
                               String SubjectCode,
                               String Medium,
                               String Type,
                               String BatchCreator,
                               String BatchSession,
                               String froll,
                               String lroll

    ) throws DocumentException
    {
        PdfPTable table = new PdfPTable(1);

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

        cell = new PdfPCell(new Phrase("Medium : "+Medium,normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


//////////table row

        cell = new PdfPCell(new Phrase("Seat No's From : "+froll+" - "+lroll,
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

}
