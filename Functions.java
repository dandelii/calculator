public class Functions {

    static double add(double a, double b) {
        return a + b;
    }

    static double subtract(double a, double b) {
        return a - b;
    }

    static double multiply(double a, double b) {
        return a * b;
    }

    static double divide(double a, double b) {
        return a / b;
    }

    static double squareRoot(double a) {
        return Math.sqrt(a);
    }

    static double nRoot(double a, double n) {
        return Math.pow(a, 1.0 / n);
    }

    static double square(double a) {
        return Math.pow(a, 2);
    }

    static double nPower(double a, double n) {
        return Math.pow(a, n);
    }

    static double ln(double a) {
        return Math.log(a);
    }

    static double log(double a, double b) {
        return Math.log(a) / Math.log(b);
    }

    static double e(double a) {
        return Math.exp(a);
    }

    static double sine(double a) {
        return Math.sin(a);
    }

    static double cosine(double a) {
        return Math.cos(a);
    }

    static double tangent(double a) {
        return Math.tan(a);
    }

    static double arcsine(double a) {
        return Math.asin(a);
    }

    static double arccos(double a) {
        return Math.acos(a);
    }

    static double arctangent(double a) {
        return Math.atan(a);
    }

    static double secant(double a) {
        return 1 / cosine(a);
    }

    static double cosecant(double a) {
        return 1 / sine(a);
    }

    static double cotangent(double a) {
        return 1 / tangent(a);
    }

}
