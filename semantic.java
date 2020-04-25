import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class semantic
{
  static int error_count=0;
  public static String[] Errors=new String[100];
  static int line_count=0;

  public static void main(String[] args)
  {
    BufferedReader read_file;
    try
    {
      read_file= new BufferedReader(new FileReader(args[0]));
      String line=read_file.readLine();
      while(line!=null)
      {
        line_count++;
        process(line);
        line=read_file.readLine();
      }
      read_file.close();
    }
    catch(Exception e)
    {
      // System.out.println("File read errror");
      e.printStackTrace();
    }
    // data.display_all();
    show_errors();
  }


  static int process(String line)
  {
    int return_val,i;
    // System.out.println(line);
    String[] split_line=line_ops.split(line);
    // display(split_line);
    if(split_line[1]==null)
    {
      return 0;
    }
    return_val=line_ops.is_include(split_line[1]);
    if(return_val==1)
    {
      return 0;
    }

    //Declaring variables and functions
    return_val=line_ops.is_datatype(split_line[0]);
    if(return_val!=-1)
    {
      declarings(split_line,return_val);
    }

    //Assigning
    chk_for_assign(split_line);

    return 0;
  }

  static void add_error(String message)
  {
    String err="In line "+String.valueOf(line_count)+": "+message;
    Errors[error_count]=err;
    error_count++;
  }

  static void show_errors()
  {
    int i;
    for(i=0;i<error_count;i++)
    {
      System.out.println(Errors[i]);
    }
  }


  static void display(String[] array)
  {
    int i=0;
    String item=array[i];
    while (item!=null)
    {
      System.out.print(item+" ");
      i++;
      item=array[i];
    }
    System.out.println();
  }


  static void declarings(String[] split_line,int return_val)
  {
    int i=0;
    int is_function=line_ops.index_of("(",split_line);
    if(is_function>0)
    {
      data.functions[data.function_count]=split_line[1];
      int close_bracket=line_ops.index_of(")",split_line);
      int para_count=0;
      for(i=is_function+1;i<close_bracket;i++)
      {
        if(line_ops.is_in(split_line[i],data.data_types)==-1)
        {
          if(line_ops.is_in(split_line[i],data.symbols)==-1)
          {
            // System.out.println(split_line[i]);
            para_count++;
          }
        }
      }
      data.parameters[data.function_count]=para_count;
      data.function_count++;
    }

    else
    {
      String[] vars=new String[100];
      int var_count=0;

      for(i=1;i<split_line.length;i++)
      {
        if(split_line[i]==null)
        {
          break;
        }
        if(line_ops.is_in(split_line[i],data.data_types)==-1)
        {
          if(line_ops.is_in(split_line[i],data.symbols)==-1)
          {
            vars[var_count]=split_line[i];
            var_count++;
          }
        }
      }

      if (return_val==0)
      {
        add_error("Void can't be a datatype");
      }

      else if(return_val==1)
      {
        for(i=0;i<var_count;i++)
        {
          if(data.var_exists(vars[i])<0)
          {
            data.integers[data.int_count]=vars[i];
            data.int_count++;
          }
        }
      }

      else if(return_val==2)
      {
        for(i=0;i<var_count;i++)
        {
          if(data.var_exists(vars[i])<0)
          {
            data.characters[data.char_count]=vars[i];
            data.char_count++;
          }
        }
      }

      else if(return_val==3)
      {
        for(i=0;i<var_count;i++)
        {
          if(data.var_exists(vars[i])<0)
          {
            data.floating[data.float_count]=vars[i];
            data.float_count++;
          }
        }
      }

      else if(return_val==4)
      {
        for(i=0;i<var_count;i++)
        {
          if(data.var_exists(vars[i])<0)
          {
            data.doubles[data.double_count]=vars[i];
            data.double_count++;
          }
        }
      }
    }
  }

  static void chk_for_assign(String[] split_line)
  {
    int i=0;
    int is_assign=line_ops.is_in("=",split_line);
    if(is_assign>0)
    {
      int[] eq_index=new int[30];
      int eq_count=0;
      for(i=0;i<split_line.length;i++)
      {
        if("=".equals(split_line[i]))
        {
          eq_index[eq_count]=i;
          eq_count++;
        }
      }
      for(i=0;i<eq_count;i++)
      {
        int cur_index=eq_index[i];
        String var=split_line[cur_index-1];
        String assigned=split_line[cur_index+1];

        int var_type=data.var_exists(var);
        int assign_type=data.var_exists(assigned);
        if(var_type!=-1)
        {
          error_count--;
        }
        if(assign_type!=-1)
        {
          error_count--;
        }
        if(var_type<0)
        {
          String err="Undeclared variable "+var;
          add_error(err);
        }
        //Assigning to int
        else if(var_type==1)
        {
          if(assign_type==1)
          {
            continue;
          }
          else if (assign_type!=-1)
          {
            String err="Invalid assignment to variable "+ var;
            add_error(err);
          }
          else
          {
            try
            {
              Integer.parseInt(assigned);
              continue;
            }
            catch(Exception e)
            {
              String err="Invalid assignment to variable "+ var;
              add_error(err);
            }
          }
        }
        //Assigning to char
        else if(var_type==2)
        {
          if(assign_type==2)
          {
            continue;
          }
          else if(assign_type!=-1)
          {
            String err="Invalid assignment to variable "+ var;
            add_error(err);
          }
          else
          {
            if(assigned.equals("\"") || assigned.equals("\'"))
            {
              continue;
            }
            else
            {
              String err="Invalid assignment to variable "+ var;
              add_error(err);
            }
          }
        }
        //Assigning to float and double
        else if(var_type==3 || var_type==4)
        {
          if(assign_type==3 || assign_type==4)
          {
            continue;
          }
          else if(assign_type!=-1)
          {
            String err="Invalid assignment to varibale "+var;
            add_error(err);
          }
          else
          {
            try
            {
              Double.parseDouble(assigned);
              continue;
            }
            catch(Exception e)
            {
              String err="Invalid assignment to varibale "+var;
              add_error(err);
            }
          }
        }
      }
    }
  }
}
