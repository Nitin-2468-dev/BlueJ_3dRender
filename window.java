
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
    double angle;
    window(int Width, int Height ,String Title ,int Fps,double fovD , double znear , double zfar ,int scale , double angle)
    {
        this.width = Width;
        this.height = Height;
        this.fps = Fps;
        this.title = Title;
        this.fovD = fovD;
        this.znear = znear;
        this.zfar = zfar;
        this.scale = scale;
        this.angle = angle;
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
    class Render extends JPanel
    {
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
            this.setPreferredSize(new Dimension(width,height));
            // setBackground(Color.BLACK);
            int d = 1000/fps;
            Timer timer = new Timer(d,e -> {
                update();
                repaint();
            });
            timer.start();
        }
        void update()
        {
            int counter = 0;
            if(counter == 0)
            {
                counter++;
                vect = rotationX(angle,vect);
                // m.printer(vect);
                // vect = rotationY(angle,vect);
                vect = rotationZ(angle,vect);
                
                a = new Mat.vec2[vect.length];
                double PPmm[][] = m.PPmm(width,height,fovD,znear,zfar);
                for(int i = 0 ; i < vect.length ; i++)
                {
                    // System.out.println("x:"+vect[i][0]+" y:"+vect[i][1]+" z:"+vect[i][2]);
                    double o[][] = m.Pdiv(PPmm,math.new vec3<>(vect[i][0],vect[i][1],vect[i][2]));
                    // m.printer(o);
                    // System.out.println();
                    a[i] = math.new vec2<>(o[0][0],o[1][0]);
                }    
            }
        }
        double[][] rotationX(double a,double [][] vect)
        {
            double R[][] = m.Rx(a);
            double vect2[][] = m.mul(vect,R);
            return vect2;
        }
        double[][] rotationZ(double a,double [][] vect)
        {
            double R[][] = m.Rz(a);
            double vect2[][] = m.mul(vect,R);
            return vect2;
        }
        double[][] rotationY(double a,double [][] vect)
        {
            double R[][] = m.Ry(a);
            double vect2[][] = m.mul(vect,R);
            return vect2;
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
            
            if (a == null) {
                return; 
            }
            int x,y,x1,y1,x2,y2;
            for(int i = 0 ; i < edge.length ; i++)
            {
                if(a[edge[i][0]].x == null)
                {
                    // m.printer(a);    
                }
                else
                {
                    int hw = (width/2);
                    int hh = (height/2);
                    
                    x =(int)( a[edge[i][0]].x.doubleValue() *scale )+ hw;
                    y =(int)( a[edge[i][0]].y.doubleValue() *scale )+ hh;
                    x1 =(int)( a[edge[i][1]].x.doubleValue() *scale)+ hw;
                    y1 =(int)( a[edge[i][1]].y.doubleValue() *scale)+ hh;
                    x2 =(int)( a[edge[i][2]].x.doubleValue() *scale)+ hw;
                    y2 =(int)( a[edge[i][2]].y.doubleValue() *scale)+ hh;
                    // System.out.println("Drawing");
                    
                    g2.drawLine(x,y,x1,y1);
                    g2.drawLine(x1,y1,x2,y2);
                    g2.drawLine(x2,y2,x,y);
                }
            }
        }
    }
}