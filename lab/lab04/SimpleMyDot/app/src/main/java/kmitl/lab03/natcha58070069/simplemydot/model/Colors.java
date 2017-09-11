package kmitl.lab03.natcha58070069.simplemydot.model;

import android.graphics.Color;
import java.util.Random;

/**
 * Created by Nacha on 10-Sep-17.
 */

public class Colors {

    Random random = new Random();

    private int red;
    private int green;
    private int blue;

    public Colors() {
        red = random.nextInt(255);
        green = random.nextInt(255);
        blue = random.nextInt(255);
    }
    public int getColor() {
        return Color.rgb(red, green, blue);
    }
}
