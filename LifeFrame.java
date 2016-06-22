import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A frame to hold and display a Life board and "go" and "quit" buttons
 * below that.
 * 
 * @author Dan Drake, who shamelessly cribbed so much of what's below
 * from Joel and Chuck that claiming authorship is really quite a
 * stretch.
 */

public class LifeFrame implements ActionListener
{
    private JFrame frame; //the actual frame we'll be showing
    private Life board; //the life board to draw
    private Timer animator; //a timer to control animation
    private JPanel panel; // panel for the two buttons
    private JButton goButton;
    private JButton stopButton;
    private JButton quitButton;

    /**
     * Creates a new LifeFrame object.
     */
    public LifeFrame()
    {
        frame = new JFrame("Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: play classic Frankenstein "it's ALIVE!!" clip when
        // executing the line below
        board = new Life();
        board.setPreferredSize(new Dimension(Life.FRAME_WIDTH, Life.FRAME_HEIGHT));

        panel = new JPanel();
        goButton = new JButton("Go!");
        panel.add(goButton);
        goButton.addActionListener(this);
        stopButton = new JButton("Stop");
        panel.add(stopButton);
        stopButton.addActionListener(this);
        quitButton = new JButton("Quit");
        panel.add(quitButton);
        quitButton.addActionListener(this);

        Container myContainer = frame.getContentPane();
        myContainer.setLayout(new BorderLayout());
        myContainer.add(board, BorderLayout.CENTER);
        myContainer.add(panel, BorderLayout.SOUTH);

        animator = new Timer(Life.ANIMATION_DELAY, this); //create the timer, with this object controlling it

        frame.pack(); //make everything the preferred size
        frame.setVisible(true); //show the frame
    }

    /**
     * An event, you say? What shall we do?
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == goButton)
            startAnimation();
        if (e.getSource() == quitButton)
            System.exit(0);
        if (e.getSource() == stopButton)
            pauseAnimation();
        board.repaint();
    }

    /**
     * Starts the animation timer
     */
    public void startAnimation()
    {
        animator.start();
    }

    /**
     * Stops the animation timer
     */
    public void pauseAnimation()
    {
        animator.stop();
    }
}
