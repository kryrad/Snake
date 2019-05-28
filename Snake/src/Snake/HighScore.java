package Snake;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
public class HighScore {
    public int OldHighScore;
    public String OldplayerName;
    public int NewHighScore=0;
    public String NewplayerName;
    CreateTextBox textBox;

    HighScore(){
        LoadHighScore();
    }


    public void LoadHighScore(){

        try {
            File file = new File("/Users/krystianrodzaj/IdeaProjects/Snake/src/Snake/highScore.txt");
            Scanner in = new Scanner(file);
            this.OldplayerName= in.nextLine();
            this.OldHighScore=in.nextInt();
        } catch (FileNotFoundException f) {

        }

    }

    public void SaveHighScore(int HighScore,String playerName){
        try {
            PrintWriter zapis = new PrintWriter("/Users/krystianrodzaj/IdeaProjects/Snake/src/Snake/highScore.txt");
            zapis.println(playerName);
            zapis.println(HighScore);
            zapis.close();
        }
        catch(FileNotFoundException f){}
    }

    public void WasHeBetter() {
        if(this.NewHighScore > this.OldHighScore)
        {
            this.OldHighScore=this.NewHighScore;
            this.OldplayerName=this.NewplayerName;
            SaveHighScore(this.OldHighScore,this.OldplayerName);
        }


    }

    public boolean SavePlayerName() {
        textBox = new CreateTextBox();
        while (this.NewplayerName == null) {
            this.NewplayerName = textBox.name;
        }
        textBox.dispose();
    return true;
    }


}
