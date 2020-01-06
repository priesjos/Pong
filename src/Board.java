//Board class is responsible for the overall game display,
//drawing game objects, handling input, and managing UI.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    final int WIDTH = 800;
    final int HEIGHT = 600;

    private final int EDGESPACE = 50;
    private final int DECORSIZE = 25;
    private int pScore = 0;

    public int getpScore() {
        return pScore;
    }

    public void setpScore(int pScore) {
        this.pScore = pScore;
    }

    public int getcScore() {
        return cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }

    private int cScore = 0;
    private int interval = 60;

    Paddle pPadoru;
    Paddle cPadoru;
    Ball ball;
    Timer timer;
    Game game;

    public Board(Game game){

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        pPadoru = new Paddle(this, game);
        cPadoru = new Paddle(this, game);
        ball = new Ball(this);
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void init(){
        pPadoru.setPosition(EDGESPACE, HEIGHT/2);
        cPadoru.setPosition(WIDTH - EDGESPACE, HEIGHT/2);
        ball.setPosition(WIDTH/2, HEIGHT/2);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font( "Serif", Font.BOLD, 36));
        printSimpleString(Integer.toString(pScore), getWidth()/2, 0, DECORSIZE*2, g);
        printSimpleString(Integer.toString(cScore), getWidth()/2, getWidth()/2, DECORSIZE*2, g);
        pPadoru.paint(g);
        cPadoru.paint(g);
        ball.paint(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
        ball.checkCollisions(pPadoru);
        ball.checkCollisions(cPadoru);
        pPadoru.move();
        cPadoru.move(ball);

        repaint();
    }

    private void printSimpleString(String s, int width, int xPos, int yPos, Graphics g){
        int stringLen = (int)g.getFontMetrics().getStringBounds(s, g).getWidth(); //length in pixels
        int start = width/2 - stringLen/2;
        g.drawString(s, start+xPos, yPos);
    }
}
