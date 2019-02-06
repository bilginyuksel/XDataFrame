package DataFrame;

import DataFrameException.ColumnElementTypeException;
import DataFrameException.ColumnRangeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class DataFrame {

    //General explanation and add methods , one hot encoding,dummies

    private ArrayList<Column> columns;
    private ArrayList<Row> rows;


    public DataFrame(ArrayList<Row> ro,ArrayList<Column> co){this.rows = ro; this.columns= co;}
    public DataFrame(){new Row();}


    //**********************************************************************************************************
    //ARRAYTODATAFRAME FUNCTIONS

    public DataFrame arrayToDataFrame(Object[][] a){

        /***********************************************************************************************************

        DataFrame.DataFrame contains columns, every column has own data type. You can manipulate columns as you wanted.

                        It converts a 2D array to DataFrame.DataFrame


                 Create Object From DataFrame.DataFrame df = new DataFrame.DataFrame(); then you can use this function like
                 df.arrayToDataFrame(2DArray); it supplies all variable types Object,String,int,double


       SAMPLE : ---------------------------------------------------------------------------------------------------


       Object a[][] = new Object[4][4];
       Random random = new Random();
       int i,j;
       for(i=0;i<a.length<i++)
            for(j=0;j<a[i].length;j++)
                a[i][j] = random.nextFloat();  #Creates number floating points

        #You filled an array now create DataFrame.DataFrame object and call function arrayToDataFrame

        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df.arrayToDataFrame(a);
        System.out.println(a);



        ----------------------------------------------------------------------------------------------------------------
        *****************************************************************************************************************/

        ArrayList<Row> temp = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();
        int rowlen = a.length;
        int collen = a[0].length;
        Object []tmp = new Object[collen];
        for(int i=0;i<collen;i++)
            tmp[i] = i;
        temp.add(new Row(tmp));

        for(int i=0;i<rowlen;i++){
          temp.add(new Row(a[i]));
        }
        int i=0;
        for(i=0;i<collen;i++){
            Object []tmpcl = new Object[rowlen];
            for(int j=0;j<rowlen;j++){
                tmpcl[j] = a[j][i];
            }
            col.add(new Column(String.valueOf(i),tmpcl));
        }


        this.columns = col;
        this.rows = temp;

        return new DataFrame(rows,columns);
    }
    public DataFrame arrayToDataFrame(int[][] a){

        /***********************************************************************************************************

         DataFrame.DataFrame contains columns, every column has own data type. You can manipulate columns as you wanted.

         It converts a 2D array to DataFrame.DataFrame


         Create Object From DataFrame.DataFrame df = new DataFrame.DataFrame(); then you can use this function like
         df.arrayToDataFrame(2DArray); it supplies all variable types Object,String,int,double


         SAMPLE : ---------------------------------------------------------------------------------------------------


         int a[][] = new int[4][4];
         Random random = new Random();
         int i,j;
         for(i=0;i<a.length<i++)
         for(j=0;j<a[i].length;j++)
         a[i][j] = random.nextInt(100);  #Bound 100 this function creates random numbers beetween 0<x<100

         #You filled an array now create DataFrame.DataFrame object and call function arrayToDataFrame

         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.arrayToDataFrame(a);
         System.out.println(a);



         ----------------------------------------------------------------------------------------------------------------
         *****************************************************************************************************************/

        ArrayList<Row> temp = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();
        int rowlen = a.length;
        int collen = a[0].length;
        Object []tmp = new Object[collen];
        for(int i=0;i<collen;i++)
            tmp[i] = i;
        temp.add(new Row(tmp));



        //Adding row but first convert type to object.If not constructor not working as we wanted and rows
        //seen like OBJECT NUMBER
        int z=0;
        for(int i=0;i<rowlen;i++){
            Object t[] = new Object[a[i].length];
            for(int b:a[i]){
                t[z++] = (Object)b;
            }
            z=0;
            temp.add(new Row(t));
        }
        int i=0;
        for(i=0;i<collen;i++){
            int []tmpcl = new int[rowlen];
            for(int j=0;j<rowlen;j++){
                tmpcl[j] = a[j][i];
            }
            col.add(new Column(String.valueOf(i),tmpcl));
        }


        this.columns = col;
        this.rows = temp;

        return new DataFrame(rows,columns);
    }
    public DataFrame arrayToDataFrame(double[][] a){

        /***********************************************************************************************************

         DataFrame.DataFrame contains columns, every column has own data type. You can manipulate columns as you wanted.

         It converts a 2D array to DataFrame.DataFrame


         Create Object From DataFrame.DataFrame df = new DataFrame.DataFrame(); then you can use this function like
         df.arrayToDataFrame(2DArray); it supplies all variable types Object,String,int,double


         SAMPLE : ---------------------------------------------------------------------------------------------------


         double a[][] = new double[4][4];
         Random random = new Random();
         int i,j;
         for(i=0;i<a.length<i++)
         for(j=0;j<a[i].length;j++)
         a[i][j] = random.nextDouble();  #Creates double points

         #You filled an array now create DataFrame.DataFrame object and call function arrayToDataFrame

         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.arrayToDataFrame(a);
         System.out.println(a);



         ----------------------------------------------------------------------------------------------------------------
         *****************************************************************************************************************/


        ArrayList<Row> temp = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();
        int rowlen = a.length;
        int collen = a[0].length;
        Object []tmp = new Object[collen];
        for(int i=0;i<collen;i++)
            tmp[i] = i;
        temp.add(new Row(tmp));

        int z=0;
        for(int i=0;i<rowlen;i++){
            Object t[] = new Object[a[i].length];
            for(double b:a[i]){
                t[z++] = (Object)b;
            }
            z=0;
            temp.add(new Row(t));
        }
        int i=0;
        for(i=0;i<collen;i++){
            double []tmpcl = new double[rowlen];
            for(int j=0;j<rowlen;j++){
                tmpcl[j] = a[j][i];
            }
            col.add(new Column(String.valueOf(i),tmpcl));
        }

        this.columns = col;
        this.rows = temp;

        return new DataFrame(rows,columns);
    }
    public DataFrame arrayToDataFrame(String[][] a){

        /***********************************************************************************************************

         DataFrame.DataFrame contains columns, every column has own data type. You can manipulate columns as you wanted.

         It converts a 2D array to DataFrame.DataFrame


         Create Object From DataFrame.DataFrame df = new DataFrame.DataFrame(); then you can use this function like
         df.arrayToDataFrame(2DArray); it supplies all variable types Object,String,int,double


         SAMPLE : ---------------------------------------------------------------------------------------------------


         String a[][] = new String[4][4];
         Random random = new Random();
         int i,j;
         for(i=0;i<a.length<i++)
         for(j=0;j<a[i].length;j++)
         a[i][j] = String.valueOf(random.nextInt(100));  #Bound 100 this function creates random numbers beetween 0<x<100


         #You filled an array now create DataFrame.DataFrame object and call function arrayToDataFrame

         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.arrayToDataFrame(a);
         System.out.println(a);



         ----------------------------------------------------------------------------------------------------------------
         *****************************************************************************************************************/


        ArrayList<Row> temp = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();
        int rowlen = a.length;
        int collen = a[0].length;
        Object []tmp = new Object[collen];
        for(int i=0;i<collen;i++)
            tmp[i] = i;
        temp.add(new Row(tmp));

        int z=0;
        for(int i=0;i<rowlen;i++){
            Object t[] = new Object[a[i].length];
            for(String b:a[i]){
                t[z++] = (Object)b;
            }
            z=0;
            temp.add(new Row(t));
        }
        int i=0;
        for(i=0;i<collen;i++){
            String []tmpcl = new String[rowlen];
            for(int j=0;j<rowlen;j++){
                tmpcl[j] = a[j][i];
            }
            col.add(new Column(String.valueOf(i),tmpcl));
        }


        this.columns = col;
        this.rows = temp;

        return new DataFrame(rows,columns);
    }

    //ARRAYTODATAFRAME FUNCTIONS
    //**********************************************************************************************************


    //CREATE DATAFRAME FROM CSV FILE
    //***********************************************************************************************************

    public DataFrame read_csv(String path){

        //Figure column problem from easiest way. Algorithm analyze is important and types.


        /***********************************************************************************************
        Reads csvFiles according to given path.Just create dataframe object and call the function.
        Sample: ---------------------------------------------------------------------------------
        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df = df.read_csv(file_path);
        ************************************************************************************************/
        BufferedReader bufferedReader = null;
        String line = "";
        String cvs_split_comma = ",";
        ArrayList<Row> row = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();

        try{

            bufferedReader = new BufferedReader(new FileReader(path));
            int i=0;
            Path file_path = Paths.get(path);
            long lineCount = Files.lines(file_path).count();
            int collen = bufferedReader.readLine().split(cvs_split_comma).length;
            int rowlen = (int)lineCount;
            //Read 2 lines for column names and column types for each one, start control with integer>double>String>Object
            Object [][]temp = new Object[rowlen][collen];
            bufferedReader = new BufferedReader(new FileReader(path));
            System.out.println("DataFrame.Row Length : " +temp.length);
            System.out.println("DataFrame.Column Length : " +temp[0].length);

            while((line = bufferedReader.readLine())!=null){

                //0,1,2,3 is going like rows.
                Object tmp[] = line.split(cvs_split_comma);
                for(int j = 0;j<tmp.length;j++){
                    temp[i][j] = tmp[j];
                }
                row.add(new Row(tmp));
                i++;
            }

            for(i=0;i<collen;i++){
                Object []tmpcl = new Object[rowlen];
                for(int j=0;j<rowlen;j++){
                    tmpcl[j] = temp[j][i];
                }
                col.add(editColumn(tmpcl[1],tmpcl));
            }



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.rows = row;
        this.columns = col;

        return new DataFrame(rows,columns);

    }

    //***********************************************************************************************************
    //CREATE DATAFRAME FROM CSV FILE


    private Column editColumn(Object first_element,Object []array){

        //NaN column names solve that problem

        /****************************************************************************************************
         Every column has its own type. Because of this we have to do type conversion.
         According to type it converts the array. Finds type as looking array's first element(not column name).
         Using : Create a column object. DataFrame.Column constructor takes parameter as array(any type). So for declaring the type
         we are calling our function check_type
         SAMPLE : -------------------------------------------------------------------------------------------

         Fill Arrays.
         And loop for fill columns {
         Assume array named tmp for filling column
         this.columns.add(editColumn(feature_name,array[]));
         }
         -----------------------------------------------------------------------------------------------------

         **********************************************************************************************************/
        String type ;
        try{
            //First try block
            first_element = Integer.valueOf(first_element.toString());
            type = "int64";
        }catch (NumberFormatException e){
            try{
                //Second try block
                first_element = Double.valueOf(first_element.toString());
                type = "double";
            }catch (NumberFormatException e1){
                //Second catch
                type="object";
            }
        }


        if(type == "int64"){
            int []tmp = new int[array.length-1];
            for(int i=1;i<array.length;i++)
                tmp[i-1] = Integer.valueOf(array[i].toString());

            return new Column(array[0].toString(),tmp);
        } else if(type =="double"){
            double tmp[] = new double[array.length-1];
            for(int i=1;i<array.length;i++)
                tmp[i-1] = Double.valueOf(array[i].toString());

            return new Column(array[0].toString(),tmp);
        }else{
            Object tmp[] = new Object[array.length-1];
            for(int i=1;i<array.length;i++)
                tmp[i-1] = array[i];
            return new Column(array[0].toString(),tmp);
        }
    }
    public DataFrame column(String feature){

        /***********************************************************************************************************
         *
        If you want to edit single column or want to examine single column more detailed, you can use that function
        this function returns you a single column DataFrame.DataFrame. Takes one parameter feature (feature name).
        SAMPLE : ------------------------------------------------------------------------------------------
         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.read_csv("iris.csv");
         df.info();

         #Info function gives you kind of output so now you know column names

         #RangeIndex: 150 entries,0 to 149
         #Data columns (total 6 columns):
         #Id                  150 non-null int64
         #SepalLengthCm       150 non-null double
         #SepalWidthCm        150 non-null double
         #PetalLengthCm       150 non-null double
         #PetalWidthCm        150 non-null double
         #Species             150 non-null object
         #dtypes: int64(1),double(4),object(1)

         df.column("SepalLengthCm");

         ------------------------------------------------------------------------------------------------

        ******************************************************************************************************/
        int i=0;
        for(i=0;i<columns.size();i++){
            if(columns.get(i).getFeature_name().equals(feature)){
                break;
            }
        }

        ArrayList<Column> c = new ArrayList<>();
        c.add(columns.get(i));
        new Row();
        ArrayList<Row> r = new ArrayList<>();
        r.add(new Row(columns.get(i).getFeature_name()));
        for(int j=0;j<columns.get(i).getElements().size();j++){
            r.add(new Row(columns.get(i).getElements().get(j)));
        }
        this.rows = r;
        this.columns = c;
        return new DataFrame(this.rows,this.columns);

    }

    public DataFrame newColumn(String feature_name , Object []column) throws ColumnRangeException {

        /***********************************************************************************************************
        This method adds new DataFrame.Column to your DataFrame.DataFrame object. Takes 2 parameters
        first parameter column name second parameter 1D array (column elements).
        If the size doesn't fit the DataFrame.DataFrame it throws an exception otherwise it adds new column
        to your DataFrame.DataFrame.
        SAMPLE : --------------------------------------------------------------------------------------------
        #I assume you have a not null DataFrame.DataFrame object named df.
        Object []column_elements = new Object[length];
        #fill column_elements ARRAY

        df.newColumn("new feature",column_elements);

        -------------------------------------------------------------------------------------------------------

        **********************************************************************************************************/
        if(columns.get(0).size()!= column.length)
            throw new ColumnRangeException("New column indexes should same with the older columns.");

        ArrayList<Column> col = columns;
        Object tmp[] = new Object[151];
        tmp[0] = feature_name;
        for(int i=1;i<tmp.length;i++)
            tmp[i] = column[i-1];

        col.add(editColumn(tmp[1],tmp));

        ArrayList<Row> ro = rows;

        ro.get(0).getRow().add(feature_name);
        int i=1;
        for(Object o : column){
            ro.get(i++).getRow().add(o);
        }

        this.rows = ro;
        this.columns = col;

        return new DataFrame(this.rows,this.columns);
    }
    public Object element(int column,int row){
        //Gives you an element for given column and row indexes
        return this.columns.get(column).getElements().get(row);
    }

    public Object[] array_from_column(int col_index){
        /*****************************************************************************************************
        This method changes column to an Object[] type array for manipulation detailed. Takes one parameter
        index of wanted column.
         SAMPLE : ------------------------------------------------------------------------------------------
         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.read_csv("iris.csv");
         Object tmp[] = df.array_from_column(2);
         -----------------------------------------------------------------------------------------------------
         Now you can use tmp array as you wanted.
         *****************************************************************************************************/
     return columns.get(col_index).getElements().toArray();
    }
    public DataFrame changeElement(int col_index,int row_index,Object element) throws ColumnElementTypeException {
        String type = element.getClass().getSimpleName();
        if(type.equals("String")) type = "object";
        else if(type.equals("Double")) type="double";
        else if(type.equals("Integer")) type="int64";

        System.out.println("Type of Type :" + type);
        System.out.println("DataFrame.Column Real Type : " + columns.get(col_index).getType()  );
        if(columns.get(col_index).getType() !=  type)
            throw new ColumnElementTypeException("Type of object should same with column data type !");
        /**************************************************************************************
        This function changes one element from DataFrame.DataFrame. According to column index and row index
          finds the element then removes the element. After removing it add's the new element which
          is taken as parameter.
          SAMPLE :----------------------------------------------------------------------------------
          #I assume you have a non null DataFrame.DataFrame object
          df.changeElement(2,3,2.83);
          ------------------------------------------------------------------------------------------
          First parameter column index , second parameter row index.
          ******************************************************************************************/

        Object tmp = columns.get(col_index).getElements().get(row_index);
        columns.get(col_index).getElements().remove(row_index);
        columns.get(col_index).getElements().add(row_index,element);
        rows.get(row_index+1).getRow().remove(col_index+1);
        System.out.println("Dealed DataFrame.Row Removed Version : " + rows.get(row_index+1));
        rows.get(row_index+1).getRow().add(col_index+1,element);
        System.out.println("Dealed DataFrame.Row Added Version : " + rows.get(row_index+1));
        System.out.println("Getting objecct : " + tmp);
        System.out.println("Added DataFrame.Column Place : " + columns.get(col_index).getElements().get(row_index));
        System.out.println("Added DataFrame.Row Place :"+ rows.get(row_index+1).getRow().get(col_index));
        return new DataFrame(rows,columns);
    }



    public DataFrame loc(int start,int end){
        /***********************************************************************************************

        loc function takes 2 integer parameters start,end : start parameter means start index of columns,
        end parameter means end index of columns and it gives you a new DataFrame.DataFrame with generated columns.

        SAMPLE : ---------------------------------------------------------------------------------------
         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.read_csv('iris.csv'); #Fill dataFrame from iris.csv file
         df.loc(1,6); #Your changed dataFrame
         -----------------------------------------------------------------------------------------------

         ************************************************************************************************/
        ArrayList<Column> c = new ArrayList<>();
        while(start<end){
            c.add(columns.get(start++));
        }
        new Row();
        ArrayList<Row> r = new ArrayList<>();
        Object[][] tmp = new Object[c.get(0).getElements().size()+1][c.size()];
        int i=0,j = 0;
        for(Column co : c){
            tmp[i++][j] = co.getFeature_name();
            for(Object o : co.getElements()){
                tmp[i++][j] = o;
            }j++;i=0;
        }

        i=0;
        while(tmp.length>i){
            Object []temp = new Object[tmp[i].length];
            for(j=0;j<tmp[i].length;j++){
                temp[j] = tmp[i][j];
            }
            i++;
            r.add(new Row(temp));
        }


        this.rows = r;
        this.columns = c;
        return new DataFrame(this.rows,this.columns);}
    public DataFrame iloc(int start,int end){

        //This function returns you a DataFrame.DataFrame. New rows beetween start and end index.
        //Using same with loc function...

        ArrayList<Row> r = new ArrayList<>();
        ArrayList<Column> col = new ArrayList<>();
        new Row();
        if(start != 0)
            r.add(rows.get(0));

        for(int i=start;i<=end;i++){
            r.add(rows.get(i));
        }
        int j=0;
        for(Column c : columns){
            Object tmp[] = new Object[end-start+2];
            tmp[j++] = c.getFeature_name();
            for(int i=start;i<=end;i++){
                tmp[j++]=c.getElements().get(i);
            }
            col.add(editColumn(tmp[1],tmp));
            j=0;
        }


        this.rows = r;
        this.columns = col;
        return new DataFrame(this.rows,this.columns);
    }


    public Object[] column_names(){

        //Returns Object Array which containts Column_names Of DataFrame..

        Object[] col_names = new Object[columns.size()];
        int i=0;
        for(Column c: columns)
            col_names[i++] = c.getFeature_name();
        return col_names;

    }
    public int[] shape(){
        //Gives dataframe shape rows-columns first index points rows, second index points columns
        int shapeofdata [] = new int[2];
        shapeofdata[1] = columns.size();
        shapeofdata[0] = rows.size()-1;
        return shapeofdata;
    }



    public String head(int element){
        /*
        Gives First elements(given parameter element) Elements of DataFrame.DataFrame for first look at data

        SAMPLE : ---------------------------------------------------------------------------------
        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df.read_csv("iris.csv");
        System.out.println(df.head(element));

        -------------------------------------------------------------------------------------------

        ********************************************************************************************/
        String obj = "";
        for(int i=0;i<element+1;i++){
            obj += rows.get(i)+ "\n";
        }
        return obj;
    }
    public String head(){
        /*
        Gives First 5 Elements of DataFrame.DataFrame for first look at data

        SAMPLE : ---------------------------------------------------------------------------------
        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df.read_csv("iris.csv");
        System.out.println(df.head());

        -------------------------------------------------------------------------------------------

        ********************************************************************************************/
        String obj = "";
        for(int i=0;i<6;i++){
            obj += rows.get(i) + "\n";
        }
        return obj;
    }
    public String tail(){
        /*
        Gives Last 5 Elements of DataFrame.DataFrame for first look at data

        SAMPLE : ---------------------------------------------------------------------------------
        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df.read_csv("iris.csv");
        System.out.println(df.tail());

        -------------------------------------------------------------------------------------------

        ********************************************************************************************/
        String obj = rows.get(0) +"\n";
        int size_of_rows = rows.size()-1;
        for(int i=size_of_rows;i>size_of_rows-5;i--)
            obj+= rows.get(i) +"\n";

        return obj;
    }
    public String tail(int element){
        /*
        Gives Last elements(given parameter element) Elements of DataFrame.DataFrame for first look at data

        SAMPLE : ---------------------------------------------------------------------------------
        DataFrame.DataFrame df = new DataFrame.DataFrame();
        df.read_csv("iris.csv");
        System.out.println(df.tail(element));

        -------------------------------------------------------------------------------------------

        ********************************************************************************************/
        String obj = rows.get(0) + "\n";
        int size_of_rows = rows.size()-1;
        for(int i=size_of_rows;i>size_of_rows-element;i--)
            obj+= rows.get(i) + "\n";
        return obj;
    }
    public String info(){

        /*********************************************************************************************
        Gives information with data DataFrame.Row length, row indexes , Columns ,column sizes , column data types.

         SAMPLE : ------------------------------------------------------------------------------------
         DataFrame.DataFrame df = new DataFrame.DataFrame();
         df.read_csv("iris.csv");
         System.out.println(df.info());

         -------------------------------------------------------------------------------------------------
         **************************************************************************************************/

        //Find memoryusage of file..

        String obj = "";
        obj += "RangeIndex: "+(this.rows.size()-1)+" entries,0 to "+(Row.i-1) +"\n";
        obj += "Data columns (total "+this.columns.size()+" columns):\n";
        String space = " ";
        int intCount=0,doubleCount=0,objectCount=0;
        for(Column c:columns){
            if(c.getType() == "int64")
                intCount++;
            else if(c.getType() == "double")
                doubleCount++;
            else
                objectCount++;
            obj += c.getFeature_name() + space.repeat(20-c.getFeature_name().length()) + c.size() + " non-null " + c.getType() +"\n";
        }
        String dtypes="";
        if(intCount>0)
            dtypes+="int64("+intCount+"),";
        if(doubleCount>0)
            dtypes+="double("+doubleCount+"),";
        if(objectCount>0)
            dtypes+="object("+objectCount+")";

        obj += "dtypes: "+dtypes ;

        return obj;
    }
    public String describe(){
        /**************************************************************************************************
         *
        This function gives statistical information about unsorted dataFrame. Like column row count for data
        mean for every column, every columns min value , every columns median,qartil1,qartil3,
        and max value. Easy to call :
        I assume you have a non-null DataFrame.DataFrame object named df
        System.out.println(df.describe());

        ****************************************************************************************************/
        String obj="";
        String space = " ";
        int i=0,size = columns.size();
        obj+="     ";
        for(i=0;i<size;i++)
            obj += space.repeat(20-columns.get(i).getFeature_name().length())+columns.get(i).getFeature_name() ;

        obj += "\ncount";
        for(i=0;i<size;i++)
            obj += space.repeat(20-String.valueOf(String.format("%.6f",Double.valueOf(columns.get(i).size()))).length()) + String.format("%.6f",Double.valueOf(columns.get(i).size()));

        obj += "\nmean ";
        for(i=0;i<size;i++)
            obj += space.repeat(20-String.valueOf(String.format("%.6f",mean(i))).length()) + String.format("%.6f",mean(i));

        obj+="\nmin  ";
        for (i=0;i<size;i++)
            obj+= space.repeat(20-String.valueOf(String.format("%.6f",columns.get(i).min())).length()) + String.format("%.6f",columns.get(i).min());

        obj+= "\n25%  ";
        for(i =0;i<size;i++)
            obj+= space.repeat(20-String.valueOf(String.format("%.6f",columns.get(i).Q1())).length()) + String.format("%.6f",columns.get(i).Q1());

        obj+= "\n50%  ";
        for(i =0;i<size;i++)
            obj+= space.repeat(20-String.valueOf(String.format("%.6f",columns.get(i).Q2())).length()) + String.format("%.6f",columns.get(i).Q2());

        obj+= "\n75%  ";
        for(i =0;i<size;i++)
            obj+= space.repeat(20-String.valueOf(String.format("%.6f",columns.get(i).Q3())).length()) + String.format("%.6f",columns.get(i).Q3());

        obj+="\nmax  ";
        for (i=0;i<size;i++)
            obj+= space.repeat(20-String.valueOf(String.format("%.6f",columns.get(i).max())).length()) + String.format("%.6f",columns.get(i).max());


        return obj;
    }

    public Object[] unique(String column_name){
        int i ;
        for(i=0;i<columns.size();i++)
            if(columns.get(i).getFeature_name().equals(column_name)) break;


        Set<Object> set = new HashSet<>(columns.get(i).getElements());

        return set.toArray();
    }
    public Object[] row(int row_index){
        Object[] row = this.rows.get(row_index+1).getRow().toArray();

        return row;
    }

    private double mean(int i){
        double mean = 0;
        if(columns.get(i).getType() == "object")
            return 0;

        for(Object o : columns.get(i).getElements()){
            mean += Double.valueOf(o.toString());
        }

        return mean/columns.get(i).size();
    }

    @Override
    public String toString() {
        String obj ="";
        for(Row r : rows){
            obj += r +"\n";
        }

        return obj +"\n" + "["+(this.rows.size()-1)+" rows x "+this.columns.size()+" columns]\n";
    }

}
