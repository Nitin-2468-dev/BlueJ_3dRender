import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Arrays;
import java.awt.Polygon;

public class window extends JFrame
{
    private int width,height,fps,scale;
    private double fovD,znear,zfar;
    private double model[][];
    private double word[][];
    private double viewed[][];
    private double normal[][];
    private boolean face[];
    private int edge[][];
    private double faceShader[];
    private double angle;
    private Mat math = new Mat();
    private Mat.matrix m =math.new matrix();
    private Mat.vec3<Double>[] projected;
    private boolean X=false,Y=false,Z=false;
    private double displace;
    private int count = 0;
    private String Path;
    private process proc = new process();
    private double amb = 0.2;
    private Color base = new Color(102,178,255);
    private ArrayList<Integer> faceOrder ;
    private input in ;
    int testx=0 , testy=0;
    
    private Mat.vec3<Double> Up;
    private Mat.vec3<Double> target;
    private Mat.vec3<Double> camera;
    private Mat.vec3<Double> lookDir;
    private Mat.vec3<Double> lightDir;
    private double[][] mcamera;
    private double[][] view;
    private double speed = 0.1;
    
    private BufferedImage image;
    private double[][] zBuffer;
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
        this.camera = math.new vec3<>(0.0,0.0,displace);
        this.lightDir = math.new vec3<>(1.0/Math.sqrt(1+1+1),1.0/Math.sqrt(1+1+1),-1.0/Math.sqrt(1+1+1));
        this.base = base;
        this.Up = math.new vec3<>(0.0,1.0,0.0);
        this.lookDir = math.new vec3<>(0.0,0.0,1.0);
        image = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
        zBuffer = new double[Width][Height];
        in=new input(width,height,fovD);
        init();
        
    }
    void init()
    {
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Render r = new Render();
        addKeyListener(in);
        addMouseMotionListener(in);
        r.update();
        r.setFocusable(true);
        r.requestFocusInWindow();
        add(r);
        setVisible(true);
        setFocusable(true);
    }
    void Title(String title)
    {
        this.setTitle(title);
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
            
            String title = proc.name;
            Title(title);
            model = proc.getVect();
            edge = proc.getEdge();
            word = model;
            int d = 1000/fps;
            Timer timer = new Timer(d,e -> { update(); repaint(); });
            timer.start();
        
        }
        void input()
        {
            if(in.up)
            {
                 camera.setY(camera.getY() - speed );
            }
            if(in.down)
            {
                camera.setY(camera.getY() + speed );
            }
            if(in.back)
            {
                 camera.setZ(camera.getZ() + speed );
            }
            if(in.forward)
            {
                camera.setZ(camera.getZ() - speed );
            }
            if(in.left)
            {
                camera.setX(camera.getX() - speed );
            }
            if(in.right)
            {
                camera.setX(camera.getX() + speed );
            }
            if(in.mouse != null)
            {
                lookDir = in.mouse;
            }
        }
        void rot()
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
        }
        void update()
        {
            input();
            rot();
            
            target = camera.add(lookDir);
            view = m.lookAt(camera, target, Up);
            double[][] clip = new double[word.length][3];    
            double[][] cameraSpace = new double[word.length][3];
            for (int i = 0; i < word.length; i++) {
                double[][] v4 = 
                {
                  {word[i][0]},
                  {word[i][1]},
                  {word[i][2]},
                  {1}
                };
                double[][] r = m.mult(view,v4);
                cameraSpace[i][0] = r[0][0];
                cameraSpace[i][1] = r[1][0];
                cameraSpace[i][2] = r[2][0];  
                
                clip[i][0] = r[0][0];
                clip[i][1] = r[1][0];
                clip[i][2] = r[2][0];
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
                depth[i] = (r1+r2+r3)/3;
                faceOrder.add(i);
            }
            faceOrder.sort((i,j) -> Double.compare(depth[j],depth[i]));
            
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
                
                double dp = nx*p0[0] + ny*p0[1] + nz*p0[2];
                face[i] = (dp < 0);
                           
                
                double diff = Math.max(0.0,
                            normal[i][0] * lightDir.getX().doubleValue() + 
                            normal[i][1] * lightDir.getY().doubleValue() +
                            normal[i][2] * lightDir.getZ().doubleValue()
                           );
                diff = Math.min(1.0,Math.max(0.0,diff));
                faceShader[i] = amb + (1-amb)*diff;
                
                Mat.vec3 vex[] = {m.vec3(cameraSpace[x]) , m.vec3(cameraSpace[y]) , m.vec3(cameraSpace[z])};
                ArrayList<Mat.vec3<Double>> clipped = clipTri(vex,znear);
                
                
                if(clipped.size() < 3 )
                {
                    project(clip);
                }
                if(clipped.size() == 3 )
                {
                    draw(clipped.get(0),clipped.get(1),clipped.get(2),clip);
                }
                if(clipped.size() < 3 )
                {
                    draw(clipped.get(0),clipped.get(1),clipped.get(2),clip);
                    repaint();
                    draw(clipped.get(0),clipped.get(2),clipped.get(3),clip);
                }
            }            
        }
        int NearClip(Mat.vec3<Double> A, Mat.vec3<Double> B ,Mat.vec3<Double> C ,Mat.vec3<Double>[] clipBuffer , double znear )
        {
            Mat.vec3<Double>[] in = new Mat.vec3[]{A,B,C};
            int inCount = 3;
            int outCount = 0;
            return 0;
        }
        void draw(Mat.vec3<Double> v0 , Mat.vec3<Double> v1 , Mat.vec3<Double> v2,double [][]clip)
        {
            clip[0][0] = v0.getX();
            clip[0][1] = v0.getY();
            clip[0][2] = v0.getZ();
            
            clip[1][0] = v0.getX();
            clip[1][1] = v0.getY();
            clip[1][2] = v0.getZ();
            
            clip[2][0] = v0.getX();
            clip[2][1] = v0.getY();
            clip[2][2] = v0.getZ();
            
            project(clip);
        }
        void project(double[][] clip)
        {
            projected = new Mat.vec3[clip.length];
            double PPmm[][] = m.PPmm(width,height,fovD,znear,zfar);
            for(int i = 0 ; i < clip.length ; i++)
            {
                double o[][] = m.Pdiv(PPmm,math.new vec3<>(clip[i][0],clip[i][1],clip[i][2]));
                projected[i] = math.new vec3<>(o[0][0],o[1][0],o[2][0]);
            } 
        }
        double[][] rotationX(double a,double [][] vect)
        {
            return  m.mult(vect,m.Rx(a));
        }
        double[][] rotationZ(double a,double [][] vect)
        {
            return  m.mult(vect,m.Rz(a));
        }
        double[][] rotationY(double a,double [][] vect)
        {
            return  m.mult(vect,m.Ry(a));
        }
        void clearBuffer()
        {
            Graphics2D g2 = image.createGraphics();
            g2.setColor(getBackground());
            g2.fillRect(0,0,width,height);
            g2.dispose();
            
            for(int x = 0 ; x < width ; x++)
            {
                Arrays.fill(zBuffer[x], Double.POSITIVE_INFINITY);
            }
        }
        private void rasterTri(Mat.vec3<Double> a , Mat.vec3<Double> b , Mat.vec3<Double> c1 ,int scale,int hw , int hh, Color c)
        {
            int x0 = (int)(a.getX().doubleValue() * scale) + hw;
            int y0 = (int)(a.getY().doubleValue() * scale) + hh;
            int z0 = a.getZ().intValue();          // depth test only needs ints
        
            int x1 = (int)(b.getX().doubleValue() * scale) + hw;
            int y1 = (int)(b.getY().doubleValue() * scale) + hh;
            int z1 = b.getZ().intValue();
        
            int x2 = (int)(c1.getX().doubleValue() * scale) + hw;
            int y2 = (int)(c1.getY().doubleValue() * scale) + hh;
            int z2 = c1.getZ().intValue();
            Mat.vec3<Integer> v0 = math.new vec3<>(x0,y0,z0); 
            Mat.vec3<Integer> v1 = math.new vec3<>(x1,y1,z1); 
            Mat.vec3<Integer> v2 = math.new vec3<>(x2,y2,z2); 
            
            int minX = Math.max(0,Math.min(v0.getX(),Math.min(v1.getX(),v2.getX())));
            int maxX = Math.min(width-1,Math.max(v0.getX(),Math.max(v1.getX(),v2.getX())));
            int minY = Math.max(0,Math.min(v0.getY(),Math.min(v1.getY(),v2.getY())));
            int maxY = Math.min(height-1,Math.max(v0.getY(),Math.max(v1.getY(),v2.getY())));
            
            double area = edge(v0.getX(),v0.getY(),v1.getX(),v1.getY(),v2.getX(),v2.getY());
            
            for(int x = minX ; x <= maxX;x++)
            {
                for(int y = minY ; y <= maxY ; y++)
                {
                    double w0 = edge(v1.getX(),v1.getY(),v2.getX(),v2.getY(),x,y) / area;
                    double w1 = edge(v2.getX(),v2.getY(),v0.getX(),v0.getY(),x,y) / area;
                    double w2 = edge(v0.getX(),v0.getY(),v1.getX(),v1.getY(),x,y) / area;
                    
                    if(w0 >= 0 && w1 >= 0 && w2 >= 0)
                    {
                        double z = w0*v0.getZ() + w1*v1.getZ() + w2*v2.getZ() ;
                        
                        if(z < zBuffer[x][y])
                        {
                            zBuffer[x][y] = z;
                            image.setRGB(x,y,c.getRGB());
                        }
                    }
                }
            }
        }
        private double edge(int ax , int ay , int bx , int by , int cx , int cy)
        {
            return (cx-ax)*(by-ay) - (cy-ay)*(bx-ax);
        }
        Mat.vec3<Double> lerp(Mat.vec3<Double> a,Mat.vec3<Double> b,double t )
        {
            return math.new vec3<Double>(a.getX() + t*(b.getX() - a.getX()),a.getY() + t*(b.getY() - a.getY()),a.getZ() + t*(b.getZ() - a.getZ()));
        }
        ArrayList<Mat.vec3<Double>> clipTri(Mat.vec3<Double>[] tri, double znear )
        {
            ArrayList<Mat.vec3<Double>> input = new ArrayList<>();
            ArrayList<Mat.vec3<Double>> output = new ArrayList<>();
            for(int i = 0 ; i < tri.length ; i++)
            {
                input.add(tri[i]);
            }
            
            for(int i = 0 ; i < input.size(); i++)
            {
                Mat.vec3<Double> A = input.get(i);
                Mat.vec3<Double> B = input.get((i+1) % input.size());
                boolean insideA = A.getZ() > znear ;
                boolean insideB = B.getZ() > znear ;
                
                if(insideA && insideB)
                {
                    output.add(B);
                }
                else if(insideA && !insideB)
                {
                    double t = (znear - A.getZ()) / (B.getZ() - A.getZ());
                    output.add(lerp(A,B,t));
                }
                else if(!insideA && insideB)
                {
                    double t = (znear - A.getZ()) / (B.getZ() - A.getZ());
                    output.add(lerp(A,B,t));
                    output.add(B);
                }
            }
            return output;
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            clearBuffer();
            if (projected == null) {
                return; 
            }
            int hw = (width/2),hh = (height/2);
            for(int i : faceOrder)
            {
                if(face[i])
                {
                    Mat.vec3<Double> v0 = projected[edge[i][0]];
                    Mat.vec3<Double> v1 = projected[edge[i][1]];
                    Mat.vec3<Double> v2 = projected[edge[i][2]];
                    float s = (float) faceShader[i];
                    int r0 = base.getRed(),   g0 = base.getGreen(),  b0 = base.getBlue();
                    int R  = (int)(r0 * s),  
                    G  = (int)(g0 * s),  
                    B  = (int)(b0 * s);
                    Color fillC = new Color(
                      Math.min(255, Math.max(0, R)),
                      Math.min(255, Math.max(0, G)),
                      Math.min(255, Math.max(0, B))
                    );
                    rasterTri(v0,v1,v2,scale,hw,hh,fillC);
                }
            }
            g.drawImage(image,0,0,null);
        }
    }
}