 public class Mat
{
    class vec2< T extends Number>
    {
        private T x, y ;
        public vec2(T x , T y)
        {
            this.x = x;
            this.y = y;
        }
        public T getX()
        {
            return this.x;
        }
        public T getY()
        {
            return this.y;
        }
        public void setX(T x)
        {
            this.x = x;
        }
        public void setY(T y)
        {
            this.y = y;
        }
        public void print()
        {
            System.out.println("X : "+x+". Y : "+y+".");
        }
        public vec2<Double> add(vec2<? extends Number> a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            return new vec2<>(x+x1,y+y1);
        }
        public vec2<Double> sub(vec2<? extends Number> a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            return new vec2<>(x-x1,y-y1);
        }
        public vec2<Double> mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2<>(x*a,y*a);
        }
        public vec2<Double> div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return new vec2<>(x/a,y/a);
        }
        public vec2<Double> unit(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double mag = Math.abs(mag());
            return new vec2(x/mag,y/mag);
        }
        public double mag()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            return Math.sqrt((x*x)+(y*y));
        }
        public double[][] matix()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double a[][] = {{x},{y}};
            return a;
        }
    }
    class vec3<T extends Number>
    {
        private T x, y , z;
        public vec3(T x , T y, T z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public T getX()
        {
            return this.x;
        }
        public T getY()
        {
            return this.y;
        }
        public T getZ()
        {
            return this.z;
        }
        public void setX(T x)
        {
            this.x = x;
        }
        public void setY(T y)
        {
            this.y = y;
        }
        public void setZ(T z)
        {
            this.z = z;
        }
        public void print()
        {
            System.out.println("X : "+x+". Y : "+y+" . Z : "+z+"." );
        }
        public vec3<Double> add(vec3<? extends Number> a )
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec3<>(x+x1,y+y1,z+z1);
        }
        public vec3<Double> sub(vec3<? extends Number> a )
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return new vec3(x-x1,y-y1,z-z1);
        }
        public vec3<Double> sub(vec3<? extends Number> a,vec3<? extends Number> b )
        {
            double x = a.x.doubleValue();
            double y = a.y.doubleValue();
            double z = a.z.doubleValue();
            double x1 = b.x.doubleValue();
            double y1 = b.y.doubleValue();
            double z1 = b.z.doubleValue();
            return new vec3(x-x1,y-y1,z-z1);
        }
        public vec3<Double> mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x*a,y*a,z*a);
        }
        public vec3<Double> div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            return new vec3(x/a,y/a,z/a);
        }
        vec3<Double> unit()
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double mag = Math.abs(mag());
            return new vec3<>(x/mag,y/mag,z/mag);
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
        Double dot(vec3<? extends Number> a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            return x*x1 + y*y1 + z*z1;
        }
        vec3<Double> cross(vec3<? extends Number> a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double x1 = a.x.doubleValue();
            double y1 = a.y.doubleValue();
            double z1 = a.z.doubleValue();
            
            double cx= y*z1 - z*y1;
            double cy = z*x1 - x*z1;
            double cz = x*y1 - y*x1;
            return new vec3(cx,cy,cz);
        }
    }
    class vec4<T extends Number>
    {
        T x, y , z , w;
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
            System.out.println("X : "+x+". Y : "+y+" . Z : "+z+" . W : "+w+"." );
        }
        vec4<Double> add(vec4<? extends Number> a)
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
        vec4<Double> sub(vec4<? extends Number> a)
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
        vec4<Double> mult(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x*a,y*a,z*a,w*a);
        }
        vec4<Double> div(double a)
        {
            double x = this.x.doubleValue();
            double y = this.y.doubleValue();
            double z = this.z.doubleValue();
            double w = this.z.doubleValue();
            return new vec4(x/a,y/a,z/a,w/a);
        }
        vec4<Double> unit()
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
    class matrix
    {
        double [][] PPmm(int width,int height,double fovD,double znear,double zfar)
        {            
            double PPmm[][] = new double[4][4];
            double fov = Math.toRadians(fovD);
            double a = (double) height / (double) width;
            double f = (1.0/Math.tan(fov/2.0));
            PPmm[0][0] = f/a;
            PPmm[1][1] = f;
            PPmm[2][2] = zfar/(zfar-znear);
            PPmm[2][3] = (-zfar * znear) / (zfar - znear);
            PPmm[3][2] = 1.0;
            return PPmm;
        }
        double [][] Pdiv(double [][]PPmm ,vec3<? extends Number> b)
        {
            double cods[][] = matrix(new vec4(b.x,b.y,b.z,1.0));
            double r[][] = mult(PPmm,cods);
            if(r[3][0] != 0.0)
            {
                r[0][0] /= r[3][0];
                r[1][0] /= r[3][0];
                r[2][0] /= r[3][0];
            }
            return r;
        }
        vec3<Double> vec3(double []a)
        {
            return new vec3<Double>(a[0],a[1],a[2]);
        }
        vec4 vec4(double [][]a)
        {
            return new vec4(a[0][0],a[1][0],a[2][0],a[3][0]);
        }
        double [][] matrix(double a[])
        {
            double c[][] = new double[a.length][1];
            for(int i = 0 ; i < a.length ; i++)
            {
                c[i][0] = a[i];
            }
            return c;
        }
        double[][] matrix(vec4 b)
        {
            double x = b.x.doubleValue();
            double y = b.y.doubleValue();
            double z = b.z.doubleValue();
            double w = b.w.doubleValue();
            double a[][] = {{x},{y},{z},{w}};
            return a;
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
        double[][] Rx(double a)
        {
            a = Math.toRadians(a);
            double R[][]  = {
                {1, 0 , 0},
                {0 ,Math.cos(a),-Math.sin(a)},
                {0 ,Math.sin(a),Math.cos(a)}
            };        
            return R;
        }
        double[][] Ry(double a)
        {
            a = Math.toRadians(a);
            double R[][]  = {
                {Math.cos(a), 0 , Math.sin(a)},
                {0 ,1,0},
                {-Math.sin(a) ,0,Math.cos(a)}
            };        
            return R;
        }
        double[][] Rz(double a)
        {
            a = Math.toRadians(a);
            double R[][]  = {
                {Math.cos(a),-Math.sin(a), 0},
                {Math.sin(a),Math.cos(a), 0},
                {0 ,0 , 1}
            };        
            return R;
        }
        double[] unit(double a[])
        {
            double mag = Math.sqrt(a[0]*a[0] + a[1]*a[1] * a[2]*a[2]);
            a[0] /= mag;
            a[1] /= mag;
            a[2] /= mag;
            return a ;
        }
        double[][] lookAt(vec3<Double> pos,vec3 target,vec3 up )
        {
            vec3<Double> forward = new vec3<>(
                target.getX().doubleValue() - pos.getX().doubleValue(),
                target.getY().doubleValue() - pos.getY().doubleValue(),
                target.getZ().doubleValue() - pos.getZ().doubleValue()
            ).unit();
            
            vec3<Double> right = up.cross(forward).unit();
            
            vec3<Double> trueUp = forward.cross(right).unit();
            
            double px = pos.getX().doubleValue(),
                   py = pos.getY().doubleValue(),
                   pz = pos.getZ().doubleValue();
            
            return new double[][] {
              {  right.getX().doubleValue(),  right.getY().doubleValue(),  right.getZ().doubleValue(),
                - (right.getX().doubleValue()*px + right.getY().doubleValue()*py + right.getZ().doubleValue()*pz) },
              {  trueUp.getX().doubleValue(),  trueUp.getY().doubleValue(),  trueUp.getZ().doubleValue(),
                - (trueUp.getX().doubleValue()*px + trueUp.getY().doubleValue()*py + trueUp.getZ().doubleValue()*pz) },
              { -forward.getX().doubleValue(), -forward.getY().doubleValue(), -forward.getZ().doubleValue(),
                   ( forward.getX().doubleValue()*px + forward.getY().doubleValue()*py + forward.getZ().doubleValue()*pz) },
              { 0, 0, 0, 1 }
            };
        }
        double[][] qi(double [][] matix,vec3<Double> pos,vec3 tra,vec3 up )
        {
            vec3<Double> newForward =tra.sub(pos);
            newForward = newForward.unit();
            
            vec3<Double> a = newForward.mult(newForward.dot(up));
            vec3<Double> newUp = a.sub(up);
            newUp= newUp.unit();
            
            vec3<Double> newRight =  newUp.cross(newForward);  
            
            matix[3][0] = -pos.dot(newRight);
            matix[3][2] = -pos.dot(newUp);
            matix[3][1] = -pos.dot(newForward);
            return matix;
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
        void printer(vec2 a[])
        {
           for(int i = 0 ; i < a.length ; i++)
           {
               System.out.println("x:"+a[i].x + " y:"+a[i].y);
           }
        }
        double[][] mult(double a[][] , double b[][])
        {
            if(a[0].length != b.length)
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
