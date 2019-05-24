package ilugbom.org.in.batchmaker;

import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class CreateCombinedPDF
{
    Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

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


        // CreateOnePDF();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileNameWithPath));
            document.open();

            LoadBatch(fileArray.get(0));
         //   addMetaData(document);
         //   addTitlePage(document);
         //   addContent(document);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/////////////// Load Single Batch

    //boolean NewNow=false,selectall=false,end=false,OpenNow=false;
    String  BatchNo="01",Date="",BatchTime="",School="SIWS College",Index="J-31.04.005",
            Strim="Science", Standard="HSC",Subject="Mathematics",SubjectCode="40",Type="Practical",
            Email1="",Email2="",BatchCreator="MO",BatchSession="";

    String froll,lroll;
    ArrayList<String> tempRoll = new ArrayList<>();

    void LoadBatch(String fylenamewithpath)
    {
        try
        {

            File myFile = new File(fylenamewithpath);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";


            aDataRow=myReader.readLine(); /// blank line separator

            String temp[],stemp;


            stemp= myReader.readLine();
            temp=stemp.split(":");
            MA.PD.Zone=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            School=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Index=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Strim=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Standard=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Subject=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            SubjectCode=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Type=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchNo=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchCreator=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email1=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            Email2=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            Date=temp[1].trim();


            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchTime=temp[1].trim();

            stemp= myReader.readLine();
            temp=stemp.split(":");
            BatchSession=temp[1].trim();

            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// ==== Reserved line ===
            aDataRow=myReader.readLine(); /// blank line separator
            aDataRow=myReader.readLine(); /// Seat Nos: Tag
            aDataRow=myReader.readLine(); /// blank line separator

            tempRoll.removeAll(tempRoll);
            while ((aDataRow = myReader.readLine()) != null)

            {
                tempRoll.add(aDataRow);
            }

            myReader.close();

            int tot=tempRoll.size();
            froll=tempRoll.get(0);
            lroll=tempRoll.get(tot-1);

            MA.show(froll);MA.show(lroll);

        //    CreateOnePDF();
            //show("Loaded From SD Card");

        }
        catch (Exception e)
        {
            Toast.makeText(MA,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    void CreateOnePDFpage(String pdfFileNameWithPath) throws FileNotFoundException,DocumentException
    {   String =rootDir+"/"+"Combined.pdf";
        File myFile = new File(pdfFileNameWithPath);
        OutputStream output = new FileOutputStream(myFile);
        Document document = new Document();
        document = new Document(PageSize.A4);
        document.setMargins(50, 30, 15, 2);
        PdfWriter.getInstance(document, output);
        document.open();
      //  size=MA.CP.CheckedNumbers.size();
        AddBoxedText(document);
     //   AddHeader(document);
     //   AddBody(document);
     //   AddFooter(document);
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

        String indexno=Index;
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