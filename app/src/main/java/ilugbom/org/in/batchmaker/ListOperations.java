package ilugbom.org.in.batchmaker;

/**
 * Created by milind on 7/2/19.
 */



public class ListOperations
{ private MainActivity MA;
  void SetMA(MainActivity MA){this.MA=MA;}
  int maxstrength=5000;

    public void FillList(String FirstSeat,String LastSeat)
    {
        //   intItemList.removeAll(planetList);
        MA.Roll.removeAll(MA.Roll);
        MA.checkmark.removeAll(MA.checkmark);

        String temproll=FirstSeat;

        int i=0;
        for(i=0;i<maxstrength;i++)
        {   MA.Roll.add(temproll);
            MA.checkmark.add(Boolean.FALSE);
            ListViewItemDTO dto=new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(temproll);

            MA.initItemList.add(dto);
            if(temproll.contains(LastSeat)) break;
            temproll=Increment(temproll);
        }

        //((BaseAdapter) mainListView.getAdapter()).notifyDataSetChanged();

    }

public void SelectAll()
{

    int size = MA.initItemList.size();
    for(int i=0;i<size;i++)
    {
        ListViewItemDTO dto = MA.initItemList.get(i);
        dto.setChecked(true);
    }

    MA.listViewDataAdapter.notifyDataSetChanged();

}


    public void SelectNone()
    {
      int size = MA.initItemList.size();
        for(int i=0;i<size;i++)
        {
            ListViewItemDTO dto = MA.initItemList.get(i);
            dto.setChecked(false);
        }

        MA.listViewDataAdapter.notifyDataSetChanged();

    }

  public void SelectReverse()
  {
      int size = MA.initItemList.size();
      for(int i=0;i<size;i++)
      {
          ListViewItemDTO dto = MA.initItemList.get(i);

          if(dto.isChecked())
          {
              dto.setChecked(false);
          }else {
              dto.setChecked(true);
          }
      }

      MA.listViewDataAdapter.notifyDataSetChanged();

  }


    private String Increment(String alphaNumericString)
    {  char[] an = alphaNumericString.toCharArray();
        int i = an.length - 1;
        while (true)
        {   if(an[i]<'0' || an[i]>'9') return new String(an);
            if (i <= 0)
                try { throw new Exception("Maxed out number!!!"); }
                catch (Exception e)
                { e.printStackTrace(); }
            an[i]++;

            if (an[i] - 1 == '9')
            {
                an[i] = '0';
                i--;
                continue;
            }


            return new String(an);
        }
    }




}
