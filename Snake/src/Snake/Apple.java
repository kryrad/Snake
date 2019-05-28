package Snake;

import java.awt.*;

public class Apple extends Fruit{



    public Apple(int x, int y){
        this.location=new Point(x,y);
        this.score=6;
        this.tailLength=3;
        this.color= Color.RED;
        this.SCALE=16;

    }

}
