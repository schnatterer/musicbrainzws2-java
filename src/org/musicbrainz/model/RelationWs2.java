package org.musicbrainz.model;

import java.util.*;

//import org.apache.commons.lang3.StringUtils;

import org.musicbrainz.DomainsWs2;
import org.musicbrainz.model.entity.EntityWs2;
//import org.musicbrainz.utils.MbUtils;


/**
 * <p>Represents a relation between two Entities.</p>
 * 
 * <p>There may be an arbitrary number of relations between all first
 * class objects in MusicBrainz. The RelationWs1 itself has multiple
 * attributes, which may or may not be used for a given relation
 * type.</p>
 * 
 * <p>Note that a {@link RelationWs1} object only contains the target but not
 * the source end of the relation.</p>
 * 
 */
public class RelationWs2 extends DomainsWs2{
	
	public RelationWs2() {}
		
	/**
	 * Relation target types
	 */
	public static final String TO_ARTIST = NS_REL_2_PREFIX + "artist";
           public static final String TO_LABEL = NS_REL_2_PREFIX + "label";
           public static final String TO_RECORDING = NS_REL_2_PREFIX + "recording";
	public static final String TO_RELEASE = NS_REL_2_PREFIX + "release";
           public static final String TO_RELEASE_GROUP = NS_REL_2_PREFIX + "release_group";
	public static final String TO_URL = NS_REL_2_PREFIX + "url";
           public static final String TO_WORK = NS_REL_2_PREFIX + "work";
          
	/**
	 * Relation reading directions
	 */
	public static final String DIR_BOTH = "both";
	public static final String DIR_FORWARD = "forward";
	public static final String DIR_BACKWARD = "backward";
           
           /*
         *  Attributes not on/off
         */
            
            public static final String ATTR_DESCRIPTION = "description";
            public static final String ATTR_LICENSE = "license";
            public static final String ATTR_POSITION = "position"; //proposal RFC-315
            
            public static final String ATTR_ORCHESTRA = "orchestra";
            public static final String ATTR_VOCAL = "vocal";
            public static final String ATTR_INSTRUMENT = "instrument";

	/**
	 * The relation's tpye
	 */
	private String type; 
           private String typeId; 
	
	/**
	 * The target's id
	 */
	private String targetId;
	
	/**
	 * The target's type
	 */
	private String targetType;
	
	/**
	 * One of {@link RelationWs1#DIR_FORWARD}, {@link RelationWs1#DIR_BACKWARD} or {@link RelationWs1#DIR_BOTH} 
	 */
	private String direction = DIR_BOTH;
	
	/**
	 * A list of attributes (URIs)
	 */
	private List<String> attributes;

	/**
	 * The begin date
	 */
	private String beginDate;

	/**
	 * The end date
	 */
	private String endDate;
           private boolean ended;
	/**
	 * The target entity
	 */
	private EntityWs2 target;
            
    /**
     * @return the attributes
     */
    public List<String> getAttributes() {
            return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(List<String> attributes) {
            this.attributes = attributes;
    }
    /**
     * @return the beginDate
     */
    public String getBeginDate() {
            return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
            return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
            this.direction = direction;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
            return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
            this.endDate = endDate;
    }
    /**
     * @return the relationType
     */
    public String getType() {
            return type;
    }

    /**
     * Note: This setter will prepend a (default) URI unless
     * the parameter <code>type</code> is not already an URI.
     * 
     * @param type the relationType to set
     */
    public void setType(String type) {
               this.type = type;
               
    }

    /**
     * @return the target
     */
    public EntityWs2 getTarget() {
            return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(EntityWs2 target) {
            this.target = target;
    }

    /**
     * Returns the targetId
     * 
     * @return the targetId
     */
    public String getTargetId() {
            return targetId;
    }

    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(String targetId) {
            this.targetId = targetId;
    }

    /**
     * @return the targetType
     */
    public String getTargetType() {
            return targetType;
    }

    /**
     * @param targetType the targetType to set
     */
    public void setTargetType(String targetType) {
            this.targetType = targetType;
    }

    /**
     * @return the typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the ended
     */
    public boolean isEnded() {
        return ended;
    }

    /**
     * @param ended the ended to set
     */
    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
