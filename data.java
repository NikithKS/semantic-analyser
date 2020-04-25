public class data
{
  public static String[] data_types={"void","int","char","float","double"};
  public static String[] defaults={"for","while","if","else"};
  public static String[] symbols={ " " , "," , "(" , "=" , "#", ";" , ")" , "<" , ">", "{", "}" , "\"" , "\'" ,"+","-","*"};


  public static int int_count=0;
  public static int char_count=0;
  public static int float_count=0;
  public static int double_count=0;
  public static int function_count=0;


  public static String[] functions=new String[30];
  public static int[] parameters=new int[30];
  public static String[] integers=new String[30];
  public static String[] floating=new String[30];
  public static String[] doubles=new String[30];
  public static String[] characters=new String[30];


  public static int var_exists(String var)
  {
    if(line_ops.is_in(var,integers)>=0)
    {
      String err=var+" already declared as Integer";
      semantic.add_error(err);
      return 1;
    }
    else if(line_ops.is_in(var,functions)>=0)
    {
      String err=var+" already declared as function";
      semantic.add_error(err);
      return 0;
    }
    else if(line_ops.is_in(var,floating)>=0)
    {
      String err=var+" already declared as Floating value";
      semantic.add_error(err);
      return 3;
    }
    else if(line_ops.is_in(var,doubles)>=0)
    {
      String err=var+" already declared as Double";
      semantic.add_error(err);
      return 4;
    }
    else if(line_ops.is_in(var,characters)>=0)
    {
      String err=var+" already declared as Character";
      semantic.add_error(err);
      return 2;
    }
    return -1;
  }

  public static void display_all()
  {
    int i;
    for(i=0;i<int_count;i++)
    {
      System.out.print(integers[i]+" ");
    }
    System.out.println();

    for(i=0;i<char_count;i++)
    {
      System.out.print(characters[i]+" ");
    }
    System.out.println();

    for(i=0;i<float_count;i++)
    {
      System.out.print(floating[i]+" ");
    }
    System.out.println();

    for(i=0;i<double_count;i++)
    {
      System.out.print(doubles[i]+" ");
    }
    System.out.println();

    for(i=0;i<char_count;i++)
    {
      System.out.print(characters[i]+" ");
    }
    System.out.println();

    for(i=0;i<function_count;i++)
    {
      System.out.print(functions[i]+" ");
    }
    System.out.println();
  }
}
