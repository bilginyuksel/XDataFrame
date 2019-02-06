package LinearRegression;

public class SimpleLinearRegression {

    /*
    * this algorithm works with one to one should be one dimension data one dimension label
    *
    * Sample Using ----------------------------------------------------------------------
    * SimpleLinearRegression slr = new SimpleLinearRegression();
    * #auto alpha = 0.01 auto iteration = 100;
    * slr.fit(train_data,train_label);
    * double predict = slr.predict(test_data);
    * System.out.println("Simple Linear Regression model Predict : " + predict);
    *
    * -----------------------------------------------------------------------------------
    *
    * */

    private double Theta0;
    private double Theta1;
    private double alpha;
    private int iteration;
    private Double[] X,y;

    public SimpleLinearRegression(double alpha,int iteration){
        this.alpha = alpha;
        this.iteration = iteration;
        this.Theta1 = 0;
        this.Theta0 = 0;
    }
    public SimpleLinearRegression(double alpha){
        this.alpha = alpha;
        this.iteration = 100;
        this.Theta1 = 0;
        this.Theta0 = 0;
    }
    public SimpleLinearRegression(int iteration){
        this.iteration = iteration;
        this.alpha = 0.01;
        this.Theta1 = 0;
        this.Theta0 = 0;
    }
    public SimpleLinearRegression(){this.iteration = 100; this.alpha = 0.01;this.Theta1 = 0;this.Theta0 = 0;}

    private double hypothesis(Object x){
        return Theta0 + Theta1*Double.parseDouble(x.toString());
    }
    private double derive_theta0(){
        double sum = 0;
        for(int j=0;j<X.length;j++)
            sum+= y[j] -hypothesis(X[j]);

        return -2*sum/X.length;
    }
    private double derive_theta1(){
        double sum =0;

        for(int j=0;j<X.length;j++)
            sum+=(y[j]-hypothesis(X[j]))*X[j];

        return -2*sum/X.length;
    }

    public void fit(Object[] X,Object[] y){
        this.X = (Double[])X;
        this.y = (Double[])y;

        for(int i=0;i<iteration;i++){
            this.Theta0 -= alpha*derive_theta0();
            this.Theta1 -= alpha*derive_theta1();
        }
    }
    public double predict(Double X){
        return hypothesis(X);
    }
}
