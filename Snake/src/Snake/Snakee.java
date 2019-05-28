package Snake;

import java.awt.*;
import java.util.ArrayList;

public class Snakee {
    public ArrayList<Point> snakeSegments=new ArrayList<Point>();
    public int direction,tailLength;
    public Point head;
    public int speed;

    Snakee(){
        this.direction=1;
        this.tailLength=6;
        this.head=new Point(10,10);
        this.speed=3;
    }
    
    void defaulSnake(){
        this.snakeSegments.clear();
        this.head=new Point(10,10);
        this.tailLength=6;
        this.direction=1;
        this.speed=3;

    }

}
