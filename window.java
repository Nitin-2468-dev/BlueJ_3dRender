import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class window extends JFrame
{
    private int width,height,fps,scale;
    private double fovD,znear,zfar;
    private String title;
    private double model[][];
    private double word[][];
    private double normal[][];
    private boolean face[];
    private int edge[][];
    private double faceShader[];
    private double angle;
    private Mat math = new Mat();
    private Mat.matrix m =math.new matrix();
    private Mat.vec2<Double>[] projected;
    private boolean X=false,Y=false,Z=false;
    private double displace;
    private int count = 0;
    private String Path;
    private process proc = new process();
    private Mat.vec3<Double> camera;
    private Mat.vec3<Double> lightDir;
    private double amb = 0.2;
    private Color base = new Color(102,178,255);
    ArrayList<Integer> faceOrder ;
    window(int Width, int Height  ,int Fps,double fovD , double znear , double zfar ,int scale , double angle , boolean X, boolean Y, boolean Z,double cameraZ,String Path,Color base)
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
        this.displace= cameraZ;
        this.Path = Path;
        this.title = proc.name;
        this.camera = math.new vec3<>(0.0,0.0,0.0);
        this.lightDir = math.new vec3<>(1.0/Math.sqrt(1+1+1),1.0/Math.sqrt(1+1+1),-1.0/Math.sqrt(1+1+1));
        this.base = base;
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
            try
            {
                process.OBJ obj = proc.new OBJ(Path);
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
                cameraSpace[i][2] = word[i][2] + displace;  
            }
            
            normal = new double[edge.length][3];
            face = new boolean[edge.length];
            faceShader = new double[edge.length];
            double depth[] = new double[edge.length];
            faceOrder = new ArrayList<>(edge.length);
            
             for (int i = 0; i < edge.length; i++) {
                 int x = edge[i][0],y = edge[i][1],z = edge[i][2];
                double[] p0  = cameraSpace[x], p1  = cameraSpace[y],p2  = cameraSpace[z];
                double r1 = cameraSpace[x][2];
                double r2 = cameraSpace[y][2];
                double r3 = cameraSpace[z][2];
                depth[i] = r1+r2+r3/3;
                faceOrder.add(i);
            }
            faceOrder.sort((j,i) -> Double.compare(depth[i],depth[j]));
            
            for(int i = 0 ; i < edge.length ; i++)
            {
                
                int x = edge[i][0],y = edge[i][1],z = edge[i][2];
                double[] p0  = cameraSpace[x], p1  = cameraSpace[y],p2  = cameraSpace[z];
                
                double vx1 = p1[0] - p0[0];
                double vy1 = p1[1] - p0[1];
                double vz1 = p1[2] - p0[2];
                
                double vx2 = p2[0] - p0[0];
                double vy2 = p2[1] - p0[1];
                double vz2 = p2[2] - p0[2];
                
                double nx = vy1 * vz2 - vz1 * vy2;
                double ny = vz1 * vx2 - vx1 * vz2;
                double nz = vx1 * vy2 - vy1 * vx2;
                double len = 1/Math.sqrt(nx*nx+ny*ny+nz*nz);
                
                normal[i][0] = nx*len;
                normal[i][1] = ny*len;
                normal[i][2] = nz*len;
                
                face[i] = (normal[i][0] * (p0[0] - camera.getX()) + 
                           normal[i][1] * (p0[1] - camera.getY()) +
                           normal[i][2] * (p0[2] - camera.getZ()) < 0);
                           
                
                double diff = Math.max(0.0,
                            normal[i][0] * lightDir.getX().doubleValue() + 
                            normal[i][1] * lightDir.getY().doubleValue() +
                            normal[i][2] * lightDir.getZ().doubleValue()
                           );
                diff = Math.min(1.0,Math.max(0.0,diff));
                faceShader[i] = amb + (1-amb)*diff;
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
             
            int hw = (width/2),hh = (height/2);
            for(int i : faceOrder)
            {
                if(projected[edge[i][0]].getX() == null)
                {
                    // m.printer(a);    
                }
                else
                {
                    if(face[i])
                    {
                        int x0,y0,x1,y1,x2,y2;
                        int x[] = new int[3], y[] = new int[3];
                        
                        float s = (float) faceShader[i];
                        
                        g2.setColor(Color.black);
                        x0 =(int)( projected[edge[i][0]].getX().doubleValue() *scale )+ hw;
                        y0 =(int)( projected[edge[i][0]].getY().doubleValue() *scale )+ hh;
                        x1 =(int)( projected[edge[i][1]].getX().doubleValue() *scale)+ hw;
                        y1 =(int)( projected[edge[i][1]].getY().doubleValue() *scale)+ hh;
                        x2 =(int)( projected[edge[i][2]].getX().doubleValue() *scale)+ hw;
                        y2 =(int)( projected[edge[i][2]].getY().doubleValue() *scale)+ hh;
                        
                        g2.drawLine(x0,y0,x1,y1);
                        g2.drawLine(x1,y1,x2,y2);
                        g2.drawLine(x2,y2,x0,y0);
                        
                        
                        
                        int r0 = base.getRed(),   g0 = base.getGreen(),  b0 = base.getBlue();
                        int R  = (int)(r0 * s),  
                        G  = (int)(g0 * s),  
                        B  = (int)(b0 * s);
                        Color fillC = new Color(
                          Math.min(255, Math.max(0, R)),
                          Math.min(255, Math.max(0, G)),
                          Math.min(255, Math.max(0, B))
                        );
                        g2.setColor(fillC);
                        // g2.setColor(Color.black);
                        x[0] =(int)( projected[edge[i][0]].getX().doubleValue() *scale )+ hw;
                        y[0] =(int)( projected[edge[i][0]].getY().doubleValue() *scale )+ hh;
                        x[1] =(int)( projected[edge[i][1]].getX().doubleValue() *scale)+ hw;
                        y[1] =(int)( projected[edge[i][1]].getY().doubleValue() *scale)+ hh;
                        x[2] =(int)( projected[edge[i][2]].getX().doubleValue() *scale)+ hw;
                        y[2] =(int)( projected[edge[i][2]].getY().doubleValue() *scale)+ hh;
                        
                        Polygon p = new Polygon(x, y, 3);
                        g2.fillPolygon(p);
                    }
                }
            }
        }
    }
}