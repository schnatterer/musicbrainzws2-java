/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicbrainz.coverart;

import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.CoverArtType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.mc2.util.miscellaneous.ImageHandler;

/**
 *
 * @author marco
 */
public class Image {
    
    private CoverArtImage coverArtImage;
    
    private String type="";
    private String displayComment="";
    
    private ImageIcon image; //no limits
    private ImageIcon Thumbnail150; //150
    private ImageIcon smallThumbnail; //250
    private ImageIcon largeThumbnail; //500
    
    private BufferedImage thumbnail;

    public Image(CoverArtImage coverArtImage){
        
        this.coverArtImage=coverArtImage;
        
        if (coverArtImage.isFront()) type=CoverArtType.FRONT.toString();
        else if (coverArtImage.isBack()) type=CoverArtType.BACK.toString();
        else type = calcType(coverArtImage.getTypes());

    }
    private String calcType(Set<CoverArtType> set){
    
        String out="";
        
        if (set == null || set.isEmpty()) return CoverArtType.OTHER.toString();
        if (set.size()==1) return set.toArray()[0].toString();
        
        String separator = "";
        
        Iterator i = set.iterator();
        while(i.hasNext()) {

            out=out + separator + (String) i.next();
            separator = ", ";
        }
        return out;
    }
    public long getId(){
         return coverArtImage.getId();
    }
    public boolean isApproved(){
        
        return coverArtImage.isApproved();
    }
    public String getComment() {
        return coverArtImage.getComment();
    }
    public long getEdit(){
         return coverArtImage.getEdit();
    }
    public String getType(){
        return type;
    }
    /**
     * @return the displayComment
     */
    public String getDisplayComment() {
        return displayComment;
    }
       /**
     * @return the image
     */
    public ImageIcon getImage() {
        
         if (image != null) return image;
         
        BufferedImage img;
        try {
            img = ImageIO.read(coverArtImage.getImage());
            image = new ImageIcon(img);
            
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    /**
     * @return the smallThumbnail
     */
    public ImageIcon getSmallThumbnail() {
         
        if (smallThumbnail != null) return smallThumbnail;
        if (thumbnail == null){
            try {
                
                thumbnail = ImageIO.read(coverArtImage.getSmallThumbnail());

                } catch (IOException ex) {
                    Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
         smallThumbnail = new ImageIcon(thumbnail);
         return smallThumbnail;
    }
    public ImageIcon get150Thumbnail() {
         
        if (Thumbnail150 != null) return Thumbnail150;
        if (thumbnail == null){
            smallThumbnail=getSmallThumbnail();
        }
        Thumbnail150 = new ImageIcon(ImageHandler.resizeProps(thumbnail,150,150));
        return Thumbnail150;
    }
    /**
     * @return the largeThumbnail
     */
    public ImageIcon getLargeThumbnail() {
        
                if (largeThumbnail != null) return largeThumbnail;

            BufferedImage img;
            try {
                img = ImageIO.read(coverArtImage.getLargeThumbnail());
                largeThumbnail = new ImageIcon(img);
            } catch (IOException ex) {
                Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            }
        return largeThumbnail;
    }
}
