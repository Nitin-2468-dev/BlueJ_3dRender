import java.awt.Color;

class Main
{
    static void Main()
    {
        int Width = 500;
        int Height = 500;
        int Fps = 100 ;
        double fovD = 45;
        double znear = 0.01;
        double zfar = 1000;
        int scale = Width/4;
        double angle = 1;
        // boolean X = true;
        boolean X = false;
        // boolean Y = true;
        boolean Y = false;
        // boolean Z = true;
        boolean Z = false;
        double cameraZ = 2;
        Color base = new Color(102,178,255);
           String Path = "./obj/cube.obj";
        // String Path = "./obj/Suzanne.obj";
        // String Path = "./obj/Primamed.obj";
        // String Path = "./obj/Icosphere.obj";
        // String Path = "./obj/axys.obj";
        
        window win = new window(Width,Height,Fps,fovD,znear,zfar,scale,angle,X,Y,Z,cameraZ,Path,base);
    }
}