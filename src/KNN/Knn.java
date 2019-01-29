package KNN;
import DataFrame.DataFrame;
import DataFrameException.ColumnRangeException;

public class Knn {

    private DataFrame data,label;
    private int k_NN;
    private double distances[];
    private int indexes_of_distances[];

    public Knn(int k_NN){
        this.k_NN =k_NN;
    }

    public void fit(DataFrame X,DataFrame y){
        this.data = X;
        this.label = y;
    }

    public Object[] predict(DataFrame test){
        distances = euclidianDistance(test);
        indexes_of_distances= new int[distances.length];
        for(int i=0;i<distances.length;i++)
            indexes_of_distances[i] = i;

        sort(distances,indexes_of_distances);

        Object[] neighbors = new Object[k_NN];
        //After sorting all find number of neighbors
        for(int i=0;i<k_NN;i++)
            neighbors[i] = this.label.element(0,indexes_of_distances[i]);

        //Write unique function for label type count
        Object predictions[] = new Object[test.shape()[0]];

        return predictions;
    }

    private double[] euclidianDistance(DataFrame test){
        //Test and Data's shape have to equal
        int sizeOfFeatures = this.data.shape()[1];
        int sizeOfRows = this.data.shape()[0];
        double tmp =0;
        double results[] = new double[sizeOfRows];

        //size of rows big for
        for(int i=0;i<sizeOfRows;i++){
            //Size of features inner for
            for(int j=0;j<sizeOfFeatures;j++){
               tmp += Double.parseDouble(this.data.element(i,j).toString())-Double.parseDouble(test.element(i,j).toString());
            }
            tmp = Math.sqrt(tmp) ;
            results[i] = tmp;
            tmp =0;
        }


        return results;
    }

    public void sort(double[] dist,int[] indexes){
        //Sort these two then its ending
    }

}
