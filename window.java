
import javax.swing.*;
import java.awt.*;

final class window extends JFrame
{
    static int width,height,fps;
    static String title;
    window(int Width, int Height ,String Title ,int Fps)
    {
        this.width = Width;
        this.height = Height;
        this.fps = Fps;
        this.title = Title;
        
        init();
    }
    void init()
    {
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(title);
        add(new Render());
        
        setVisible(true);
    }
    class Render extends JPanel implements Runnable
    {
        Thread Render;
        Render()
        {
            Render = new Thread(this);
            this.setPreferredSize(new Dimension(width,height));
            // setBackground(Color.BLACK);
            Render.start();
        }
        @Override
        public void run()
        {
            while(Render != null)
            {
                
                update();
                repaint();    
                
            }
        }
        void update()
        {
            // System.out.println("Updating");
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
        }
    }
}