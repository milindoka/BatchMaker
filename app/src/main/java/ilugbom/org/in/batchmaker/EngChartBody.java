package ilugbom.org.in.batchmaker;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

public final class EngChartBody {
    static Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);


    static void Add(Document document, String BatchSession, ArrayList<String> seatNos) throws DocumentException {
        float col[] = {25,52,52,50,50,50,50,50,60};
        //float col[]={4,8,8,30};

        //////////   TITLE ROW

        PdfPTable table2 = new PdfPTable(col);
        table2.setWidthPercentage(95);
        PdfPCell cell = new PdfPCell(new Phrase("Sr No", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPaddingBottom(5f);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Seat No", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Roll", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);


        cell = new PdfPCell(new Phrase("LM", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("RM", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("SM", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("CM", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Total", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

        cell = new PdfPCell(new Phrase("Word", normal));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table2.addCell(cell);

//////////table rows

        int size = seatNos.size();

        String srno;
        String seatno;

        for (int i = 0; i < 32; i++) { // boolean temp=MA.initItemList.get(i).isChecked();
            //  if(!temp) continue;
            srno = String.format("%d", i + 1);
            if (i < size) {
                seatno = seatNos.get(i);

            } else {
                seatno = " ";
                srno = " ";
            }

            cell = new PdfPCell(new Phrase(srno, normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPaddingBottom(5f);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(seatno, normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            //cell.setBorder(PdfPCell.NO_BORDER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);

            cell = new PdfPCell(new Phrase(" ", normal));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table2.addCell(cell);
        }


        document.add(table2);

    }
}