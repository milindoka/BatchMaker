package ilugbom.org.in.batchmaker;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by milind on 10/2/19.
 */

public class CreatePDF
{

    void SingleBatchPdf(String pdfFileNameWithPath) throws FileNotFoundException,DocumentException {


      //  String rootDir= Environment.getExternalStorageDirectory().getPath();
      //  String fnem=rootDir+"/"+pdffilename+".pdf";
        File myFile = new File(pdfFileNameWithPath);

        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph("Hello World!"));

        document.close();
    }

}
