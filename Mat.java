class Mat
{
    class vec2< T extends Number>
    {
        T x, y ;
        vec2(T x , T y)
        {
            this.x = x;
            this.y = y;
        }
        T getX()
        {
            return this.x;
        }
        T getY()
        {
            return this.y;
        }
        void setX(T x)
        {
            this.x = x;
        }
        void setY(T y)
        {
            this.y = y;
        }
        void print()
        {
            System.out.println("X : "+x+". Y : "+y+".");
        }
        vec2 add(vec2 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            return new vec2(x+x1,y+y1);
        }
        vec2 sub(vec2 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            return new vec2(x-x1,y-y1);
        }
        vec2 mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2(x*a,y*a);
        }
        vec2 mult(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2(x*a,y*a);
        }
        vec2 div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2(x/a,y/a);
        }
        vec2 div(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2(x/a,y/a);
        }
        vec2 unit(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double mag = Math.abs(mag());
            return new vec2(x/mag,y/mag);
        }
        double mag()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return Math.sqrt((x*x)+(y*y));
        }
        double[][] matix2()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double a[][] = {{x},{y}};
            return a;
        }
    }
    class vec3<T extends Number>
    {
        T x, y , z;
        vec3(T x , T y)
        {
            this.x = x;
            this.y = y;
        }
        vec3(T x , T y, T z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        T getX()
        {
            return this.x;
        }
        T getY()
        {
            return this.y;
        }
        T getZ()
        {
            return this.z;
        }
        void setX(T x)
        {
            this.x = x;
        }
        void setY(T y)
        {
            this.y = y;
        }
        void setZ(T z)
        {
            this.z = z;
        }
        void print()
        {
            System.out.println("X : "+x+". Y : "+y+".");
        }
        void print3()
        {
            System.out.println("X : "+x+". Y : "+y+" . Z : "+z+"." );
        }
        vec3 add(vec3 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec3(x+x1,y+y1,z+z1);
        }
        vec3 sub(vec3 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec3(x-x1,y-y1,z-z1);
        }
        vec3 mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x*a,y*a,z*a);
        }
        vec3 mult(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x*a,y*a,z*a);
        }
        vec3 div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x/a,y/a,z/a);
        }
        vec3 div(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x/a,y/a,z/a);
        }
        vec3 unit(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double mag = Math.abs(mag());
            return new vec3(x/mag,y/mag,z/mag);
        }
        double mag()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return Math.sqrt((x*x)+(y*y)+(z*z));
        }
        double[][] matix()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double a[][] = {{x},{y},{z}};
            return a;
        }
        double[][] matix2()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double a[][] = {{x},{y}};
            return a;
        }
    }
    class vec4<T extends Number>
    {
        T x, y , z , w;
        vec4(T x , T y, T z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        vec4(T x , T y, T z, T w)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
        }
        T getX()
        {
            return this.x;
        }
        T getY()
        {
            return this.y;
        }
        T getZ()
        {
            return this.z;
        }
        T getW()
        {
            return this.w;
        }
        void setX(T x)
        {
            this.x = x;
        }
        void setY(T y)
        {
            this.y = y;
        }
        void setZ(T z)
        {
            this.z = z;
        }
        void setW(T w)
        {
            this.w = w;
        }
        void print()
        {
            System.out.println("X : "+x+". Y : "+y+" . Z : "+z+"." );
        }
        void print4()
        {
            System.out.println("X : "+x+". Y : "+y+" . Z : "+z+" . W : "+w+"." );
        }
        vec4 add(vec3 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec4(x+x1,y+y1,z+z1);
        }
        vec4 add(vec4 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.w.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            double w1 = a.w.doubleValue();
            return new vec4(x+x1,y+y1,z+z1,w+w1);
        }
        vec4 sub(vec3 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec4(x-x1,y-y1,z-z1);
        }
        vec4 sub(vec4 a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.w.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            double w1 = a.w.doubleValue();
            return new vec4(x-x1,y-y1,z-z1,w-w1);
        }
        vec4 mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec4(x*a,y*a,z*a);
        }
        vec4 mult4(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x*a,y*a,z*a,w*a);
        }
        vec4 mult(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec4(x*a,y*a,z*a);
        }
        vec4 mult4(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x*a,y*a,z*a,w*a);
        }
        vec4 div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec4(x/a,y/a,z/a);
        }
        vec4 div4(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x/a,y/a,z/a,w/a);
        }
        vec4 div(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec4(x/a,y/a,z/a);
        }
        vec4 div4(int a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x/a,y/a,z/a,w/a);
        }
        vec4 unit()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.w.doubleValue();
            double mag = Math.abs(mag());
            return new vec4(x/mag,y/mag,z/mag,w/mag);
        }
        double mag()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.w.doubleValue();
            return Math.sqrt((x*x)+(y*y)+(z*z)+(w*w));
        }
        
    }
    class matix
    {
        double [][] matrix(double a[])
        {
            double c[][] = new double[a.length][1];
            for(int i = 0 ; i < a.length ; i++)
            {
                c[i][0] = a[i];
            }
            return c;
        }
        double[][] matix(vec2 b)
        {
            double x = b.x.doubleValue();
            double y = b.y.doubleValue();
            double a[][] = {{x},{y}};
            return a;
        }
        double[][] matix(vec3 b)
        {
            double x = b.x.doubleValue();
            double y = b.y.doubleValue();
            double z = b.z.doubleValue();
            double a[][] = {{x},{y},{z}};
            return a;
        }
        double[][] matix(vec4 b)
        {
            double x = b.x.doubleValue();
            double y = b.y.doubleValue();
            double z = b.z.doubleValue();
            double w = b.w.doubleValue();
            double a[][] = {{x},{y},{z},{w}};
            return a;
        }
        void printer(double a[][])
        {
           for(int i = 0 ; i < a.length ; i++)
           {
               for(int j = 0 ; j < a[0].length ; j++)
               {
                   System.out.print(a[i][j] + " ");
               }
               System.out.println();
           }
        }
        double [][] mul(double a[][] , double b[][])
        {
            if(a.length != b[0].length)
            {
                System.err.println("ERROR : A col != B row ! ");
                return null;
            }
            else
            {
                double c[][] = new double[a.length][b[0].length];
                int sum = 0;
                for(int i = 0 ; i < a.length ; i++)
                {
                    for(int j = 0 ; j < b[0].length ; j++)
                    {
                        c[i][j] = 0;
                        for(int k = 0 ; k < a[0].length ; k++)
                        {
                            c[i][j] += a[i][k] * b[k][j];
                        }
                    }
                }
                return c;    
            }
        }
    }
}