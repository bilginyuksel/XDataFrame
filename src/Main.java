import DataFrame.DataFrame;
import DataFrameException.ColumnElementTypeException;
import DataFrameException.ColumnRangeException;
import KNN.KNNClassifier;
import LinearRegression.LinearRegression;
import LinearRegression.NormalEquation;
import LinearRegression.SimpleLinearRegression;
import jdk.swing.interop.SwingInterOpUtils;

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
        System.out.println("Get Single Element From DataFrame.DataFrame ");
        System.out.println("Element : " + element);
        System.out.println("Element Type : " + element.getClass().getSimpleName());
        System.out.println("************************************************************************");
        try {
            df1.changeElement(1,2,3.22);
        }catch (ColumnElementTypeException e) {e.printStackTrace();}

        Object newElement = df1.element(1,2);
        System.out.println("Get New Element From DataFrame.DataFrame");
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
        System.out.println("Shape of Rows : " + df1.shape()[0]);
        System.out.println("Shape of Columns : " + df1.shape()[1]);
        Object column_names[] = df1.column_names();
        for(Object o: column_names)
            System.out.println("Column Name : " + o);
        System.out.println("*************************************************************************");
        Object []unique_col = df1.unique("Species");
        System.out.println("Unique Keys ");
        for(Object o : unique_col)
            System.out.print(o +",");
        System.out.println("\n****************************************************************************");
        System.out.println(df1.iloc(2,43));


        System.out.println("*********************************************************************************");
        KNNClassifier knn = new KNNClassifier(3);
        DataFrame df3 = new DataFrame();
        DataFrame df5 = df3.read_csv("iris.csv");
        knn.fit(df3.loc(1,5),df5.column("Species"));
        Object [][] test = new Object[1][4];
        test[0][0] =5.9;
        test[0][1] =3.0;
        test[0][2] = 5.1;
        test[0][3] = 1.8;

        DataFrame df4 = new DataFrame();
        for(Object o : knn.predict(df4.arrayToDataFrame(test)))
            System.out.println("Knn Predicts :" + o);


        Object[][] bayes =new Object[5][5];
        for(int i=0;i<bayes.length;i++){
            for(int j=0;j<bayes[i].length;j++){
                bayes[i][j] = random.nextInt(100);
            }
        }
        int[][] omg = new int[150][1];
        for(int i=0;i<50;i++)
            omg[i][0] = 1;
        for(int i=50;i<100;i++)
            omg[i][0]=2;
        for(int i=100;i<150;i++)
            omg[i][0]=3;

        System.out.println("***************************************************************");


        Double X[] = {5.21,3.32,6.0,4.0,8.21,10.32,5.2,5.3,11.0};
        Double y[] = {1.60,0.80,1.80,1.30,2.50,3.0,1.6,1.63,4.0};

        SimpleLinearRegression s = new SimpleLinearRegression();
        s.fit(X,y);
        System.out.println("Simple Linear Regression");
        System.out.println("Predicted Value : "+ s.predict(5.21));
        System.out.println("Real Value : " + y[0]);

        System.out.println("**********************************************************");
        LinearRegression lr = new LinearRegression(0.01,1000);
        lr.fit(df3,df4.arrayToDataFrame(omg));
        lr.predict(df4.arrayToDataFrame(test));

        System.out.println("**************************************************************");

        NormalEquation n = new NormalEquation();
        Integer[][] xx = new Integer[2][4];
        Integer[][] yy = new Integer[4][1];
        for(int i=0;i<xx.length;i++)
            for(int j=0;j<xx[0].length;j++)
                xx[i][j] = random.nextInt(5);


        for(int i=0;i<yy.length;i++)
            for(int j=0;j<yy[0].length;j++)
                yy[i][j] = random.nextInt(5);

        Object[][] zz = n.multiply(xx,yy);
        System.out.println("XX Matrix");
        for(int i=0;i<xx.length;i++){
            for(int j=0;j<xx[0].length;j++){
                System.out.print(xx[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("YY Matrix");
        for(int i=0;i<yy.length;i++){
            for(int j=0;j<yy[0].length;j++){
                System.out.print(yy[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("ZZ Matrix");
        for(int i=0;i<zz.length;i++){
            for(int j=0;j<zz[0].length;j++){
                System.out.print(zz[i][j]+"  ");
            }
            System.out.println();
        }
        n.fit(df3,new DataFrame().arrayToDataFrame(omg));
        n.predict(new DataFrame().arrayToDataFrame(test));
        n.predict(test);
    }

}
