public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, no bit-wise operations are allowed.
     */
    public static void main(String[] argv) {
        int length = 0;
        int value = 1;
        while (value != 0) {
            value <<= 1;
            length++;
        }
        System.out.println("The length of an int in bits is: " + length);
    }
}
