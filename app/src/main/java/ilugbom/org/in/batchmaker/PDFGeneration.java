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
        MA.show(String.format("Total Batches %d",totalfiles));

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

                DiskInOut.LoadBatch(fileArray.get(i));


                CreateSinglePDF(document,
                        DiskInOut.Zone,
                        DiskInOut.MonthYear,
                        DiskInOut.BatchNo,
                        DiskInOut.Date,
                        DiskInOut.BatchTime,
                        DiskInOut.School,
                        DiskInOut.Index,
                        DiskInOut.CenterNo,
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

                if (DiskInOut.Subject.toUpperCase().contains("CHEMISTRY")
                        && DiskInOut.Type.toUpperCase().contains("PRACTICAL"))
                {   document.newPage();
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

                if (

                DiskInOut.Subject.toUpperCase().contains("ENGLISH") ||
                DiskInOut.Subject.toUpperCase().contains("MARATHI") ||
                DiskInOut.Subject.toUpperCase().contains("HINDI") ||
                DiskInOut.Subject.toUpperCase().contains("TAMIL")

                )
                {   document.newPage();
                    CreateSingleEngChartPDF(document,
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
                String progress=String.format("Added %d",i+1);
                MA.show(progress);
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void CreateSinglePDF
            (Document document, String Zone, String MonthYear, String BatchNo,
             String Date, String BatchTime, String School, String Index, String CenterNo,
             String Strim, String Standard, String Subject, String SubjectCode,
             String Medium, String Type, String BatchCreator,
             String BatchSession, String froll,
             String lroll, ArrayList<String> seatNos) throws DocumentException {



        if (Type.toUpperCase().contains("ORAL") || Type.toUpperCase().contains("PROJECT")) {
            BoxedHeader.AddBoxedText(document, Index);
            OralHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                    School, Index,Strim, Standard, Subject,
                    SubjectCode, Medium, Type, BatchCreator, BatchSession,
                    froll, lroll);
            OralBody.Add(document, seatNos);
            OralFooter.Add(document);
        }

        if (Type.toUpperCase().contains("PRACTICAL"))
        {
            BoxedHeader.AddBoxedText(document, Index);
            PracticalHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                    School, Index, Strim, Standard, Subject,
                    SubjectCode, Medium, Type, BatchCreator, BatchSession,
                    froll, lroll);
            PracticalBody.Add(document, BatchSession, seatNos);
            PracticalFooter.Add(document);
        }

        if (Type.toUpperCase().contains("THEORY")) {
            TheoryHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                    School, Index,CenterNo, Strim, Standard, Subject,
                    SubjectCode, Medium, Type, BatchCreator, BatchSession,
                    froll, lroll);
            TheoryBody.Add(document, seatNos);
            TheoryFooter.Add(document);
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

    void CreateSingleEngChartPDF (Document document, String Zone, String MonthYear,
                                  String BatchNo, String Date, String BatchTime,
                                   String School, String Index, String Strim,
                                   String Standard, String Subject, String SubjectCode,
                                    String Medium, String Type, String BatchCreator,
                                    String BatchSession, String froll,
                                    String lroll, ArrayList<String> seatNos) throws DocumentException
    {
        OralHeader.Add(document, Zone, MonthYear, BatchNo, Date, BatchTime,
                School, Index, Strim, Standard, Subject,
                SubjectCode, Medium, Type, BatchCreator, BatchSession,
                froll, lroll);
        EngChartBody.Add(document, BatchSession, seatNos);
        OralFooter.Add(document);
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
                MA.FSL.Date,MA.FSL.BatchTime,MA.PD.School,MA.PD.Index,MA.PD.CenterNo,MA.PD.Strim,
                MA.PD.Standard,MA.PD.Subject,MA.PD.SubjectCode,MA.PD.Medium,
                MA.PD.Type,MA.PD.BatchCreator,MA.FSL.BatchSession,MA.froll,
                MA.lroll,CheckedNumbers);
        document.close();
       // MA.show(MA.FSL.Date);
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

        if (MA.PD.Subject.toUpperCase().contains("CHEMISTRY"))

        CreateSingleChemChartPDF(document,MA.PD.Zone,MA.PD.MonthYear,MA.FSL.BatchNo,
                MA.FSL.Date,MA.FSL.BatchTime,MA.PD.School,MA.PD.Index,MA.PD.Strim,
                MA.PD.Standard,MA.PD.Subject,MA.PD.SubjectCode,MA.PD.Medium,
                MA.PD.Type,MA.PD.BatchCreator,MA.PD.BatchSession,MA.froll,
                MA.lroll,CheckedNumbers);

        if (
                MA.PD.Subject.toUpperCase().contains("ENGLISH") ||
                MA.PD.Subject.toUpperCase().contains("MARATHI") ||
                MA.PD.Subject.toUpperCase().contains("HINDI") ||
                MA.PD.Subject.toUpperCase().contains("TAMIL")
        )
        CreateSingleEngChartPDF(document,MA.PD.Zone,MA.PD.MonthYear,MA.FSL.BatchNo,
                MA.FSL.Date,MA.FSL.BatchTime,MA.PD.School,MA.PD.Index,MA.PD.Strim,
                MA.PD.Standard,MA.PD.Subject,MA.PD.SubjectCode,MA.PD.Medium,
                MA.PD.Type,MA.PD.BatchCreator,MA.PD.BatchSession,MA.froll,
                MA.lroll,CheckedNumbers);

        document.close();

    }


}
