package ilugbom.org.in.batchmaker;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.util.ArrayList;

public class PDFGeneration
{
  //  Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
    //        Font.NORMAL);

    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}

    String rootDir;
    public ArrayList<String> fileArray = new ArrayList<String>();


    int listfiles(String path)
    {
        FilenameFilter mrkFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".bch")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        fileArray.removeAll(fileArray);
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(mrkFilter);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile())
            {  fileArray.add(listOfFiles[i].getAbsolutePath());
            }
        }
        int Total=fileArray.size();
        return Total;
    }


    void CreateCombined()
    {
        rootDir= Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath=rootDir+"/"+"Combined.pdf";
        int totalfiles=listfiles(rootDir);
        MA.show(totalfiles);

        try {

            File myFile = new File(pdfFileNameWithPath);
            OutputStream output = new FileOutputStream(myFile);
            Document document = new Document();
            document = new Document(PageSize.A4);
            document.setMargins(50, 30, 15, 2);
            PdfWriter.getInstance(document, output);

            document.open();


            for(int i=0;i<totalfiles;i++)
            {
               // LoadBatch(fileArray.get(i));
               // MA.show(fileArray.get(i));
                MA.show(DiskInOut.Type);
                DiskInOut.LoadBatch(fileArray.get(i));
                BoxedHeader.AddBoxedText(document,DiskInOut.Index);

                if(DiskInOut.Type.toUpperCase().contains("PRAC")) {
                    PracticalHeader.Add(document,
                            DiskInOut.Zone,
                            DiskInOut.MonthYear,
                            DiskInOut.BatchNo,
                            DiskInOut.Date,
                            DiskInOut.BatchTime,
                            DiskInOut.School,
                            DiskInOut.Index,
                            DiskInOut.Strim,
                            DiskInOut.Standard,
                            DiskInOut.Subject,
                            DiskInOut.SubjectCode,
                            DiskInOut.Medium,
                            DiskInOut.Type,
                            DiskInOut.BatchCreator,
                            DiskInOut.BatchSession,
                            DiskInOut.froll,
                            DiskInOut.lroll);
                    //
                    PracticalBody.Add(document, DiskInOut.BatchSession, DiskInOut.tempRoll);
                    //

                    PracticalFooter.Add(document);

                }

                if(DiskInOut.Type.toUpperCase().contains("ORAL"))
                {


                    OralHeader.Add(document,
                            DiskInOut.Zone,
                            DiskInOut.MonthYear,
                            DiskInOut.BatchNo,
                            DiskInOut.Date,
                            DiskInOut.BatchTime,
                            DiskInOut.School,
                            DiskInOut.Index,
                            DiskInOut.Strim,
                            DiskInOut.Standard,
                            DiskInOut.Subject,
                            DiskInOut.SubjectCode,
                            DiskInOut.Medium,
                            DiskInOut.Type,
                            DiskInOut.BatchCreator,
                            DiskInOut.BatchSession,
                            DiskInOut.froll,
                            DiskInOut.lroll);

                    OralBody.AddORALBody(document,DiskInOut.tempRoll);
                    OralFooter.AddORALFooter(document);

                }
                document.newPage();

            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    void CreateSingleOralPDF()
    {

    }

}
