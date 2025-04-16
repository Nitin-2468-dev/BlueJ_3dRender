
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.Graphics2D;

final class window extends JFrame
{
    static int width,height,fps,scale;
    double fovD,znear,zfar;
    static String title;
    double vect[][];
    int edge[][];
    window(int Width, int Height ,String Title ,int Fps,double fovD , double znear , double zfar ,int scale )
    {
        this.width = Width;
        this.height = Height;
        this.fps = Fps;
        this.title = Title;
        this.fovD = fovD;
        this.znear = znear;
        this.zfar = zfar;
        this.scale = scale;
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
        static Graphics2D g2;
        Mat math = new Mat();
        Mat.matrix m = math.new matrix();
        Mat.vec2 a[];
        double PPmm[][];
        Render()
        {
            String path= "C:\\Users\\Nitin\\Desktop\\3D-obj\\Cube.obj";
            process proc = new process();
            try
            {
                process.OBJ obj = proc.new OBJ(path);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            vect = proc.getVect();
            edge = proc.getEdge();
            Render = new Thread(this);
            this.setPreferredSize(new Dimension(width,height));
            // setBackground(Color.BLACK);
            Render.start();
        }
        @Override
        public void run()
        {
            long lastTime =System.nanoTime(), CurrentTime,drawAmount= 0;
            double delta = 0 , drawInterval = fps/1_000_000_000;
            double timer= 0;
            int fpscap = fps;
            
            while(Render != null)
            {
                CurrentTime = System.nanoTime();
                delta += (CurrentTime-lastTime)/drawInterval;
                timer += (CurrentTime-lastTime);
                lastTime = CurrentTime;
                
                if(delta >= 1)
                {
                    update();
                    delta--;
                    drawAmount++;
                    repaint(); 
                }
                // if(timer >= 1)
                // {
                    // // System.out.println("FPS :  " + fps);
                    // // String Title = title + " , FPS : " ;
                    // // setTitle(Title);
                    // timer = 0;
                    // fpscap = (int) drawAmount;
                    // if(fpscap > fps)
                    // {
                        // fpscap = fps;
                    // }
                    // drawAmount = 0;
                // }
                // drawInterval = fpscap/1_000_000_000;
            }
        }
        void update()
        {
            a = new Mat.vec2[vect.length];
            double PPmm[][] = m.PPmm(width,height,fovD,znear,zfar);
            for(int i = 0 ; i < vect.length ; i++)
            {
                // System.out.println("x:"+vect[i][0]+" y:"+vect[i][1]+" z:"+vect[i][2]);
                double o[][] = m.Pdiv(PPmm,math.new vec3<>(vect[i][0],vect[i][1],vect[i][2]));
                // m.printer(o);
                // System.out.println();
                Mat.vec2 b[] = new Mat.vec2[vect.length];
                a[i] = math.new vec2<>(o[0][0],o[1][0]);
            }
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            
            if (a == null) {
                return; 
            }
            int x,y,x1,y1;
            for(int i = 0 ; i < edge.length ; i++)
            {
                x =(int) a[edge[i][0]].x.doubleValue() *scale;
                y =(int) a[edge[i][0]].y.doubleValue() *scale;
                x1 =(int) a[edge[i][1]].x.doubleValue() *scale;
                y1 =(int) a[edge[i][1]].y.doubleValue() *scale;
                // System.out.println("Drawing");
                int hw = (width/2);
                int hh = (height/2);
                g2.drawLine(x+hw,y+hh,x1+hw,y1+hh);
            }
        }
    }
}