package Application;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Palette {
    Color[] palette;
    int pointer;

    public Palette() {
        pointer =-1;
        palette = new Color[6];
//        for (int i = 0; i < 6; i++) {
//            int red = (int) (Math.random()*256);
//            int green = (int) (Math.random()*256);
//            int blue = (int) (Math.random()*256);
////            Color customColor = Color.WHITE;
//            Color customColor = Color.rgb(red,green,blue);
//            palette[i]= customColor;
//        }
        palette[0]= Color.AQUAMARINE;
        palette[1] = Color.CHARTREUSE;
        palette[2] = Color.DARKORANGE;
        palette[3] = Color.GREENYELLOW;
        palette[4] = Color.ORANGERED;
        palette[5] = Color.YELLOW;






    }

    public Color nextColor()
    {
        pointer= (pointer+1)%6;
        return palette[pointer];
    }
}
