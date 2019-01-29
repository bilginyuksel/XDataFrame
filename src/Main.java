import java.util.Random;

public class Main {

    public static void main(String args[]){


        Object[][] a = new Object[10][5];
        Random random = new Random();
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                a[i][j] = random.nextInt(150);
            }
        }


        int[][] b =new int[3][3];
        for(int i=0;i<b.length;i++){
            for(int j=0;j<b[i].length;j++){
                b[i][j] = random.nextInt(100);
            }
        }


        System.out.println("********************************************************************");
        DataFrame df = new DataFrame();
        df = df.arrayToDataFrame(b);
        System.out.println(df);
        System.out.println("*********************************************************************");
        DataFrame df2 = new DataFrame();
        df2 = df2.arrayToDataFrame(a);
        System.out.println(df2);
        System.out.println("*********************************************************************");


        DataFrame df1 = new DataFrame();
        df1 = df1.read_csv("Iris.csv");
        System.out.println(df1);
        System.out.println("*********************************************************************");
        System.out.println(df1.info());
        System.out.println("*********************************************************************");
        System.out.println(df1.loc(1,6));
        System.out.println("**********************************************************************");
        System.out.println(df1.head(8));
        System.out.println("***********************************************************************");
        System.out.println(df1.tail(22));
        System.out.println("**********************************************************************");

     // System.out.println(df1.column("PetalLengthCm"));
        Object element = df1.element(1,2);
        System.out.println("Get Single Element From DataFrame ");
        System.out.println("Element : " + element);
        System.out.println("Element Type : " + element.getClass().getSimpleName());
        System.out.println("************************************************************************");
        try {
            df1.changeElement(1,2,3.22);
        }catch (ColumnElementTypeException e) {e.printStackTrace();}

        Object newElement = df1.element(1,2);
        System.out.println("Get New Element From DataFrame");
        System.out.println("Element : " + newElement);
        System.out.println("Element Type : " + element.getClass().getSimpleName());
        System.out.println("*************************************************************************");
        Object col_array[] = df1.array_from_column(3);
        //Print col_array and types

        System.out.println("GET ARRAY FROM DATAFRAME");
        System.out.print("KYB_Array([");
        for(int i=0;i<14;i++)
            System.out.print(col_array[i] + "," );
        System.out.print("............");
        for(int i=col_array.length-1;i>col_array.length-15;i--)
            System.out.print(col_array[i] +",");
        System.out.print("])\n");
        System.out.println("************************************************************************");

        Object tmp[] = new Object[150];
        Integer t[] = new Integer[150];
        for(int i=0;i<tmp.length;i++)
            tmp[i] = random.nextInt(1200);
        for(int i=0;i<t.length;i++)
            t[i] = random.nextInt(1200);

        try{
            System.out.println(df1.newColumn("Yeni",tmp));
            System.out.println("******************************************************************");
            System.out.println(df1.newColumn("Yeni2",t));
            System.out.println("*******************************************************************");
        }
        catch (ColumnRangeException e){e.printStackTrace();}
        System.out.println(df1.info());
        System.out.println("***********************************************************************");
        System.out.println(df1.describe());
        System.out.println("**********************************************************************");
        System.out.println(df1.iloc(2,43));



    }

}
