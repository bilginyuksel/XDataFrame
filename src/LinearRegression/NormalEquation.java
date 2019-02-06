package LinearRegression;

import DataFrame.DataFrame;


public class NormalEquation {


    //you can write function for one hot encoding or dummies to dataframe class
    //functions like changes data labels type object to numerical

    /*
    * many to one
    * NormalEquation works with labeled data and labels should be numerical :
    * double or int doesnt matter but it should numerical
    *
    * Sample Using : ----------------------------------------------------------------
    *
    * NormalEquation n = new NormalEquation();
    * n.fit(train_data,train_labels);
    * double [] predicts = n.predict(test_data);
    *
    * for(int i = 0; i<predicts.length;i++){
    * System.out.println("Normal Equation Predict : " + predicts[i] );
    * }
    *
    * ------------------------------------------------------------------------------------
    *
    * */

    private DataFrame X,y;
    private int feature_size ;
    private int row_size ;
    private Object theta[];



    public NormalEquation(){
    }

    public void fit(DataFrame X,DataFrame y){
        /*
        * finds best coefficients theta
        * formula : theta = (Xtranspoze multiply X )inverse multiply transpozeX multiply label */
        this.X = X;
        this.y = y;
        this.feature_size = X.shape()[1];
        this.row_size = X.shape()[0];

        //theta0 involved.
        this.theta = new Object[feature_size+1];
        Object []tmp = y.array_from_column(0);
        Object[][] label = new Object[tmp.length][1];
        for(int i=0;i<tmp.length;i++)
            label[i][0] = tmp[i];
        Object [][] tmp_theta;
        tmp_theta = multiply(multiply(matrix_inverse(multiply(transpozed_X(),normal_X())),transpozed_X()),label);

        for(int i=0;i<tmp_theta.length;i++)
            this.theta[i] = tmp_theta[i][0];
        //print theta parameters
        System.out.println("Matris Theta");
        for(int i=0;i<theta.length;i++){
            System.out.println("Theta"+i+"  : "+theta[i] );
        }

    }

    public double[] predict(DataFrame X){
        /*
        * this function predicts the given DataFrame then returns the predicts array
        * */
        int test_row = X.shape()[0];
        double predicts[] = new double[test_row];
        for(int i=0;i<test_row;i++){
            Object tmp[] = X.row(i);
            double sum = Double.parseDouble(theta[0].toString());
            for(int j=0;j<tmp.length;j++){
                sum+= Double.parseDouble(tmp[j].toString())*Double.parseDouble(theta[j].toString());
            }
            predicts[i] = sum;
        }

        System.out.println("Normal Equation Predicts");
        for(int i=0;i<predicts.length;i++)
            System.out.println("Predict : " + predicts[i]);

        return predicts;
    }

    public void predict(Object [][]X){
        /*
        * it predicts the given 2D array */
        double sum = 0;
        sum = Double.parseDouble(theta[0].toString());
        for(int i=1;i<theta.length;i++){
            sum += Double.parseDouble(X[0][i-1].toString())*Double.parseDouble(theta[i].toString());
        }

        System.out.println("Predict Sum : " + sum);
    }



    private Object[][] normal_X(){

        Object[][] normal_X = new Object[row_size][feature_size+1];
        for(int i=0;i<row_size;i++)
            normal_X[i][0] = 1;

        for(int i=0;i<row_size;i++)
            for(int j=0;j<feature_size;j++)
                normal_X[i][j+1] = X.element(j,i);



        return normal_X;
    }
    private Object[][] transpozed_X(){


        Object[][] transpoze_X = new Object[feature_size+1][row_size];

        for(int i=0;i<row_size;i++)
            transpoze_X[0][i] = 1;

        for(int i=0;i<feature_size;i++)
            for(int j=0;j<row_size;j++)
                transpoze_X[i+1][j] = X.element(i,j);

        return transpoze_X;
    }

    public Object[][] multiply(Object[][] first,Object[][] second){

        /*
        * matrix multiplication... works for every kind of matrix
        * first matrix multiply second matrix.
        * if you are dealing with matrixes queue is important*/
        int first_row = first.length;
        int first_col = first[0].length;
        int second_col =second[0].length;

        Double [][] result = new Double[first_row][second_col];

        for(int i=0;i<first_row;i++){
            for(int j=0;j<second_col;j++){
                result[i][j] = 0.0;
                for(int k=0;k<first_col;k++){
                    result[i][j] += Double.parseDouble(first[i][k].toString())*Double.parseDouble(second[k][j].toString());
                }
            }
        }
        return result;
    }


    //finsih calculate adjoint for calculate determinant
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


    public Object[][] matrix_inverse(Object[][] matrix){
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
