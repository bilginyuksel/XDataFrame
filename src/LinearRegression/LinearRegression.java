package LinearRegression;
import DataFrame.DataFrame;

public class LinearRegression {

    private DataFrame data,label;
    private double[] theta_vector;
    private Object[] features;
    private int lenght_data_columns,length_data_rows;

    //need edit hypothesis always returns 0.0 globals dont work.
    //needs whole check...

    //Algorithm Settings
    private double alpha = 0.01;
    private double tol = 1e-11;
    private int maxiter = 9000;
    private int iteration = 100;

    public LinearRegression(double learning_rate,int iteration){
        if(iteration>this.maxiter)
            System.out.println("Sorry maximum iteration: 9000");
        else this.iteration = iteration;
        this.alpha = learning_rate;
    }
    public LinearRegression(double learning_rate){this.alpha=learning_rate;}
    public LinearRegression(int iteration){if(iteration>this.maxiter) System.out.println("Sorry maximum iteration: 9000"); else this.iteration =iteration;}
    public LinearRegression(){}

    public void fit(DataFrame data,DataFrame label){
        this.data = data;
        this.label = label;

        this.features = label.array_from_column(0);
        this.lenght_data_columns = data.shape()[1]+1;
        this.length_data_rows = data.shape()[0];
        this.theta_vector = new double[lenght_data_columns];
        this.theta_vector[0] = 1;
        for(int i=1;i<lenght_data_columns;i++)
            this.theta_vector[i] = 0;
        gradient_descent();
    }

    public double[] predict(DataFrame test){
        int test_len = test.shape()[0];
        System.out.println("Test Length: " + test_len);
        double []predicts = new double[test_len];
        for(int i=0;i<test_len;i++){
            predicts[i] = hypothesis(test.row(i));
        }
        for(double d:predicts)
            System.out.println("Predicts : "+ d);
        return predicts;
    }



    private double hypothesis(Object[] row) {
        double hypothesis = 0;

        hypothesis += this.theta_vector[0];

        for (int i = 1; i < lenght_data_columns; i++) {
            hypothesis += this.theta_vector[i ] * Double.parseDouble(row[i].toString());
        }
        return hypothesis;
    }
    private double[] costFunction(){

        double[] theta_vector = new double[lenght_data_columns];
        for(int i=0;i<length_data_rows;i++)
            theta_vector[0] += (Double.parseDouble(features[i].toString())-hypothesis(this.data.row(i)));
        theta_vector[0] = -2*theta_vector[0]/length_data_rows;


        for(int j=0;j<lenght_data_columns-1;j++){
            Object [] row = this.data.row(j);
            for(int i=0;i<length_data_rows;i++){
                theta_vector[j+1]+=(Double.parseDouble(features[i].toString())-hypothesis(row))*Double.parseDouble(row[j+1].toString());
            }
            theta_vector[j+1] = -2*theta_vector[j+1]/length_data_rows;
        }
        this.theta_vector = theta_vector;
        return theta_vector;
    }
    private void gradient_descent(){
        double tmp[] = costFunction();
        while(iteration-->0){
            for(int i=0;i<lenght_data_columns;i++)
                this.theta_vector[i] -= alpha*tmp[i];
        }
        int i=0;
        for(double d: this.theta_vector)
            System.out.println("Theta"+i++ +" : "+d);
    }

}
