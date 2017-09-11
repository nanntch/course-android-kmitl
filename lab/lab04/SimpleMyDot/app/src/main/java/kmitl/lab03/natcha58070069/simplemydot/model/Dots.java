package kmitl.lab03.natcha58070069.simplemydot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nacha on 10-Sep-17.
 */

public class Dots {

    //INTERFACE & LISTENER
    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener){
        this.listener = listener;
    }

    //CREATE ARRAY LIST
    private List<Dot> allDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot){
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearAll(){
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y){
        for (int i=0; i<allDot.size(); i++){
            int centerX = (int) allDot.get(i).getCenterX();
            int centerY = (int) allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= 100){
                return i;
            }
        }
        return -1;
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }
}
