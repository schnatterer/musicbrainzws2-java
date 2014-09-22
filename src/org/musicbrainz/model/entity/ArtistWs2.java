package org.musicbrainz.model.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import org.musicbrainz.model.LifeSpanWs2;
import org.musicbrainz.model.entity.listelement.RecordingListWs2;
import org.musicbrainz.model.entity.listelement.ReleaseGroupListWs2;
import org.musicbrainz.model.entity.listelement.ReleaseListWs2;
import org.musicbrainz.model.entity.listelement.WorkListWs2;

/**
 * <p>Represents an artist.</p>
 * 
 */
public class ArtistWs2 extends EntityWs2 {
	
	public static final String TYPE_GROUP = NS_MMD_2 + "Group";
	public static final String TYPE_PERSON = NS_MMD_2 + "Person";
	
	/**
	 * The artist's type (as an absolute URI).
	 */
           private String type;
    
	/**
	 * The artist's name.
	 */
	private String name;
	
	/**
	 * The sort name is the artist's name in a special format which
	 * is better suited for lexicographic sorting. The MusicBrainz
	 * style guide specifies this format.
	 */
	private String sortName;

	private LifeSpanWs2 lifespan;
           
           private AreaWs2 beginArea;
           private AreaWs2 endArea;
        
	private String disambiguation;
	
            /**
	 * The artist's country.
	 */
           private String country;
           private AreaWs2 area;
            /**
	 * The artist's gender.
	 */
           private String gender;
            /**
	 * The artist's ipi.
	 */
           private String ipi;
           private List<String> ipiList= new ArrayList<String>();
           private List<String> isniList = new ArrayList<String>();

	/**
	 * The list of releases from this artist.

	 */
	private ReleaseListWs2 releaseList = new ReleaseListWs2();
        	 
        /* 
	 * This One only  include releases where this artist isn't the
	 * main artist but has just contributed one or more tracks
	 * (aka VA-Releases).
         * 
         */
           private ReleaseListWs2 releaseListVA = new ReleaseListWs2();
           
           private ReleaseGroupListWs2 releaseGroupList = new ReleaseGroupListWs2();
           private RecordingListWs2 recordingList = new RecordingListWs2();
           private WorkListWs2 workList = new WorkListWs2();
           
           
           // is in mmd but not allowed in ws.
           //private LabelListWs2 labelList = new LabelListWs2();
          /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
           /**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}
	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	/**
	 * @return the disambiguation
	 */
	public String getDisambiguation() {
		return disambiguation;
	}
	/**
	 * @param disambiguation the disambiguation to set
	 */
	public void setDisambiguation(String disambiguation) {
		this.disambiguation = disambiguation;
	}
   	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
         * @return the LifeSpan
	*/
           public LifeSpanWs2 getLifeSpan(){
               return lifespan;
           }
           /**
	 * @param LifeSpan the LifeSpan to set
        */
           public void setLifeSpan(LifeSpanWs2 lifespan){
               this.lifespan=lifespan;
           }
           /**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
        	 /**
         * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param country the country to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
            /**
             * @return the beginArea
             */
            public AreaWs2 getBeginArea() {
                return beginArea;
            }
            /**
             * @param beginArea the beginArea to set
             */
            public void setBeginArea(AreaWs2 beginArea) {
                this.beginArea = beginArea;
            }
            /**
             * @return the endArea
             */
            public AreaWs2 getEndArea() {
                return endArea;
            }
            /**
             * @param endArea the endArea to set
             */
            public void setEndArea(AreaWs2 endArea) {
                this.endArea = endArea;
            }

