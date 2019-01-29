package KNN;
import DataFrame.DataFrame;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KNNClassifier {

    private DataFrame data,label;
    private int k_NN;
    private double distances[];
    private int indexes_of_distances[];

    public KNNClassifier(int k_NN){
        this.k_NN =k_NN;
    }

    public void fit(DataFrame X,DataFrame y){
        this.data = X;
        this.label = y;
    }

    public Object[] predict(DataFrame test){
        /*
        * Predict method of knn class. First of all finds distances beetween test set and all train set with euclidian Distance function
        * After finding distances sorts the distances. And finds neigbors according to K-nearest-neighbor number.Then calculates the
        * weight of which class has high rate. After that process returns the answer.
        * */
        distances = euclidianDistance(test);
        indexes_of_distances= new int[distances.length];
        for(int i=0;i<distances.length;i++)
            indexes_of_distances[i] = i;

        sort(distances,indexes_of_distances);

        Object[] neighbors = new Object[k_NN];
        //After sorting all find number of neighbors
        for(int i=0;i<k_NN;i++)
            neighbors[i] = this.label.element(0,indexes_of_distances[i]);

        for(Object o : neighbors)
            System.out.println("Neigbors :" + o);

        Set<Object> obj_set = new HashSet<>(Arrays.asList(neighbors));
        int size_of_unique_keys = obj_set.size();
        Object[]unique = obj_set.toArray();
        int []counts = new int[size_of_unique_keys];
        int tmp_count = 0;


        int j=0;
        Object tmp = null;
        while(j<size_of_unique_keys){
            tmp = unique[j];
            for(int i=0;i<neighbors.length;i++){
                if(tmp == neighbors[i]) tmp_count++;
            }
            counts[j++]=tmp_count;
            tmp_count = 0;
        }

        int max=-1;
        for(int i=0;i<size_of_unique_keys;i++){
            if(counts[i]>max){
                max = counts[i];
                tmp = unique[i];
            }
        }

        //find neighbors weight
        //Write unique function for label type count
        Object predictions[] = new Object[test.shape()[0]];
        predictions[0] = tmp;

        return predictions;
    }

    private double[] euclidianDistance(DataFrame test){
        //Test and Data's shape have to equal
        /*
        * finds euclidian distance beetween knn's data and parameter test.then returns the result array.*/

        int sizeOfFeatures = this.data.shape()[1];
        int sizeOfRows = this.data.shape()[0];
        double tmp =0;
        double results[] = new double[sizeOfRows];

        System.out.println("Size of Features of Data : " +sizeOfFeatures);
        System.out.println("Size of Rows of Data : " + sizeOfRows);
        System.out.println(test);
        //size of rows big for
        for(int i=0;i<sizeOfRows;i++){
            //Size of features inner for
            for(int j=0;j<sizeOfFeatures;j++){

               tmp += Double.parseDouble(this.data.element(j,i).toString())-Double.parseDouble(test.element(j,0).toString());
            }
            tmp = Math.sqrt(tmp) ;
            results[i] = tmp;
            tmp =0;
        }


        return results;
    }

    private void sort(double[] dist,int[] indexes){
        //Sort these two then its ending
        //Sorts the distances and indexes according to distances for finding which row index is the smallest
        int min_index,length = dist.length;
        for(int i=0;i<length-1;i++){
            min_index = i;
            for(int j=i;j<length;j++){
                if(dist[min_index]>dist[j])
                    min_index = j;
            }
            double tmp = dist[i];
            dist[i] = dist[min_index];
            dist[min_index] = tmp;
            int tmp_indexes = indexes[i];
            indexes[i] = indexes[min_index];
            indexes[min_index] = tmp_indexes;
        }
        this.indexes_of_distances = indexes;
        this.distances = dist;
    }

}
