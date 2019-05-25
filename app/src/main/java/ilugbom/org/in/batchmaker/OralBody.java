package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

public class OralBody
{
    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);


    static  void Add(Document document, ArrayList<String> seatNos) throws DocumentException
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
        int size = seatNos.size();


        String srno;
        String seatno;
        String session;
        for(int i=0;i<32;i++)

        { // boolean temp=MA.initItemList.get(i).isChecked();
            //  if(!temp) continue;
            srno=String.format("%d",i+1);
            if(i<size)
            { seatno=seatNos.get(i);
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


}
