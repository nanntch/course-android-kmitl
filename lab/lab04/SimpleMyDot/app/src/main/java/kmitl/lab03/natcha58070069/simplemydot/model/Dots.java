package kmitl.lab03.natcha58070069.simplemydot.model;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;

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

    public void changeSize(int position, int radius){
        allDot.get(position).setRadius(radius);
        this.listener.onDotsChanged(this);
    }

    public void changeColor(int position){
        allDot.get(position).setColor(new Colors().getColor());
        this.listener.onDotsChanged(this);
    }

    public void editDot(Context context, final int position, final int radius){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new CharSequence[]{"Size", "Color", "Remove"},
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                changeSize(position, radius);
                                dialog.dismiss();
                                break;
                            case 1:
                                changeColor(position);
                                dialog.dismiss();
                                break;
                            case 2:
                                removeBy(position);
                                dialog.dismiss();
                                break;
                        }
                    }
                });
        builder.show();
    }
}
