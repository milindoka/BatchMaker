package ilugbom.org.in.batchmaker;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class CreateCombinedPDF
{

    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}

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
        String rootDir= Environment.getExternalStorageDirectory().getPath();
        int totalfiles=listfiles(rootDir);
        MA.show(totalfiles);



    }


    void LoadSingleFile(String file)
    {





    }



}
