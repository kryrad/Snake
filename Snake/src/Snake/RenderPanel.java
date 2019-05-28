package Snake;

import javax.swing.*;
import java.awt.*;




public class RenderPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Board board=Board.board;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,board.SCALE,800,700);
        g.setColor(Color.GREEN);


        for(Point point: board.snakee.snakeSegments){

            g.fillRect(point.x *board.SCALE, point.y*board.SCALE,board.SCALE,board.SCALE);
        }

        for(Fruit fruit: board.Tab_fruit){

            g.setColor(fruit.color);
            g.fillOval(fruit.location.x*board.SCALE,fruit.location.y*board.SCALE,fruit.SCALE,fruit.SCALE);
        }

        String string = "Score: " + board.score + ", Length: " + board.snakee.tailLength + ", Time: " + board.time / 20 +", Best score: "+ board.highScore.OldHighScore +
                ", Best player: " + board.highScore.OldplayerName;

        g.setColor(Color.BLACK);

        g.drawString(string, (int) (getWidth() / 2 - string.length() * 3.0f), 14);

        string = "Game Over!";
        g.setColor(Color.white);
        if (board.gameOver)
        {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 3.0f), (int) board.dim.getHeight() / 4);
        }

        string = "Paused!";

        if (board.paused && !board.gameOver)
        {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 3.0f), (int) board.dim.getHeight() / 4);
        }
    }
}
