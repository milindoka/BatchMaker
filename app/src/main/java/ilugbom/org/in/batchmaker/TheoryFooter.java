package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TheoryFooter
{
    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.NORMAL);

    static void Add(Document document) throws DocumentException
    {  float footcolwid[]= {8,6};
        PdfPTable table = new PdfPTable(footcolwid);
        table.setWidthPercentage(95);
        table.setSpacingBefore(40f);

        PdfPCell cell = new PdfPCell(new Phrase("Supervisor's Name",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("Deputy/Conductor's Signature",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        table.addCell(cell);


        cell = new PdfPCell(new Phrase("& Signature",normal));
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


        cell = new PdfPCell(new Phrase("Note : To be kept by center Conductor " +
                "for one year after the declaration of the result.",normal));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        document.add(table);
        document.add(table2);
    }

}
