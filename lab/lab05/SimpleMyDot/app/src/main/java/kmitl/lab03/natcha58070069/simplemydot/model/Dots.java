package kmitl.lab03.natcha58070069.simplemydot.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nacha on 10-Sep-17.
 */

public class Dots implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    //INTERFACE & LISTENER
    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    //CREATE ARRAY LIST
    private List<Dot> allDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < allDot.size(); i++) {
            int centerX = (int) allDot.get(i).getCenterX();
            int centerY = (int) allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= 100) {
                return i;
            }
        }
        return -1;
    }

    public void undoDot() {
        if (allDot.size() > 0) {
            allDot.remove(allDot.size() - 1);
            this.listener.onDotsChanged(this);
        }
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void editDotAttr(int position,Dot dot){
        allDot.set(position, dot);
        this.listener.onDotsChanged(this);
    }
}
