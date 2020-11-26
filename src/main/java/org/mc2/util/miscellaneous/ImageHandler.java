package org.mc2.util.miscellaneous;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

/**
 * 
 */
public class ImageHandler {

    public ImageHandler(){

   }
   public static Boolean isFileAnImage(String pathname)
   {
        java.io.File file = new java.io.File(pathname);
        return isFileAnImage(file);
 
    }
    public static Boolean isFileAnImage(File file)
    {
        BufferedImage img = null;
        try {
                  img = ImageIO.read(file);

                  if (img!=null)
                  {
                      return true;
                  }
        }
        catch (IOException e)
                { System.err.println(e.toString()); }

        return false;
 
    } 
    public  static BufferedImage getImagefromFile (java.io.File file){

         if (file== null){return null;}
         if (!file.exists()) {return null;}

         try {BufferedImage img = ImageIO.read(file);
               return img;
              }
        catch (IOException e){
            System.err.println(e.toString());
            return null;}

     }
     public  static BufferedImage getImagefromFile (String pathname){
    
        if (pathname == null) {return null;}

        File file  = new File(pathname);

        BufferedImage img = getImagefromFile(file);

        return img;
  
    }
     public  static BufferedImage getImagefromInputStream (InputStream in) throws IOException{
        
            BufferedImage img;
            img = ImageIO.read(in);
            return img;

    }
    public static BufferedImage resize(BufferedImage img,
                                int width,
                                int height){
        
        if (img==null){return null;}
        
        if (img.getHeight() != width ||
               img.getWidth() !=  height)
        {
              img = resizeImageWithHint(img,width,height);
        }
        
        return img;
        
    }
     public static BufferedImage resizeProps(BufferedImage img,
                                int width,
                                int height){
        
        if (img==null){return null;}
        
        if (img.getHeight() != width ||
               img.getWidth() !=  height)
        {
              img = resizeImageProp(img,width,height);
        }
        
        return img;
        
    }
    private static BufferedImage resizeImageProp (BufferedImage originalImage,
                                                int width,
                                                int height){
        
        int w = originalImage.getWidth();
        int h = originalImage.getHeight();
        

        float wRatio = (float)width/(float)w;
        float hRatio = (float)height/(float)h;
        
        float ratio = (wRatio>hRatio) ? hRatio : wRatio;
        
        int newW = (int) Math.rint( w*ratio );
        int newH = (int) Math.rint( h*ratio );

        return resizeImageWithHint(originalImage, newW, newH);
    }
    private static BufferedImage resizeImageWithHint(
                                                BufferedImage originalImage,
                                                int width,
                                                int height){

        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizedImage = new BufferedImage(width, height, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, width, height, null);
	g.dispose();
	g.setComposite(AlphaComposite.Src);

	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);

	return resizedImage;
    }
    public static void writeImageToFile(BufferedImage image,
                                           java.io.File file,
                                           String format) throws IOException{
        
        ImageIO.write(image, format, file);
    }
}
