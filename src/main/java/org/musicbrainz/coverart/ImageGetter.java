/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musicbrainz.coverart;

import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author marco
 */
public class ImageGetter {
    
    private static ImageGetter _instance;
    private CoverArtArchiveClient client;
    
    private ImageGetter (){
        client = new DefaultCoverArtArchiveClient();
    } 
    public static ImageGetter getInstance() {
        
        if (_instance == null) _instance = new ImageGetter();
       
        return _instance;
    } 
    
     public List<Image> getImageListByMbID(String id){

        UUID mbid = UUID.fromString(id);

        CoverArt coverArt = null;
        List<Image> ImageList = new ArrayList<Image>(0);

          coverArt = client.getByMbid(mbid);         
          if (coverArt != null) {
            for (CoverArtImage coverArtImage : coverArt.getImages()) {
              
                Image image = new Image(coverArtImage);
                ImageList.add(image);
                
            }
         }
         return ImageList;
    }
}