            /**
             * @return the area
             */
            public AreaWs2 getArea() {
                return area;
            }
            /**
             * @param area the area to set
             */
            public void setArea(AreaWs2 area) {
                this.area = area;
            }
             /**
            * @return the ipi
            */
              public String getIpi() {
                    return ipi;
              }
              /**
            * @param ipi the ipi to set
            */
              public void setIpi(String ipi) {
                    this.ipi = ipi;
                }
            /**
         * @param ipiList the ipiList to set
         */
            public void setIpiList(List<String> ipiList) {
                this.ipiList = ipiList;
            }
            /**
         * @return the ipiList
         */
            public List<String> getIpiList() {
                return ipiList;
            }
            public String getDisplayIpi() {
                
                 String out="";
                 if ((getIpiList()==null) || (getIpiList().isEmpty())) return out;

                 if ((getIpiList().size()==1)&& 
                      (getIpiList().get(0).equals(getIpi()))) return getIpi();

                if (getIpiList().size()>1){
                    String separator="";
                    Iterator itr = getIpiList().iterator();
                    while (itr.hasNext()) {

                        String sType = (String)itr.next();
                        out=out+separator+sType;
                        separator=", ";

                    }
                    return out;
                }
                return getIpi()+" / "+getIpiList().get(0);
            }
             /**
         * @param isniList the isniList to set
         */
            public void setIsniList(List<String> isniList) {
                this.isniList = isniList;
            }
            /**
             * @return the isniList
             */
            public List<String> getIsniList() {
                return isniList;
            }
            public String getDisplayIsni() {

                String out="";
                if (!(getIsniList()==null) && !(getIsniList().isEmpty())){

                    String separator="";
                    Iterator itr = getIsniList().iterator();
                    while (itr.hasNext()) {

                        String sType = (String)itr.next();
                        out=out+separator+sType;
                        separator=", ";

                     }
                }
                return out;
           }
           /**
	* Gets the underlying <code>List</clode> of releaseGroups.
        * 
	* @return the releaseGroups
	*/
	public List<ReleaseGroupWs2> getReleaseGroups() {
                return ( releaseGroupList == null ? null : releaseGroupList.getReleaseGroups());
	}
	/**
	 * Sets the underlying <code>List</clode> of releaseGroups.
	 * 
	 * Note: This will implicitly create a new {@link #releaseGroupList}
	 * if it is null.
	 * 
	 * @param releaseGroups the releaseGroups to set
	 */
	public void setReleaseGroups(List<ReleaseGroupWs2> releaseGroups) 
	{
		if (releaseGroupList == null) {
			releaseGroupList = new ReleaseGroupListWs2();
		}
			
		this.releaseGroupList.setReleaseGroups(releaseGroups);
	}
            /**
	 * @return the releaseGroupList
	 */
	public ReleaseGroupListWs2 getReleaseGroupList() {
		return releaseGroupList;
	}

	/**
	 * @param releaseList the releaseGroupList to set
	 */
	public void setReleaseGroupList(ReleaseGroupListWs2 releaseGroupList) {
		this.releaseGroupList = releaseGroupList;
	}
            /**
	 * <p>Adds a releaseGroup to the underlying <code>List</clode>
	 * of releaseGroups.</p>
	 * 
	 * <p><em>Note: This will implicitly create a new {@link #releaseGroupList}
	 * if it is null.</em></p>
	 * 
	 * @param releaseGroup The {@link ReleaseGroupWs2} to add.
	 */
	public void addReleaseGroup(ReleaseGroupWs2 releaseGroup) 
	{
		if (releaseGroupList == null) {
			releaseGroupList = new ReleaseGroupListWs2();
		} 
		releaseGroupList.addReleaseGroup(releaseGroup);
	}
	/**
	 * Gets the underlying <code>List</clode> of releases.
	 * 
	 * @return the releases
	 */
	public List<ReleaseWs2> getReleases() {
		return ( releaseList == null ? null : releaseList.getReleases());
	}
	/**
	 * Sets the underlying <code>List</clode> of releases.
	 * 
	 * Note: This will implicitly create a new {@link #releaseList}
	 * if it is null.
	 * 
	 * @param releases the releases to set
	 */
	public void setReleases(List<ReleaseWs2> releases) 
	{
		if (releaseList == null) {
			releaseList = new ReleaseListWs2();
		}
			
		this.releaseList.setReleases(releases);
	}
            /**
	 * @return the releaseList
	 */
	public ReleaseListWs2 getReleaseList() {
		return releaseList;
	}

	/**
	 * @param releaseList the releaseList to set
	 */
	public void setReleaseList(ReleaseListWs2 releaseList) {
		this.releaseList = releaseList;
	}
            /**
	 * <p>Adds a release to the underlying <code>List</clode>
	 * of releases.</p>
	 * 
	 * <p><em>Note: This will implicitly create a new {@link #releaseList}
	 * if it is null.</em></p>
	 * 
	 * @param release The {@link ReleaseWs1} to add.
	 */
	public void addRelease(ReleaseWs2 release) 
	{
		if (releaseList == null) {
			releaseList = new ReleaseListWs2();
		} 
		releaseList.addRelease(release);
	}
        /**
	 * Gets the underlying <code>List</clode> of releases.
	 * 
	 * @return the releases
	 */
	public List<ReleaseWs2> getReleasesVA() {
		return ( releaseListVA == null ? null : releaseListVA.getReleases());
	}
	/**
	 * Sets the underlying <code>List</clode> of releases.
	 * 
	 * Note: This will implicitly create a new {@link #releaseList}
	 * if it is null.
	 * 
	 * @param releases the releases to set
	 */
	public void setReleasesVA(List<ReleaseWs2> releasesVA) 
	{
		if (releaseListVA == null) {
			releaseListVA = new ReleaseListWs2();
		}
			
		this.releaseListVA.setReleases(releasesVA);
	}
            /**
	 * @return the releaseList
	 */
	public ReleaseListWs2 getReleaseListVA() {
		return releaseListVA;
	}

