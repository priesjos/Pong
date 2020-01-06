import java.awt.*;

public class Ball {

    private int x, y;
    private final int DIAMETER = 8;
    private final int SPEED = 4;

    final double MAXANGLE = 5*Math.PI/12;
    double dx = SPEED, dy = SPEED;

    Board board;
    Paddle paddle;

    public Ball(Board board){
        x = 0;
        y = 0;

        //'this' keyword references the object that is executing or calling the 'this' reference
        this.board = board;
    }

    public void move(){

        //Collision with the board edges
        if (x <= 0) {
            dx*=-1;
            board.setcScore(board.getcScore() + 1);
        }
        if (x+DIAMETER >= board.getWidth()){
            dx*=-1;
            board.setpScore(board.getpScore()+1);
        }
        if(y <= 0 || y+DIAMETER >= board.getHeight())
            dy*=-1;

        x+=dx;
        y+=dy;
    }

    public void setPosition(int x, int y){
        this.x = x - DIAMETER/2;
        this.y = y - DIAMETER/2;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,DIAMETER,DIAMETER);
    }

    public void checkCollisions(Paddle other){
        if (getBounds().intersects(other.getBounds())){
            //bug here; the ball gets caught on the paddle if hit from the sides
            if (x < board.getWidth()/2){
                dx *= -1;
            }
            if (x > board.getWidth()/2){
                dx *= -1;
            }
            //the following code has issues on the edges of the paddle
            /*
            double paddleY = other.getBounds().getY();
            double paddleC = other.getBounds().getHeight()/2;
            double ballY = y;

            double relativeIntersect = (paddleY + paddleC) - ballY;
            double normalIntersect = relativeIntersect/paddleC;
            double bounceAngle = MAXANGLE + normalIntersect;

            if (y+(DIAMETER/2)< paddleY){
                ballY = y+DIAMETER;
            }
            else if (y + DIAMETER/2 > paddleY + other.getBounds().getHeight()){
                ballY = y;
            }
            else
                ballY = y+DIAMETER;

            if (x < board.getWidth()/2){ //left paddle
                dx = (int)(SPEED * Math.cos(bounceAngle));
            }

            if (x > board.getWidth()/2){ //right paddle
                dx = (int)(SPEED * -Math.cos(bounceAngle));
            }
            dy = (int)(SPEED*-Math.sin(bounceAngle));
            */
        }
    }

    public void paint(Graphics g){
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDIAMETER() {
        return DIAMETER;
    }

    public int getSPEED() {
        return SPEED;
    }
}
