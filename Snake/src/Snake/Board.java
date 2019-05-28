package Snake;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileNotFoundException;


public class Board implements ActionListener, KeyListener {
public static Board board;
public Snakee snakee=new Snakee();
public ArrayList<Fruit> Tab_fruit=new ArrayList<Fruit>();
public JFrame jFrame;
public RenderPanel renderPanel;
public Timer timer=new Timer(20,this);
public static final int UP=0, DOWN=1, LEFT=2,RIGHT=3,SCALE=17;
public int ticks=0,score,time;
public Random random;
public Dimension dim;
public boolean gameOver=false,move=true,paused=false,saveNick=false;
public HighScore highScore;



public Board(){
    highScore=new HighScore();
    saveNick=highScore.SavePlayerName();
    dim = Toolkit.getDefaultToolkit().getScreenSize();
    jFrame=new JFrame("Snake");
    jFrame.setVisible(true);
    jFrame.setResizable(false);
    jFrame.setSize(800,705);
    jFrame.setLocation(dim.width /2 - jFrame.getWidth()/2 , dim.height/2 - jFrame.getHeight()/2);
    jFrame.add(renderPanel=new RenderPanel());
    jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
    jFrame.addKeyListener(this);

    startGame();


}

public void startGame()
{
    snakee.defaulSnake();

    ticks=0;
    time=0;
    score=0;
    gameOver=false;
    paused=false;

    Tab_fruit.clear();
    random=new Random();
    Tab_fruit.add(new Apple(random.nextInt(46),random.nextInt(38)+1));
    Tab_fruit.add(new Orange(random.nextInt(46),random.nextInt(38)+1));
    Tab_fruit.add(new Berry(random.nextInt(46),random.nextInt(38)+1));

    timer.start();


}


    @Override
    public void actionPerformed(ActionEvent e)  {
renderPanel.repaint();
ticks++;


if(ticks%snakee.speed==0 && snakee.head!=null && !gameOver && !paused && saveNick) {
    time++;
    snakee.snakeSegments.add(new Point(snakee.head.x,snakee.head.y));
    if (snakee.direction == UP) {
        if (snakee.head.y - 1 >= 1 && OrCollision(snakee.head.x,snakee.head.y-1)){
            snakee.head = new Point(snakee.head.x, snakee.head.y - 1); move=true;}
        else
            gameOver=true;
    }
    if (snakee.direction == DOWN) {
        if (snakee.head.y + 1 < 40 && OrCollision(snakee.head.x,snakee.head.y+1)){
            snakee.head = new Point(snakee.head.x, snakee.head.y + 1);move=true;}
        else
            gameOver=true;
    }
    if (snakee.direction == LEFT) {
        if (snakee.head.x - 1 >= 0 && OrCollision(snakee.head.x-1,snakee.head.y)){
            snakee.head = new Point(snakee.head.x - 1, snakee.head.y);move=true;}
        else
            gameOver=true;
    }
    if (snakee.direction == RIGHT){
       if(snakee.head.x+1<47 && OrCollision(snakee.head.x+1,snakee.head.y)){
           snakee.head = new Point(snakee.head.x + 1, snakee.head.y);move=true;}
       else
           gameOver=true;
    }


    if (snakee.snakeSegments.size() > snakee.tailLength)
    {
        snakee.snakeSegments.remove(0);
    }



    if(!Tab_fruit.isEmpty()){
        for(Fruit fruit : Tab_fruit){

            if(snakee.head.equals(fruit.location) || !OrCollisionFruit(fruit.location.x,fruit.location.y)){
                if(snakee.speed==3) score += fruit.score;
                else score=score + (fruit.score*5);

                snakee.tailLength+=fruit.tailLength;
                if(fruit.add_speed) {
                    snakee.speed = fruit.speed;
                    ticks=1;
                }
                fruit.location.setLocation(random.nextInt(46),random.nextInt(38)+1);
            }
                if(fruit.moveVer && ticks%fruit.FruitSpeed==0){
                    if(fruit.location.x+1<=46 && fruit.direction==RIGHT){
                        fruit.location.x+=1;
                    }
                    else fruit.direction=LEFT;

                    if(fruit.location.x-1>=0 && fruit.direction==LEFT){
                        fruit.location.x-=1;
                    }
                    else fruit.direction=RIGHT;
                }

                if(fruit.moveHor && ticks%fruit.FruitSpeed==0){
                if(fruit.location.y+1<=39 && fruit.direction==DOWN){
                    fruit.location.y+=1;
                }
                else fruit.direction=UP;

                if(fruit.location.y-1>=1 && fruit.direction==UP){
                    fruit.location.y-=1;
                }
                else fruit.direction=DOWN;
            }
        }


    }
    highScore.NewHighScore=score;
    highScore.WasHeBetter();
if(ticks%100==0)
    snakee.speed=3;
}

}
    public boolean OrCollision(int x,int y){

    for(Point point : snakee.snakeSegments){
        if(point.equals(new Point(x,y)))
        return false;
    }

    return true;
    }

    public boolean OrCollisionFruit(int x,int y){

        if(snakee.snakeSegments.size()>1)
        for(int i=snakee.snakeSegments.size()-1; i>snakee.snakeSegments.size()-3;i--){
            if(snakee.snakeSegments.get(i).equals(new Point(x,y)))
            {
            return false;
            }
        }
              return true;

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(move) {
            if (i == KeyEvent.VK_LEFT && snakee.direction != RIGHT)
                snakee.direction = LEFT;
            if (i == KeyEvent.VK_RIGHT && snakee.direction != LEFT)
                snakee.direction = RIGHT;
            if (i == KeyEvent.VK_UP && snakee.direction != DOWN)
                snakee.direction = UP;
            if (i == KeyEvent.VK_DOWN && snakee.direction != UP)
                snakee.direction = DOWN;
            move=false;
        }
        if (i == KeyEvent.VK_SPACE) {
            if(gameOver)
                startGame();
            else
                paused=!paused;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



    public static void main(String[] args)throws FileNotFoundException{
        board = new Board();
}

    }

