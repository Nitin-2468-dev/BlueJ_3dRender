import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class process
{
    private double vect[][];
    private int edge[][];
    Mat math = new Mat();
    String name;
    public double[][] getVect()
    {
        return vect;
    }
    public int[][] getEdge()
    {
        return edge;
    }
    class OBJ 
    {
        Stack<Mat.vec3<Double>> vector;
        Stack<Mat.vec3<Integer>> indus;        
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
                List<Integer> vids = new ArrayList<>();
                split = Line.split(" ");
                String[] tok = Line.trim().split("\\s+");
                if(Line.charAt(0) == 'o')
                {
                    name = split[1];
                }
                if(Line.charAt(0) == 'v')
                {
                    double x,y,z;
                    x = Double.valueOf(split[1]);
                    y = Double.valueOf(split[2]);
                    z = Double.valueOf(split[3]);
                    vector.add(math.new vec3<>(x,y,z));
                }
                if(Line.charAt(0) == 'f')
                {
                    for(int i = 1 ; i < tok.length ; i++)
                    {
                        String parts = tok[i];
                        int sh = parts.indexOf('/');
                        String idx= sh < 0 ? parts : parts.substring(0,sh);
                        vids.add(Integer.parseInt(idx) - 1); 
                    }
                    for (int k = 1; k + 1 < vids.size(); k++) {
                        int i0 = vids.get(0), i1 = vids.get(k), i2 = vids.get(k+1);
                        indus.add(math.new vec3<>(i0, i2, i1));
                    }
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
            for(int i =0 ; i < edge.length; i++)
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