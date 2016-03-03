/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dip;

import java.util.List;
import java.util.Vector;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.imgproc.Imgproc;

public class RbgToYcbcr {

    public static void main(String[] args) {

        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            /*File input = new File("C:/Users/JAY/Desktop/image/face.jpg");
             BufferedImage image = ImageIO.read(input);
             byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
             Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
             mat.put(0, 0, data);
             //Mat mat = Imgcodecs.imread("C:/Users/JAY/Desktop/image/face.jpg");
             //Mat mat1 = new Mat();
             Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
             Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2YCrCb);

             byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
             mat1.get(0, 0, data1);
             BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), 5);
             image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

             File ouptut = new File("ycbcr23.jpg");
             ImageIO.write(image1, "jpg", ouptut);*/

            System.out.println("Writing1.... ");

            Mat m = new Mat();
            m = Imgcodecs.imread("C:/Users/JAY/Desktop/image/face.jpg");

            Mat ycbcr = new Mat();
            Imgproc.cvtColor(m, ycbcr, Imgproc.COLOR_RGB2YCrCb);
            Imgcodecs.imwrite("ycbcrimg.jpg", ycbcr);

            // System.out.println("Writing2.... ");
            List<Mat> chan = new Vector<Mat>();
            Core.split(ycbcr, chan);
            Mat y = chan.get(0);
            Mat cb = chan.get(1);
            Mat cr = chan.get(2);
            // Mat C = ycbcr.clone();
            Imgcodecs.imwrite("ycomponet.jpg", y);
            Imgcodecs.imwrite("cbcomponet.jpg", cb);
            Imgcodecs.imwrite("crcomponet.jpg", cr);

            // Mat img = Imgcodecs.imread("Input.jpg"); //Reads image from the file system and puts into matrix
            int rows = ycbcr.rows(); //Calculates number of rows
            int cols = ycbcr.cols(); //Calculates number of columns
            int ch = ycbcr.channels(); //Calculates number of channels (Grayscale: 1, RGB: 3, etc.)
            System.out.println("rows.... " + rows);
            System.out.println("coloums.... " + cols);
            System.out.println("channels.... " + ch);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    double[] data = ycbcr.get(i, j); //Stores element in an array
                    //for (int k = 0; k < ch; k++){ //Runs for the available number of channels

                    if (data[0] > 80) {
                        if (80 < data[1] && data[1] < 130) {
                            if (150 < data[2] && data[2] < 180) {
                                data[0] = 255;
                                data[1] = 255;
                                data[2] = 255; //Pixel modification done here
                            }
                        }
                    }
                    ycbcr.put(i, j, data); //Puts element back into matrix
                }
            }
            Imgcodecs.imwrite("output.jpg", ycbcr); //Writes image back to the file system using values of the modified matrix

            m.release();
            ycbcr.release();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
