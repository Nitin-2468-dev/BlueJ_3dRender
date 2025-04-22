import java.awt.event.*;

class input implements KeyListener,MouseMotionListener
{
    private Mat math = new Mat();
    public boolean up,down,left,right,forward,back,moved = false;
    Mat.vec3<Double> mouse ;
    private int width , height ;
    public double fov,sen = 0.002,yaw , pitch;
    public int lastX , lastY;
    input(int width ,int height,double fov)
    {
        this.width = width;
        this.height = height;
        this.fov = Math.toRadians(fov/2);
        this.lastX = width/2;
        this.lastY = height/2;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W)
        {
            forward = true;
        }
        if(code == KeyEvent.VK_S)
        {
            back = true;
        }
        if(code == 32) // Space
        {
            up = true;
        }
        if(code == 16) // shift
        {
            down = true;
        }
        if(code == KeyEvent.VK_A)
        {
            left = true;
        }
        if(code == KeyEvent.VK_D)
        {
            right = true;
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W)
        {
            forward = false;
        }
        if(code == KeyEvent.VK_S)
        {
            back = false;
        }
        if(code == 32) // Space
        {
            up = false;
        }
        if(code == 16) // shift
        {
            down = false;
        }
        if(code == KeyEvent.VK_A)
        {
            left = false;
        }
        if(code == KeyEvent.VK_D)
        {
            right = false;
        }
    }
    private void move(MouseEvent e)
    {
        int x = e.getX(),y = e.getY();
        if(!moved)
        {
            lastX = x;
            lastY = y;
            moved = true;
        }
        
        int dx = lastX - x;
        int dy = lastY - y;
        lastX = x;
        lastY = y;
        
        yaw += dx*sen;
        pitch += dy*sen;
        
        pitch=Math.max(-Math.PI/2+0.001,Math.min(Math.PI/2 - 0.001 , pitch));
        
        double cx = Math.cos(pitch) * Math.sin(yaw);
        double cy = Math.sin(pitch);
        double cz = Math.cos(pitch) * Math.cos(yaw);
        mouse = math.new vec3<>(cx,cy,cz).unit();
        // System.out.println("Now : ("+x+","+y+")"+" last : ("+lastX+","+lastY+")");
    }
    public void mouseDragged(MouseEvent e)
    {
        move(e);
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    
}