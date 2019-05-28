package Snake;

import java.awt.*;

public class Berry extends Fruit{

    public Berry(int x, int y){
        this.location=new Point(x,y);
        this.score=15;
        this.tailLength=4;
        this.color= Color.CYAN;
        this.SCALE=15;
        this.moveHor=true;
        this.direction=1;
        this.speed=1;
        this.add_speed=true;
        this.FruitSpeed=1;
    }
}
