package Snake;

import java.awt.*;

public class Orange extends Fruit {

    public Orange(int x, int y){
        this.location=new Point(x,y);
        this.score=10;
        this.tailLength=5;
        this.color= Color.ORANGE;
        this.SCALE=10;
        this.moveVer=true;
        this.direction=3;
        this.FruitSpeed=8;
    }
}
