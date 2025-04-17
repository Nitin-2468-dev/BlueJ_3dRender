import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.Graphics2D;

public class window extends JFrame
{
    private int width,height,fps,scale;
    private double fovD,znear,zfar;
    private String title;
    private double model[][];
    private double word[][];
    private double cameraSpace[][];
    private int edge[][];
    private double angle;
    private Mat math = new Mat();
    private Mat.matrix m =math.new matrix();
    private Mat.vec2<Double>[] projected;
    private boolean X=false,Y=false,Z=false;
    private double cameraZ;
    private int count = 0;
    window(int Width, int Height  ,int Fps,double fovD , double znear , double zfar ,int scale , double angle , boolean X, boolean Y, boolean Z,double cameraZ)
    {
        this.width = Width;
        this.height = Height;
        this.fps = Fps;
        this.fovD = fovD;
        this.znear = znear;
        this.zfar = zfar;
        this.scale = scale;
        this.angle = angle;
        this.X = X;
        this.Y =Y;
        this.Z = Z;
        this.cameraZ= cameraZ;
        init();
    }
    void init()
    {
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new Render());
        setVisible(true);
    }
    class Render extends JPanel
    {
        Render()
        {
            this.setPreferredSize(new Dimension(width,height));
            init();
        }
        void init()
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
            model = proc.getVect();
            edge = proc.getEdge();
            word = model;
            int d = 1000/fps;
            Timer timer = new Timer(d,e -> { update(); repaint(); });
            timer.start();
        
        }
        void update()
        {
            
            if(X)
            {
                word = rotationX(angle,word);
            }
            if(Y)
            {
                word = rotationY(angle,word);
            }
            if(Z)
            {
                word = rotationZ(angle,word);
            }    
            double[][] cameraSpace = new double[word.length][3];
            for (int i = 0; i < word.length; i++) {
                cameraSpace[i][0] = word[i][0];
                cameraSpace[i][1] = word[i][1];
                cameraSpace[i][2] = word[i][2] + cameraZ;  
            }
            
            projected = new Mat.vec2[cameraSpace.length];
            double PPmm[][] = m.PPmm(width,height,fovD,znear,zfar);
            for(int i = 0 ; i < cameraSpace.length ; i++)
            {
                double o[][] = m.Pdiv(PPmm,math.new vec3<>(cameraSpace[i][0],cameraSpace[i][1],cameraSpace[i][2]));
                projected[i] = math.new vec2<>(o[0][0],o[1][0]);
            } 
        }
        double[][] rotationX(double a,double [][] vect)
        {
            return  m.mul(vect,m.Rx(a));
        }
        double[][] rotationZ(double a,double [][] vect)
        {
            return  m.mul(vect,m.Rz(a));
        }
        double[][] rotationY(double a,double [][] vect)
        {
            return  m.mul(vect,m.Ry(a));
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if (projected == null) {
                return; 
            }
            int x,y,x1,y1,x2,y2;
            int hw = (width/2),hh = (height/2);
            for(int i = 0 ; i < edge.length ; i++)
            {
                if(projected[edge[i][0]].getX() == null)
                {
                    // m.printer(a);    
                }
                else
                {
                    
                    
                    x =(int)( projected[edge[i][0]].getX().doubleValue() *scale )+ hw;
                    y =(int)( projected[edge[i][0]].getY().doubleValue() *scale )+ hh;
                    x1 =(int)( projected[edge[i][1]].getX().doubleValue() *scale)+ hw;
                    y1 =(int)( projected[edge[i][1]].getY().doubleValue() *scale)+ hh;
                    x2 =(int)( projected[edge[i][2]].getX().doubleValue() *scale)+ hw;
                    y2 =(int)( projected[edge[i][2]].getY().doubleValue() *scale)+ hh;
                    
                    g2.drawLine(x,y,x1,y1);
                    g2.drawLine(x1,y1,x2,y2);
                    g2.drawLine(x2,y2,x,y);
                }
            }
        }
    }
}