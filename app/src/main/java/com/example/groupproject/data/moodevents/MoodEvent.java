package com.example.groupproject.data.moodevents;

import android.media.Image;

import java.util.ArrayList;

import com.example.groupproject.data.firestorehandler.FireStoreHandler;
import com.example.groupproject.data.relations.SocialSituation;
import com.example.groupproject.ui.moodlists.SortingMethod;
import com.example.groupproject.data.user.User;
import com.example.groupproject.data.moods.Mood;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.DocumentReference;

import java.util.Calendar;

import static com.example.groupproject.ui.moodlists.SortingMethod.*;

public class MoodEvent implements Comparable, FireStoreHandler.CustomFirebaseDocumentListener {
    private Mood mood;
    private Calendar timeStamp;
    private SocialSituation socialSituation;
    private String reasonText;
    private Image reasonImage;
//    private Location location;
    private LatLng latlng;
    private User owner;
    private DocumentReference documentReference;
    private FireStoreHandler.CustomFirebaseDocumentListener customFirebaseDocumentListener;


              public MoodEvent(Mood currentMood,
                               Calendar timeStamp,
                               User owner,
                               SocialSituation socialSituation,
                               String reasonText,
                               Image reasonImage,
//              Location location
                               LatLng latlng
              )

    {
        this.mood = currentMood;
        this.timeStamp = timeStamp;
        this.owner = owner;
        this.socialSituation = socialSituation; // Optional
        this.reasonText = reasonText; // Optional
        this.reasonImage = reasonImage; // Optional
//        this.setLocation(location); // Optional
        this.setLatLng(latlng);

    }

    public String getInfo(){
        if(hasLatLng())
        {
            return ("Owner: " + this.owner + ", Mood: " + this.mood + ", TimeStamp: " + this.timeStamp.toString() + ", Social Situation: " + this.socialSituation + ", LatLng: " + this.latlng.latitude + ", " + this.latlng.longitude);
        }
        else
        {
            return ("Owner: " + this.owner + ", Mood: " + this.mood + ", TimeStamp: " + this.timeStamp.toString() + ", Social Situation: " + this.socialSituation + ", LatLng: null");

        }

    }

    public Mood getMood()
    {
        return this.mood;
    }

    public Calendar getTimeStamp()
    {
        return this.timeStamp;
    }

    public SocialSituation getSocialSituation()
    {
        return this.socialSituation;
    }

    public String getReasonText()
    {
        return this.reasonText;
    }

    public Image getReasonImage()
    {
        return this.reasonImage;
    }

//    public Location getLocation()
//    {
//        return this.location;
//    }

    public LatLng getLatLng() {return this.latlng;}

    public User getOwner()
    {
        return this.owner;
    }

    public void setMood(Mood mood)
    {
        this.mood = mood;
    }

    public void setTimeStamp(Calendar timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public void setSocialSituation(SocialSituation socialSituation)
    {
        this.socialSituation = socialSituation;
    }

    public void setReasonText(String reasonText)
    {
        this.reasonText = reasonText;
    }

    public void setReasonImage(Image reasonImage)
    {
        this.reasonImage = reasonImage;
    }

    public boolean hasLatLng(){
        return this.latlng != null;
    }

    public void setLatLng(LatLng latlng){
          this.latlng = latlng;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public void setDocumentReference(DocumentReference reference){this.documentReference = reference;}
    public DocumentReference getDocumentReference(){return this.documentReference;}
    @Override
    public int compareTo(Object o) { // By default, sort by date
        return compareTo(o, DATE_OTON);
    }

    public int compareTo(Object o, SortingMethod sm)
    {
        /**
         * Returns the comparasion depending on the sorting method
         *
         * @param sm - See SortingMethod for details
         */
        switch(sm)
        {
            case NAME:
                return this.mood.compareTo(((MoodEvent) o).getMood());
            case DATE_OTON:
                return this.timeStamp.compareTo(((MoodEvent) o).getTimeStamp());
            case DATE_NTOO:
                return -1 * this.timeStamp.compareTo(((MoodEvent) o).getTimeStamp());
            case OWNER:
                return this.owner.getUserName().compareTo(((MoodEvent) o).getOwner().getUserName());
            default:
                throw new IllegalStateException("Unexpected value: " + sm);
        }
    }

    @Override
    public void onSuccess(DocumentReference documentReference) {
        // Upon successful FireStore update, update document reference
        this.setDocumentReference(documentReference);
        System.out.println("NEW DOCUMENT ID:" + this.getDocumentReference().getId());
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println(e);
    }

    public boolean contains(String query)
    {
        /**
         * Determines whether or not the moodevent contains the query in anyway, shape or form.
         *
         * Checks the following:
         * - MoodName
         * - Timestamp
         * - Owner name
         *
         * @param query - String to search by
         */
        String[] parsedQuery = query.split(" ");

        Boolean rc = false;
        ArrayList<String> checkList = new ArrayList<>();
        checkList.add(mood.getName());
        checkList.add(String.valueOf(timeStamp.get(Calendar.YEAR)));
        checkList.add(String.valueOf(timeStamp.get(Calendar.MONTH)));
        checkList.add(String.valueOf(timeStamp.get(Calendar.DATE)));
        checkList.add(owner.getUserName());

        for(String i : parsedQuery)
        {
            for(String j : checkList)
            {
                if(j.toLowerCase().contains(i.toLowerCase()))
                {
                    rc = true;
                    break;
                }
            }
        }

        return rc;
    }
}
