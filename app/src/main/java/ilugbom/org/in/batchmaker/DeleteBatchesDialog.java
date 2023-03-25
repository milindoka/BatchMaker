package ilugbom.org.in.batchmaker;

import android.content.DialogInterface;
import android.os.Environment;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DeleteBatchesDialog {

    private MainActivity MA;
    void SetMA(MainActivity MA){this.MA=MA;}


    void ShowBatchesDeleteDialog()
    {
        ////  ensure that the follwing line is added in AndroidManifest.xml
        //// <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

       final  ArrayList<String> filepath = new ArrayList<String>();

        String tempstr;
        String rootDir= Environment.getExternalStorageDirectory().getPath();
        final List<String> listItems = new ArrayList<String>();
        File mfile=new File(rootDir);
        File[] list=mfile.listFiles();
        String tempupper;
        for(int i=0;i<mfile.listFiles().length;i++)
        {
            tempstr=list[i].getAbsolutePath();
            tempupper=tempstr.toUpperCase();
            if(tempupper.endsWith(".BCH") )
            {
                filepath.add(list[i].getAbsolutePath());
                listItems.add(list[i].getName());
            }
        }


        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        final    ArrayList<String> selectedItems = new ArrayList<String>();


        AlertDialog.Builder builder = new AlertDialog.Builder(MA);
        // Set the dialog title
        builder.setTitle("Delete Selected Batches")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(filepath.get(which));
                                } else if (selectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        {   int syze=selectedItems.size();
                            for(int i=0;i<syze;i++) {
                                File file = new File(selectedItems.get(i));
                                boolean deleted = file.delete();


                                String ttt=selectedItems.get(i).replaceAll(".bch",".pdf");

                                File filepdf = new File(ttt);
                                boolean deleted2 = filepdf.delete();

                                String ccc=selectedItems.get(i).replaceAll(".bch","-Chart.pdf");

                                File chartpdf = new File(ccc);
                                boolean deleted3 = chartpdf.delete();


                            }
                           // Toast.makeText(MA,String.format("Deleted %d Batches",syze),Toast.LENGTH_SHORT).show();
                            Msg.Show(String.format("Deleted %d Batch(es)",syze),MA);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }









}
