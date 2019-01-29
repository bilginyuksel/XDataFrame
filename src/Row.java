import java.util.ArrayList;

public class Row {

    private ArrayList row;
    static int i=-1;


    protected Row(Object... params ){
        ArrayList<Object> tmp = new ArrayList<>();
        if (i==-1){tmp.add("");i++;}
        else
            tmp.add(i++);
        for(Object o : params){
            tmp.add(o);
        }
        this.row = tmp ;

    }
    protected Row(){ this.i =-1; }

    public ArrayList getRow() { return row; }

    @Override
    public String toString() {
        String obj = "";
        String ch = " ";
        for(Object s : row)
            obj += s.toString() + ch.repeat(16-s.toString().length());
        return obj ;
    }
}
