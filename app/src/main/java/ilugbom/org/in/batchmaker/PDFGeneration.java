package ilugbom.org.in.batchmaker;

import android.os.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.util.ArrayList;

public class PDFGeneration {

    ArrayList<String> CheckedNumbers=new ArrayList<String>();//creating new generic arraylist
    private MainActivity MA;

    void SetMA(MainActivity MA) {
        this.MA = MA;
    }

    String rootDir;
    public ArrayList<String> fileArray = new ArrayList<String>();


    int listfiles(String path) {
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
            if (listOfFiles[i].isFile()) {
                fileArray.add(listOfFiles[i].getAbsolutePath());
            }
        }
        int Total = fileArray.size();
        return Total;
    }


    void CreateCombined()
    {
        rootDir = Environment.getExternalStorageDirectory().getPath();
        String pdfFileNameWithPath = rootDir + "/" + "Combined.pdf";
        int totalfiles = listfiles(rootDir);
        MA.show(totalfiles);

        try {

            File myFile = new File(pdfFileNameWithPath);
            OutputStream output = new FileOutputStream(myFile);
            Document document = new Document();
            document = new Document(PageSize.A4);
            document.setMargins(50, 30, 15, 2);
            PdfWriter.getInstance(document, output);

            document.open();


            for (int i = 0; i < totalfiles; i++) {
                // LoadBatch(fileArray.get(i));
                // MA.show(fileArray.get(i));
                MA.show(DiskInOut.Type);
                DiskInOut.LoadBatch(fileArray.get(i));

                CreateSinglePDF(document,
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
                        DiskInOut.lroll, DiskInOut.tempRoll);

                if (DiskInOut.Subject.toUpperCase().contains("CHEMISTRY"))
                {
                    CreateSingleChemChartPDF(document,
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
                            DiskInOut.lroll, DiskInOut.tempRoll);
                }
                document.newPage();
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void CreateSinglePDF
            (Document document, String Zone, String MonthYear, String BatchNo,
             String Date, String BatchTime, String School, String Index,
             String Strim, String Standard, String Subject, String SubjectCode,
             String Medium, String Type, String BatchCreator,
             String BatchSession, String froll,
             String lroll, ArrayList<String> seatNos) throws DocumentException {

        BoxedHeader.AddBoxedText(document, Index);

        if (Type.toUpperCase().contains("ORAL")) {
            OralHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                    School, Index, Strim, Standard, Subject,
                    SubjectCode, Medium, Type, BatchCreator, BatchSession,
                    froll, lroll);
            OralBody.Add(document, seatNos);
            OralFooter.Add(document);
        }
      else
        {

            PracticalHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                    School, Index, Strim, Standard, Subject,
                    SubjectCode, Medium, Type, BatchCreator, BatchSession,
                    froll, lroll);
            PracticalBody.Add(document, BatchSession, seatNos);
            PracticalFooter.Add(document);
        }




    }

    void CreateSingleChemChartPDF  (Document document, String Zone, String MonthYear, String BatchNo,
    String Date, String BatchTime, String School, String Index,
    String Strim, String Standard, String Subject, String SubjectCode,
    String Medium, String Type, String BatchCreator,
    String BatchSession, String froll,
    String lroll, ArrayList<String> seatNos) throws DocumentException
    {
        PracticalHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                School, Index, Strim, Standard, Subject,
                SubjectCode, Medium, Type, BatchCreator, BatchSession,
                froll, lroll);
        ChemChartBody.Add(document, BatchSession, seatNos);
        PracticalFooter.Add(document);
    }


    void CreateCurrentPDF(String pdfFileNameWithPath) throws FileNotFoundException, DocumentException
    {
        int tot=CheckedNumbers.size();
        MA.froll=CheckedNumbers.get(0);
        MA.lroll=CheckedNumbers.get(tot-1);
        File myFile = new File(pdfFileNameWithPath);
        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);
        document.setMargins(50, 30, 15, 2);
        PdfWriter.getInstance(document, output);
        document.open();

        CreateSinglePDF(document,MA.PD.Zone,MA.PD.MonthYear,MA.FSL.BatchNo,
                MA.PD.Date,MA.PD.BatchTime,MA.PD.School,MA.PD.Index,MA.PD.Strim,
                MA.PD.Standard,MA.PD.Subject,MA.PD.SubjectCode,MA.PD.Medium,
                MA.PD.Type,MA.PD.BatchCreator,MA.PD.BatchSession,MA.froll,
                MA.lroll,CheckedNumbers);
        document.close();
    }

    void CreateCurrentChart(String pdfFileNameWithPath) throws FileNotFoundException, DocumentException
    {
        int tot=CheckedNumbers.size();
        MA.froll=CheckedNumbers.get(0);
        MA.lroll=CheckedNumbers.get(tot-1);
        File myFile = new File(pdfFileNameWithPath);
        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);
        document.setMargins(50, 30, 15, 2);
        PdfWriter.getInstance(document, output);
        document.open();

        CreateSingleChemChartPDF(document,MA.PD.Zone,MA.PD.MonthYear,MA.FSL.BatchNo,
                MA.PD.Date,MA.PD.BatchTime,MA.PD.School,MA.PD.Index,MA.PD.Strim,
                MA.PD.Standard,MA.PD.Subject,MA.PD.SubjectCode,MA.PD.Medium,
                MA.PD.Type,MA.PD.BatchCreator,MA.PD.BatchSession,MA.froll,
                MA.lroll,CheckedNumbers);
        document.close();
    }


}
