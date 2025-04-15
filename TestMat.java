class TestMat {
    public static void main(String[] args) {
        // Instantiate the outer Mat class
        Mat mat = new Mat();

        // --- Testing vec2 operations ---
        System.out.println("Testing vec2:");
        // Create two vec2 objects with Double values
        Mat.vec2<Double> v1 = mat.new vec2<>(3.0, 4.0);
        Mat.vec2<Double> v2 = mat.new vec2<>(1.0, 2.0);

        // Print the vectors
        System.out.print("v1: ");
        v1.print();
        System.out.print("v2: ");
        v2.print();

        // Test addition of vec2
        Mat.vec2 vAdd = v1.add(v2);
        System.out.print("v1 + v2 = ");
        vAdd.print();

        // Test subtraction of vec2
        Mat.vec2 vSub = v1.sub(v2);
        System.out.print("v1 - v2 = ");
        vSub.print();

        // Test multiplication by a scalar (using int overload)
        Mat.vec2 vMult = v1.mult(2);
        System.out.print("v1 * 2 = ");
        vMult.print();

        // Test division by a scalar (using double overload)
        Mat.vec2 vDiv = v1.div(2.0);
        System.out.print("v1 / 2.0 = ");
        vDiv.print();

        // Test magnitude calculation
        System.out.println("Magnitude of v1: " + v1.mag());
        System.out.println();

        // --- Testing matrix operations with vec2 ---
        System.out.println("Matrix representation of v1:");
        Mat.matix matrixObj = mat.new matix();
        double[][] v1Matrix = matrixObj.matix(v1);
        matrixObj.printer(v1Matrix);
        System.out.println();

        // --- Testing vec3 operations ---
        System.out.println("Testing vec3:");
        // Create two vec3 objects
        Mat.vec3<Double> w1 = mat.new vec3<>(1.0, 2.0, 3.0);
        Mat.vec3<Double> w2 = mat.new vec3<>(4.0, 5.0, 6.0);

        // Print the vec3 objects
        System.out.print("w1: ");
        w1.print3();
        System.out.print("w2: ");
        w2.print3();

        // Test addition of vec3
        Mat.vec3 wAdd = w1.add(w2);
        System.out.print("w1 + w2 = ");
        wAdd.print3();

        // Test subtraction of vec3
        Mat.vec3 wSub = w1.sub(w2);
        System.out.print("w1 - w2 = ");
        wSub.print3();

        // Test magnitude of vec3
        System.out.println("Magnitude of w1: " + w1.mag());
        System.out.println();

        // --- Testing vec4 operations ---
        System.out.println("Testing vec4:");
        // Create two vec4 objects
        Mat.vec4<Double> u1 = mat.new vec4<>(1.0, 2.0, 3.0, 4.0);
        Mat.vec4<Double> u2 = mat.new vec4<>(5.0, 6.0, 7.0, 8.0);

        // Print the vec4 objects using print4
        System.out.print("u1: ");
        u1.print4();
        System.out.print("u2: ");
        u2.print4();

        // Test addition of vec4 (using the vec4 add(vec4) method)
        Mat.vec4 uAdd = u1.add(u2);
        System.out.print("u1 + u2 = ");
        uAdd.print4();

        // Test subtraction of vec4 (using the vec4 sub(vec4) method)
        Mat.vec4 uSub = u1.sub(u2);
        System.out.print("u1 - u2 = ");
        uSub.print4();

        // Test magnitude of vec4
        System.out.println("Magnitude of u1: " + u1.mag());
        
        System.out.println("\n\n\n\n");
        

        // --------- Test conversion methods ---------
        System.out.println("Testing conversion from double[] to matrix:");
        double[] arr = { 1.0, 2.0, 3.0 };
        double[][] mArr = matrixObj.matrix(arr);
        matrixObj.printer(mArr);
        
        System.out.println("\nTesting conversion from vec2 to matrix:");
        Mat.vec2<Double> vec2obj = mat.new vec2<>(1.0, 2.0);
        double[][] mVec2 = matrixObj.matix(vec2obj);
        matrixObj.printer(mVec2);
        
        System.out.println("\nTesting conversion from vec3 to matrix:");
        Mat.vec3<Double> vec3obj = mat.new vec3<>(3.0, 4.0, 5.0);
        double[][] mVec3 = matrixObj.matix(vec3obj);
        matrixObj.printer(mVec3);
        
        System.out.println("\nTesting conversion from vec4 to matrix:");
        Mat.vec4<Double> vec4obj = mat.new vec4<>(6.0, 7.0, 8.0, 9.0);
        double[][] mVec4 = matrixObj.matix(vec4obj);
        matrixObj.printer(mVec4);

        // --------- Test matrix multiplication ---------
        System.out.println("\nTesting matrix multiplication:");
        // Define two matrices that satisfy the condition in mul():
        // The implementation requires that a.length == b[0].length.
        // For example, a 2x2 matrix and another 2x2 matrix.
        double[][] A = {
            { 1, 2 },
            { 3, 4 }
        };
        double[][] B = {
            { 5, 6 },
            { 7, 8 }
        };
        System.out.println("Matrix A:");
        matrixObj.printer(A);
        System.out.println("Matrix B:");
        matrixObj.printer(B);
        
        // Call the multiplication method. It sums element-wise products along each row.
        double[][] C = matrixObj.mul(A, B);
        if (C != null) {
            System.out.println("Result of A mul B:");
            matrixObj.printer(C);
        }
    }
}
