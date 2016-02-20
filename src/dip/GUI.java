/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dip;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class GUI extends JFrame {

    JLabel l1, l2;
    JTextField t1;
    JButton b1, b2;
    BufferedImage icon;
    //JLabel lable;
    static File fFile;
    Mat m = null;

    public GUI() {

        final Container c = getContentPane();
        c.setLayout(new FlowLayout());

        setTitle("Face Detector");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Select a target image :");
        t1 = new JTextField(20);
        b1 = new JButton("Browse");
        b2 = new JButton("Detect");
        l2 = new JLabel();
        //lable = new JLabel();

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.print("heeee2");
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Open File");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setCurrentDirectory(new File("."));

                int result = fc.showOpenDialog(null);
                // if (result == JFileChooser.CANCEL_OPTION) {
                //} else 
                if (result == JFileChooser.APPROVE_OPTION) {

                    fFile = fc.getSelectedFile();
                    m = Imgcodecs.imread(fFile.getAbsolutePath());
                    t1.setText(fFile.getAbsolutePath());

                    //m.release();
                }
            }
        });
        b2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("\nRunning FaceDetector");

                        CascadeClassifier faceDetector = new CascadeClassifier("G:/TAJ/opencv/build/etc/haarcascades/haarcascade_frontalface_alt.xml");
                        MatOfRect faceDetections = new MatOfRect();
                        faceDetector.detectMultiScale(m, faceDetections);

                        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
                        l2.setText("Detected faces : " + faceDetections.toArray().length);
                        for (Rect rect : faceDetections.toArray()) {
                            Imgproc.rectangle(m, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                   new Scalar(0, 255, 0));
                        }

                        String filename = "ouput.png";
                        System.out.println(String.format("Writing %s", filename));
                        
                        Imgcodecs.imwrite(filename, m);
                        ImageIcon image = new ImageIcon(filename);
                        //Image img = image.getImage();
                        //Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_SMOOTH);
                        //ImageIcon newIcon = new ImageIcon(newimg);
                        //image.getIconHeight();
                        JLabel label1 = new JLabel(" ", image, JLabel.CENTER);

                        c.add(label1);
                        
                        faceDetections.release();
                        m.release();
                    }
                });

        c.add(l1);
        c.add(t1);
        c.add(b1);
        c.add(b2);
        c.add(l2);
       // c.add(lable);
        validate();
        setVisible(true);

    }
}
