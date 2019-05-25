package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PracticalFooter
{

    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

    static void Add(Document document) throws DocumentException
    {  float footcolwid[]= {10,10,20};
        PdfPTable table = new PdfPTable(footcolwid);
        table.setWidthPercentage(95);
        table.setSpacingBefore(40f);

        PdfPCell cell = new PdfPCell(new Phrase("Internal Examiner",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("External Examiner",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Signature of Head of Jr College",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);

        ////////////////////////// TABLE ROW

        cell = new PdfPCell(new Phrase("Name & Signature",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Name & Signature",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("With Rubber Stamp",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        table.addCell(cell);

        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(95);

        cell = new PdfPCell(new Phrase("Note :",normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table2.addCell(cell);


        cell = new PdfPCell(new Phrase("1. Submit to Board with Parctical Marksheet. 2. Mark ABSENT with Red Ink. 3. Write Extra No.s if any.",normal));cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        document.add(table);
        document.add(table2);

    }

}
