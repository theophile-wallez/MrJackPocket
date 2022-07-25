package front;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    //Ces méthodes ont été adaptées de méthodes trouvées sur StackOverFlow

    //Méthode pour fusionner deux images
    public static ImageIcon imageCombine(Icon imageIcon1,ImageIcon imageIcon2) throws IOException {
        BufferedImage imgBG = iconToBuffImage(imageIcon1);

        BufferedImage imgFG = new BufferedImage(
                imageIcon2.getImage().getWidth(null),
                imageIcon2.getImage().getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imgFG.createGraphics();
        // paint the Icon to the BufferedImage.
        g2.drawImage(imageIcon2.getImage(), 0, 0, null);

        //imageIcon2.paintIcon(null, g2, 0,0);
        g2.dispose();

        // For simplicity we will presume the images are of identical size
        final BufferedImage combinedImage = new BufferedImage(
                imgBG.getWidth(),
                imgBG.getHeight(),
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage(imgBG,0,0,null);
        g.drawImage(imgFG,0,0,null);
        g.dispose();
        return new ImageIcon(combinedImage);
    }



    public static ImageIcon resizeImage(ImageIcon imageIcon, int newW, int newH) {
        BufferedImage img = imageIconToBuffImage(imageIcon);
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(dimg);
    }

    public static ImageIcon resizeIcon(Icon imageIcon, int newW, int newH) {
        BufferedImage img = iconToBuffImage(imageIcon);
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(dimg);
    }


    static BufferedImage iconToBuffImage(Icon icon) {
        BufferedImage imageBuff = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g1 = imageBuff.createGraphics();
// paint the Icon to the BufferedImage.
        icon.paintIcon(null, g1, 0,0);
        g1.dispose();
        return imageBuff;
    }

    static BufferedImage imageIconToBuffImage(ImageIcon imageIcon){
        BufferedImage imageBuff = new BufferedImage(
                imageIcon.getImage().getWidth(null),
                imageIcon.getImage().getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageBuff.createGraphics();
        // paint the Icon to the BufferedImage.
        g2.drawImage(imageIcon.getImage(), 0, 0, null);

        //imageIcon2.paintIcon(null, g2, 0,0);
        g2.dispose();
        return imageBuff;
    }

    public static ImageIcon rotate(Icon imageIcon1, Double degrees) {
        BufferedImage image = new BufferedImage(
                imageIcon1.getIconWidth(),
                imageIcon1.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g1 = image.createGraphics();
// paint the Icon to the BufferedImage.
        imageIcon1.paintIcon(null, g1, 0,0);
        g1.dispose();
        // Calculate the new size of the image based on the angle of rotaion
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
        int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

        // Create a new image
        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotate.createGraphics();
        // Calculate the "anchor" point around which the image will be rotated
        int x = (newWidth - image.getWidth()) / 2;
        int y = (newHeight - image.getHeight()) / 2;
        // Transform the origin point around the anchor point
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        // Paint the originl image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return new ImageIcon(rotate);
    }
}

