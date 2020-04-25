public class line_ops
{
  public static String[] split(String line)
  {
    String[] split_items=new String[100];
    int item_count=0;
    String item="";
    int line_len=line.length();
    int i,is_symbol;
    String symb;
    for(i=0;i<line_len;i++)
    {
      symb=String.valueOf(line.charAt(i));
      is_symbol=is_in(symb,data.symbols);
      if(is_symbol==-1)
      {
        item+=symb;
      }
      else
      {
        if(!(item.equals("")))
        {
          split_items[item_count]=item;
          item_count++;
          item="";
        }
        if(is_symbol!=0)
        {
          split_items[item_count]=String.valueOf(symb);
          item_count++;
        }
      }
    }
    // semantic.display(split_items);
    return split_items;
  }

  static int is_include(String first_item)
  {
    if(first_item.equals("#"))
    {
      return 1;
    }
    else
    {
      return 0;
    }
  }


  static int is_datatype(String first_item)
  {
    int ret_value=is_in(first_item,data.data_types);
    if(ret_value>=0)
    {
      return ret_value;
    }
    else
    {
      return -1;
    }
  }

  static int if_default(String first_item)
  {
    int ret_value=is_in(first_item,data.defaults);
    if(ret_value>=0)
    {
      return ret_value;
    }
    else
    {
      return -1;
    }
  }


  static int is_in(String item, String[] array)
  {
    // semantic.display(array);
    int i;
    int len=array.length;
    for(i=0;i<len;i++)
    {
      try
      {
        if(array[i].equals(item))
        {
          return i;
        }
      }
      catch(Exception e)
      {
        break;
      }
    }
    return -1;
  }


  static int index_of(String item, String[] array)
  {
    int index;
    for(index=0;index<array.length;index++)
    {
      if(item.equals(array[index]))
      {
        return index;
      }
    }
    return -1;
  }
}
