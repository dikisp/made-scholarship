package com.diki.myprofile.data;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.Model.Profile;

import java.util.ArrayList;

public class Repository {
     static String[] profileData = new String[]{
            "",
            "10116352",
            "DIKI SUPRIADI",
            "IF - 8",
            "`I am a student majoring in informatics engineering, I like to eat a lot and eat halal foods, especially eating friends`"
    };

     static String[] contactData = new String[]{
             "",
             "",
             "",
             "082316901623",
             "dikisupriadi021@gmail.com",
             "suara_hati69",
             "suara_hati69",
             "Diki Supriadi"

     };

    public static ArrayList<Profile> getProfileData(){
        Profile p = null;

        ArrayList<Profile> list = new ArrayList<>();

            p = new Profile();
            p.setFoto(profileData[0]);
            p.setNim(profileData[1]);
            p.setNama(profileData[2]);
            p.setKelas(profileData[3]);
            p.setDeskripsi(profileData[4]);
            list.add(p);

            return list;
    }

    public static ArrayList<Friend> getContactData(){
        Friend friend = null;

        ArrayList<Friend> list = new ArrayList<>();

        friend = new Friend();
        friend.setTelepon(contactData[3]);
        friend.setEmail(contactData[4]);
        friend.setTwitter(contactData[5]);
        friend.setInstagram(contactData[6]);
        friend.setFacebook(contactData[7]);
        list.add(friend);

        return list;
    }


}
