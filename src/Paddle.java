import java.awt.*;

public class Paddle {

    private int x, y;
    private final int WIDTH=20, HEIGHT=100, SPEED = 5;

    double dy = SPEED;

    Board board;
    Ball ball;
    Game game;

    public Paddle(Board board, Game game){
        x = 0;
        y = 300;
        this.board = board;
        this.game = game;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public void setPosition(int x, int y){
        this.x = x - WIDTH/2;
        this.y = y - HEIGHT/2;
    }

    public void move(){
        if(game.isUpPressed() && !(y <= 0)){
            y-=dy;
        }
        if(game.isDownPressed() && !(y+HEIGHT >= board.getHeight())){
            y+=dy;
        }
    }

    public void move(Ball ball){
        //setPosition(board.getWidth()-50, ball.getY());
        if (ball.getX() + ball.getDIAMETER() > board.getWidth() / 2){
            if (ball.getY() > y+HEIGHT/2)
                y+=dy;
            if (ball.getY() < y+HEIGHT/2)
                y-=dy;
        }
    }

    public void paint(Graphics g){
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
