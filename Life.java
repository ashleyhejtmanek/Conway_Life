import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * A class that represents a (finite) board for Conway's Life.
 * 
 * @author Ashley Hejtmane and David Brookshier and Daniel Feldman
 */

public class Life extends JPanel {

    // The delay between frames of the animation, in milliseconds. You
    // can change this if you want it to run faster or slower.
    public final static int ANIMATION_DELAY = 200;

    // The size of one cell one the screen, in pixels. You can make this
    // smaller if you make a big board.
    public final static int CELL_SIZE = 10; //(int)(Math.random()*20);

    // Width and height of the board, in cells.
    public final static int BOARD_WIDTH = 780/CELL_SIZE;
    public final static int BOARD_HEIGHT = 780/CELL_SIZE;

    //Here the board is intialized
    private int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];

    /**
     * A constructor for a new Life game
     * 
     * This will start the game with a random pattern
     */
    public Life()
    {
        fillRandom();
        //To test the makeStillLife, makeBlinker, and makeGlider remove //
        // makeStillLife(2,2);
        // makeBlinker(2,20);
        // makeGlider(10,15);
        //for(int i = 0; i < 7; i++){
          //  for(int j = 0; j < 7; j++){
            //    makePulsar(7 + i*16, 7 + j*16);}}
    }

    /**
     * Returns whether or not a cell is alive.
     * @param x the x coord of the cell
     * @param y the y coord of the cell
     * @return whether the cell is alive or not
     */
    public boolean isAlive(int x, int y)
    {
        if(x < 0 || x >= BOARD_WIDTH){
            return false;
        }
        if(y < 0 || y >= BOARD_HEIGHT){
            return false;
        }
        if(board[x][y] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Updates the board for the next generation
     */
    public void nextGeneration()
    {
        //Creates intermediate board
        int[][] nextBoard = new int[BOARD_WIDTH][BOARD_HEIGHT];

        //Clears the intermediate board
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                nextBoard[i][j] = 0;
            }
        }

        //updates the indtermediate board
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(countNeighbors(i,j) == 3){
                    nextBoard[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(isAlive(i,j) == true){
                    if(countNeighbors(i,j) == 2){
                        nextBoard[i][j] = 1;
                    }
                }
            }
        }

        //updates board
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                board[i][j] = nextBoard[i][j];
            }
        }
    }

    /**
     * Returns the number of living neighbors next to a cell.
     * @param x the x coord of the cell
     * @param y the y coord of the cell
     * @return the number of living neighbors next to the cell
     */
    public int countNeighbors(int x, int y)
    {
        int counter = 0;

        if(isAlive(x - 1,y) == true){
            counter++;
        }
        if(isAlive(x - 1, y - 1) == true){
            counter++;
        }
        if(isAlive(x,y-1) == true){
            counter++;
        }
        if(isAlive(x+1,y-1) == true){
            counter++;
        }
        if(isAlive(x+1,y) == true){
            counter++;
        }
        if(isAlive(x+1,y+1) == true){
            counter++;
        }
        if(isAlive(x,y+1) == true){
            counter++;
        }
        if(isAlive(x-1,y+1) == true){
            counter++;
        }

        return counter;
    }

    /**
     * This method randomly fills the board with live cells.
     */
    public void fillRandom()
    {
        Random random = new Random();
        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_HEIGHT; j++){
                if(random.nextBoolean()){
                    board[i][j] = 1;
                }
                else{
                    board[i][j] = 0;
                }
            }
        }
    }

    /**
     * Method makeStillLife makes an example still life like from the wiki.
     *
     * @param x gives the x coordinate
     * @param y gives the y coordinate
     */
    public void makeStillLife(int x, int y){
        board[x][y] = 1;
        board[x - 1][y] = 1;
        board[x - 1][y -1] = 1;
        board[x][y - 1] = 1;
    }

    /**
     * Method makeBlinker makes a blinker like from the wiki.
     *
     * @param x gives the x coordinate
     * @param y gives the y coordinate
     */
    public void makeBlinker(int x, int y){
        board[x][y] = 1;
        board[x-1][y] = 1;
        board[x+1][y] = 1;
    }

    /**
     * Method makeVBlinker gives a vertical blinker
     *
     * @param x A parameter giving the x coordinate
     * @param y A parameter giving the y coordinate
     */
    public void makeVBlinker(int x, int y){
        board[x][y -1] = 1;
        board[x][y] = 1;
        board[x][y + 1] = 1;
    }

    /**
     * Method makeGlider makes two gliders like from the wiki which will eventually collide.
     *
     * @param x gives the x coordinate
     * @param y gives the y coordinate
     */
    public void makeGlider(int x, int y){
        //Here is the first glider
        board[x][y] = 1;
        board[x-1][y+1] = 1;
        board[x+1][y] = 1;
        board[x][y-1] = 1;
        board[x-1][y-1] = 1;

        //Here is the second glider
        board[x + 5][y] = 1;
        board[x + 6][y+1] = 1;
        board[x + 4][y] = 1;
        board[x + 5][y-1] = 1;
        board[x + 6][y-1] = 1;
    }

    /**
     * This should make a pulsar....
     *
     * @param  x gives the x coordinate
     * @param  y gives the y coordinate
     * @return     the sum of x and y
     */
    public void makePulsar(int x, int y)
    {
        makeVBlinker(x - 1, y - 3);
        makeVBlinker(x + 1, y - 3);
        makeVBlinker(x - 6, y - 3);
        makeVBlinker(x + 6, y - 3);
        makeVBlinker(x - 1, y + 3);
        makeVBlinker(x + 1, y + 3);
        makeVBlinker(x - 6, y + 3);
        makeVBlinker(x + 6, y + 3);
        makeBlinker(x - 3, y - 1);
        makeBlinker(x - 3, y + 1);
        makeBlinker(x - 3, y - 6);
        makeBlinker(x - 3, y + 6);
        makeBlinker(x + 3, y - 1);
        makeBlinker(x + 3, y + 1);
        makeBlinker(x + 3, y - 6);
        makeBlinker(x + 3, y + 6);
    }

    /**
     * Draws the board
     */
    public void paintComponent(Graphics g)
    {
        int Gen = 0;
        Color PRUSSIANBLUE = new Color(0,49,83);
        Color LIMEGREEN = new Color(0,255,0);
        Color NEONYELLOW = new Color(243,243,21);
        Color BLACK = new Color(0,0,0);
        Color NEONPINK = new Color(255,0,255);
        Color Random1 = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        Color Random2 = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        Color classColor1 = new Color(25,11,22);
        Color classColor2 = new Color(17,255,215);
        
        g.setColor(Random1);
        g.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

        for (int x = 0; x < BOARD_WIDTH; x++)
        {
            for (int y = 0; y < BOARD_HEIGHT; y++)
            {
                if(isAlive(x,y))
                {
                    g.setColor(Random2);
                    g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
        //System.out.println(Random1);
        //System.out.println(Random2);
        //System.out.println("These are the colors for Generation " + Gen);
        
        nextGeneration();
    }

    /****CONSTANTS FOR YOUR CONVENIENCE --- DO NOT MODIFY****/
    public final static int FRAME_WIDTH = BOARD_WIDTH*CELL_SIZE;
    public final static int FRAME_HEIGHT = BOARD_HEIGHT*CELL_SIZE;
    /**** END CONSTANTS ****/
}