	/**
	 * @param releaseList the releaseList to set
	 */
	public void setReleaseListVA(ReleaseListWs2 releaseListVA) {
		this.releaseListVA = releaseListVA;
	}
            /**
	 * <p>Adds a release to the underlying <code>List</clode>
	 * of releases.</p>
	 * 
	 * <p><em>Note: This will implicitly create a new {@link #releaseList}
	 * if it is null.</em></p>
	 * 
	 * @param release The {@link ReleaseWs1} to add.
	 */
	public void addReleaseVA(ReleaseWs2 releaseVA) 
	{
		if (releaseListVA == null) {
			releaseListVA = new ReleaseListWs2();
		} 
		releaseListVA.addRelease(releaseVA);
	}
           /**
	 * Gets the underlying <code>List</clode> of releases.
	 * 
	 * @return the recordings
	 */
	public List<RecordingWs2> getRecordings() {
		return ( recordingList == null ? null : recordingList.getRecordings());
	}
	/**
	 * Sets the underlying <code>List</clode> of recordings.
	 * 
	 * Note: This will implicitly create a new {@link #recordingList}
	 * if it is null.
	 * 
	 * @param recordings the recordings to set
	 */
	public void setRecordings(List<RecordingWs2> recordings) 
	{
		if (recordingList == null) {
			recordingList = new RecordingListWs2();
		}
			
		this.recordingList.setRecordings(recordings);
	}
            /**
	 * @return the recordingList
	 */
	public RecordingListWs2 getRecordingList() {
		return recordingList;
	}

	/**
	 * @param recordingList the recordingList to set
	 */
	public void setRecordingList(RecordingListWs2 recordingList) {
		this.recordingList = recordingList;
	}
            /**
	 * <p>Adds a recording to the underlying <code>List</clode>
	 * of recordings.</p>
	 * 
	 * <p><em>Note: This will implicitly create a new {@link #recordingList}
	 * if it is null.</em></p>
	 * 
	 * @param recordinge The {@link RecordingWs2} to add.
	 */
	public void addRecording(RecordingWs2 recording) 
	{
		if (recordingList == null) {
			recordingList = new RecordingListWs2();
		} 
		recordingList.addRecording(recording);
	}
        /**
	 * Gets the underlying <code>List</clode> of works.
	 * 
	 * @return the works
	 */
	public List<WorkWs2> getWorks() {
		return ( workList == null ? null : workList.getWorks());
	}
	/**
	 * Sets the underlying <code>List</clode> of works.
	 * 
	 * Note: This will implicitly create a new {@link #workList}
	 * if it is null.
	 * 
	 * @param works the works to set
	 */
	public void setWorks(List<WorkWs2> works) 
	{
		if (workList == null) {
			workList = new WorkListWs2();
		}
			
		this.workList.setWorks(works);
	}
            /**
	 * @return the workList
	 */
	public WorkListWs2 getWorkList() {
		return workList;
	}

	/**
	 * @param workList the workList to set
	 */
	public void setWorkList(WorkListWs2 workList) {
		this.workList = workList;
	}
            /**
	 * <p>Adds a work to the underlying <code>List</clode>
	 * of works.</p>
	 * 
	 * <p><em>Note: This will implicitly create a new {@link #workList}
	 * if it is null.</em></p>
	 * 
	 * @param work The {@link workWs2} to add.
	 */
	public void addWork(WorkWs2 work) 
	{
		if (workList == null) {
			workList = new WorkListWs2();
		} 
		workList.addWork(work);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
         * 
	 * Returns a unique artist name using disambiguation
	 * (if available).
	 * 
	 * This method returns the artist name together with the
	 * disambiguation attribute in parenthesis if it exists.
	 * Example: 'Vixen (Hip-hop)'.
	 */
	public String getUniqueName()
	{
		if (StringUtils.isNotBlank(disambiguation)) {
			return name + " (" + disambiguation + ")";
		}
		return name;
	}
           @Override
	public String toString() {
		return getUniqueName();
	}
           @Override
            public boolean equals(Object object) {
                if (!(object instanceof ArtistWs2)) {
                    return false;
                }
                ArtistWs2 other = (ArtistWs2) object;
                if (this.getIdUri().equals(other.getIdUri()))
                {
                    return true;
                }

                return false;
            }
}
