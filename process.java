import java.io.*;
import java.util.*;

public class process
{
    Mat math = new Mat();
    double vect[][];
    int edge[][];
    double[][] getVect()
    {
        return vect;
    }
    int[][] getEdge()
    {
        return edge;
    }
    class OBJ 
    {
        Stack<Mat.vec3<Double>> vector;
        Stack<Mat.vec3<Integer>> indus;        
        String name;
        BufferedReader read;
        OBJ(String Path) throws IOException,FileNotFoundException 
        {
            FileReader file = new FileReader(Path);
            read = new BufferedReader(file);
            vector = new Stack<>();
            indus = new Stack<>();
            getData();
            Data();
        }
        
        void getData() throws IOException
        {
            String Line = read.readLine();
            while(Line != null)
            {
                String split[];
                if(Line.charAt(0) == 'o')
                {
                    split = Line.split(" ");
                    name = split[1];
                }
                if(Line.charAt(0) == 'v')
                {
                    split = Line.split(" ");
                    double x,y,z;
                    x = Double.valueOf(split[1]);
                    y = Double.valueOf(split[2]);
                    z = Double.valueOf(split[3]);
                    vector.add(math.new vec3<>(x,y,z));
                }
                if(Line.charAt(0) == 'f')
                {
                    split = Line.split(" ");
                    int x,y,z;
                    x = Integer.valueOf(split[1]) - 1;
                    y = Integer.valueOf(split[2]) - 1;
                    z = Integer.valueOf(split[3]) - 1 ;
                    indus.add(math.new vec3<>(x,y,z));
                }
                Line = read.readLine();
            }
        }
        void Data()
        {
            vect = new double[vector.size()][3];
            for(int i =0 ; i < vector.size(); i++)
            {
                vect[i][0] = vector.get(i).getX();
                vect[i][1] = vector.get(i).getY();
                vect[i][2] = vector.get(i).getZ();
            }
            
            edge = new int[indus.size()][3];
            for(int i =0 ; i < indus.size(); i++)
            {
                edge[i][0] = indus.get(i).getX();
                edge[i][1] = indus.get(i).getY();
                edge[i][2] = indus.get(i).getZ();
            }
        }
        void print()
        {
            System.out.println("o " + name+"\n");
            System.out.println("\n\n");
            for(int i = 0 ; i < edge.length ; i++)
            {
                for(int j=0 ; j < 3 ; j++)
                {
                    System.out.print("{" + vect[edge[i][j]][0]+" "+ vect[edge[i][j]][1]+" "+vect[edge[i][j]][2]+" "+"} ("+(edge[i][j]+1)+") ");
                }
                System.out.println();
            }
        }
    }
    
}