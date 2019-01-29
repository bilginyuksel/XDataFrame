package DataFrame;

import java.util.ArrayList;

public class Column {


    private ArrayList elements;
    private String feature_name;
    private String type;


    protected Column(String feature_name,int[] params){
        this.feature_name = feature_name;
        ArrayList<Integer> i = new ArrayList<>();
        for(int p : params)
            i.add(p);

        this.elements = i;
        this.type = "int64";
    }
    protected Column(String feature_name,Object[] params){
        this.feature_name = feature_name;
        ArrayList<Object> obj = new ArrayList<>();
        for(Object o:params)
            obj.add(o);

        this.elements = obj;
        this.type = "object";
    }
    protected Column(String feature_name,double[] params){
        ArrayList<Double> d = new ArrayList<>();
        this.feature_name = feature_name;
        for(double p:params)
            d.add(p);

        this.elements =d;
        this.type = "double";
    }
    protected Column(String feature_name,String[] params){
        ArrayList<String> temp = new ArrayList<>();
        this.feature_name = feature_name;
        for(String s:params)
            temp.add(s);

        this.elements = temp;
        this.type = "String";
    }


    protected String getType(){return this.type;}
    protected ArrayList getElements() { return elements; }
    protected int size() { return elements.size(); }
    protected String getFeature_name() { return feature_name; }
    protected double min(){
        if(type == "object")
            return 0;

        double min=1000000;
        for(int i=0;i<elements.size();i++){
            if(min>Double.valueOf(elements.get(i).toString()))
                min = Double.valueOf(elements.get(i).toString());
        }
        return min;
    }
    protected double max(){
        if(type == "object")
            return 0;

        double max = 0;
        for(int i=0;i<elements.size();i++){
            if(max<Double.valueOf(elements.get(i).toString()))
                max = Double.valueOf(elements.get(i).toString());
        }
        return max;
    }
    protected double Q1(){
        if(type == "object") return 0;
        int size = elements.size();

        if(size%2 ==0) return (Double.parseDouble(elements.get(size/4).toString()) + Double.parseDouble(elements.get((size/4)+1).toString()))/2;
        else return Double.parseDouble(elements.get((size+1)/4).toString());
    }
    protected double Q2(){
        if(type == "object") return 0;
        int size =elements.size();

        if(size%2 == 0) return (Double.parseDouble(elements.get(size/2).toString())+Double.parseDouble(elements.get((size/2)+1).toString()))/2;
        else return Double.parseDouble(elements.get((size+1)/2).toString());
    }
    protected double Q3(){
        if(type == "object") return 0;
        int size = elements.size();

        if(size%2 ==0) return (Double.parseDouble(elements.get(3*size/4).toString()) + Double.parseDouble(elements.get((3*size/4)+1).toString()))/2;
        else return Double.parseDouble(elements.get(((3*size)+1)/4).toString());
    }
    protected void setFeature_name(String feature_name) { this.feature_name = feature_name; }

    public String toString(){
        String obj = this.feature_name +"\n";
        String ch = " ";
        for(Object s : this.elements)
            obj += s + ch.repeat(16-s.toString().length()) +"\n";
        return obj ;
    }
}
