package ilugbom.org.in.batchmaker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean endnow=false;
    ArrayList<String> Roll = new ArrayList<String>();
    ArrayList<String> tempRoll = new ArrayList<>();
    int checkmarkCount = 0;
    ArrayList<ListViewItemDTO> initItemList = new ArrayList<>();
    ArrayList<ListViewItemDTO> backupItemList = new ArrayList<>();
    String froll="M058151",lroll="M058200",tempstr;
    ListViewItemCheckboxBaseAdapter listViewDataAdapter;

    ListOperations LO=new ListOperations();
    //CurrentPDF CPDF=new CurrentPDF();
    FileSaveLoad FSL=new FileSaveLoad();
    PreferenceDialog PD = new PreferenceDialog();
  //  CreatePDF CP=new CreatePDF();
   // CreateORALpdf OPDF= new CreateORALpdf();
   // CreateCombinedPDF CCPDF= new CreateCombinedPDF();
    PDFGeneration PGEN=new PDFGeneration();
    TextView FC;
    FloatingActionButton fab;
    boolean modified=false;

    /////////////Show Msg Functions /////////////////////////////////////
    public void show(int tempnum)
    {
        Toast.makeText(getBaseContext(),String.valueOf(tempnum),Toast.LENGTH_SHORT).show();
    }

    public void show(String tempstring)
    {
        Toast.makeText(getBaseContext(),tempstring,Toast.LENGTH_SHORT).show();
    }

//////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else

        if(modified)  {  Bye(); }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BatchMaker - 1.7");

        fab =  findViewById(R.id.fab);

        fab.setOnTouchListener(new View.OnTouchListener() {


            float x, y;
            float x1,y1;
            float x2,y2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {   case MotionEvent.ACTION_UP :
                    if(Math.abs(x2-x1)<10 && Math.abs(y2-y1)<10)

                    {
                        OnFloatingButton();
                    }

                    return true;
                    case MotionEvent.ACTION_MOVE:

                        x2=fab.getX()+event.getX()-x; y2=fab.getY()+event.getY()-y;

                        fab.setX(x2);
                        fab.setY(y2);
                        return true;
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        x1=fab.getX()+event.getX()-x; y1=fab.getY()+event.getY()-y;
                        x2=fab.getX();y2=fab.getY();
                        //   x1=x;
                        //  y1=y;
                        //   Msg.show(String.format("%d",event.getX()));
                        return true;
                }

                return false;
            }
        });



       // if(!StoragePermissionGranted()) finish();

        LO.SetMA(this);
        FSL.SetMA(this);
       // CP.SetMA(this);
       // OPDF.SetMA(this);
        //CPDF.SetMA(this);
      //  CCPDF.SetMA(this);
        PGEN.SetMA(this);

        FC= findViewById(R.id.FabCounter);
        /////////////////////////////////////Custom List Initialization///////////


        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.list_view_with_checkbox);

        // Initiate listview data.
      //  final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();

        LO.FillList(froll,lroll);
        checkmarkCount=0;

        // Create a custom list view adapter with checkbox control.
        // In the foloowing line Original ApplicationContext() gave white color to listview
        // Changed to getBaseContex()
        listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getBaseContext(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox =  view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                     checkmarkCount--;
                     modified=true;
                }else
                {   if(checkmarkCount<32)
                    {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                    checkmarkCount++;
                    modified = true;
                    }
                    else show("Batch Full");
                }

                fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPink));
                FC.setText(String.format("%d",checkmarkCount));

            }
        });

//////////////////////////////End of Custom List InitializationList////////////////////////
        PD.LoadPreferrences(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorGreen));



          if(!StoragePermissionGranted()) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

   switch(id)
    {case R.id.action_new :
        if(modified) Msg.Show("Please Save Current Batch",this);
        else
            LO.GetNewRoll();
        return true;

      case R.id.action_select_all : LO.SelectAll(); return true;
      case R.id.action_select_none : LO.SelectNone(); return true;
      case R.id.action_load : FSL.OpenFileDialog(); return true;
      case R.id.action_save :
      { if(!StoragePermissionGranted())
         Msg.Show("No Permission",this);
      else
          FSL.SaveDirect();
      }
      return true;
      case R.id.action_pick_unpick : LO.PickRoutine();return true;
      case R.id.action_reverse : LO.SelectReverse();return true;
     }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id)
        {   case R.id.nav_edit_header : FSL.ShowHeaderDlg(); break;
            case R.id.nav_save_combined_pdf : PGEN.CreateCombined(); break;
            case R.id.nav_set_preferences :  EditSettings();  break;
            case R.id.nav_send_combined : FSL.SendCombinedList();break;
            case R.id.nav_send : FSL.SendList();break;

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



void EditSettings()
{
   PD.SetPreferrenceDlg(this);

}

void OnFloatingButton()
{
    if(modified) {
        FSL.SaveDirect();
    }
    else
        LO.GetNewRoll();

}


    public void Bye()
    {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Modified !");
        myAlertDialog.setMessage("Save Batch Before Exit ?");
        myAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the OK button is clicked
                endnow=true;
                FSL.SaveDirect();

            }});

        myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                /////do nothing and continue
            }});

        myAlertDialog.setNeutralButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the Cancel button is clicked
                finish();
            }});

        myAlertDialog.show();
    }





    public  boolean StoragePermissionGranted()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG,"Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Msg.Show("Permission Granted",this);

        }
    }


}
