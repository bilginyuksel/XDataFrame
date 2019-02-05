package LinearRegression;

import DataFrame.DataFrame;

public class NormalEquation {
    private DataFrame X,y;
    private int feature_size ;
    private int row_size ;

    public NormalEquation(DataFrame X,DataFrame y,Object[][] matrix){
        this.X = X;
        this.y = y;
        this.feature_size = X.shape()[1];
        this.row_size = X.shape()[0];

    }



    private Object[][] normal_X(){

        Object[][] normal_X = new Object[row_size][feature_size];
        for(int i=0;i<feature_size;i++)
            normal_X[0][i] = 1;

        for(int i=1;i<row_size;i++)
            for(int j=0;j<feature_size;j++)
                normal_X[i][j] = X.element(j,i);

        System.out.println("Normal X");
        for(int i=0;i<normal_X.length;i++){
            for(int j=0;j<normal_X[i].length;j++){
                System.out.print(normal_X[i][j] + "  ");
            }
            System.out.println();
        }


        return normal_X;
    }
    private Object[][] transpozed_X(){


        Object[][] transpoze_X = new Object[feature_size][row_size];

        for(int i=0;i<feature_size;i++)
            transpoze_X[i][0] = 1;

        for(int i=0;i<feature_size;i++)
            for(int j=1;j<row_size;j++)
                transpoze_X[i][j] = X.element(i,j);

        System.out.println("Transpoze X");
            for(int i=0;i<transpoze_X.length;i++){
                for(int j=0;j<transpoze_X[i].length;j++){
                    System.out.print(transpoze_X[i][j] +"  ");
                }
                System.out.println();
            }
        return transpoze_X;
    }


    private Object[][] calculate_adjoint(int threshold_row,int threshold_column,Object[][] matrix){
        int matrix_row_len = matrix.length;
        int matrix_col_len = matrix[0].length;
        Object [][] tmp = new Object[matrix_row_len-1][matrix_col_len-1];
        System.out.println("(" +threshold_row +" X "+threshold_column+  ") Small MAtrix");
        int row_c=0,col_c=0 ;
        for(int i=0;i<matrix_row_len;i++){
            if(threshold_row==i){i++;}
            if(i==matrix_row_len) break;
            for(int j=0;j<matrix_col_len;j++){
                if(threshold_column==j) {j++;}
                if(j==matrix_col_len) break;
                tmp[row_c][col_c++] = matrix[i][j];
            }
            row_c++;
            col_c=0;
        }


        //Prints the adjoint matrix
        for(int i=0;i<tmp.length;i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                System.out.print( tmp[i][j] + "   ");
            }
            System.out.println();
        }
        return tmp;
    }


    private Object[][] matrix_inverse(Object[][] matrix){
        /*
        * matrix inverse with gauss-jordan elimination method
        * for nxn matrix*/
        Object[][] matrixI = new Object[matrix.length][matrix.length];

        //identity matrix creation
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix.length;j++)
                if(i==j) matrixI[i][j]=1;
                else matrixI[i][j]=0;


        double d,k;
        for(int i=0;i<matrix.length;i++){
            d = Double.parseDouble(matrix[i][i].toString());
            for(int j=0;j<matrix.length;j++){
                matrix[i][j] = Double.parseDouble(matrix[i][j].toString())/d;
                matrixI[i][j] = Double.parseDouble(matrixI[i][j].toString())/d;
            }
            for(int x=0;x<matrix.length;x++){
                if(x!=i){
                    k = Double.parseDouble(matrix[x][i].toString());
                    for(int j=0;j<matrix.length;j++){
                        matrix[x][j] = Double.parseDouble(matrix[x][j].toString())-(Double.parseDouble(matrix[i][j].toString())*k);
                        matrixI[x][j] = Double.parseDouble(matrixI[x][j].toString())-(Double.parseDouble(matrixI[i][j].toString())*k);
                    }
                }
            }
        }

        return matrixI;
    }

}
