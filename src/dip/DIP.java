package dip;

import org.opencv.core.Core;

public class DIP {

    public static void main(String[] args) {
        //opencv_java300 is your version dll file eg. if you hve opencv2.4.2 then load "opencv_java242"
        System.loadLibrary("opencv_java300");
        //to know your opencv version
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        //to read image ; convert into matrix form
        //Mat m = Imgcodecs.imread("C:/Users/JAY/Desktop/image/img.jpg");
        //display matrix only if you have small size image like 1 or 2 kb unless below stat. takes endless time 
        //System.out.println("OpenCV Mat data:\n" + m.dump());
        //call to write image ; convert back to image from matrix
        //new LoadImage("writeimg1.jpg", m);
        new GUI();

    }
}
