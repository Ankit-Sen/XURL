package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XUrlImpl implements XUrl{

    private Map<String,String> SU;
    private Map<String,String> LU;
    private Map<String,Integer> count;

    public XUrlImpl(){
        SU= new HashMap<>();
        LU= new HashMap<>();
        count= new HashMap<>();
    }

    // public String Display(){
    //     System.out.println(LU);
    //     System.out.println(SU);
    //     return "";
    // }

    public static String generateString() {
        {
            // Create a random object
            Random random = new Random();
            // Create a string buffer to store the random string
            StringBuffer sb = new StringBuffer();
            // Generate a random 9 digit alphanumeric string
            for (int i = 0; i < 9; i++) {
                int randomInt = random.nextInt(36); // 0 to 35
                char randomChar = (char) (randomInt < 10 ? randomInt + 48 : randomInt + 87); // 0 to 9 or A to Z
                sb.append(randomChar);
            }
            return sb.toString();
        }
    }

    // If longUrl already has a corresponding shortUrl, return that shortUrl
    // If longUrl is new, create a new shortUrl for the longUrl and return it
    public String registerNewUrl(String longUrl){

        if(SU.containsKey(longUrl))
            return SU.get(longUrl);
        else{
            String shortUrl=setShortUrl(longUrl);
            SU.put(longUrl,shortUrl);
            LU.put(shortUrl, longUrl);
            count.put(longUrl,0);
            return shortUrl;
        }
    }

    private String setShortUrl(String longUrl) {
        String shortUrl = "http://short.url/"+generateString();
        return shortUrl;
    }

    // If shortUrl is already present, return null
    // Else, register the specified shortUrl for the given longUrl
    // Note: You don't need to validate if longUrl is already present,
    //       assume it is always new i.e. it hasn't been seen before
    public String registerNewUrl(String longUrl, String shortUrl){

        if(LU.containsKey(shortUrl))
            return null;
        else{
            SU.put(longUrl,shortUrl);
            LU.put(shortUrl,longUrl);
            count.put(longUrl,0);
            return shortUrl;
        }
    }


    // If shortUrl doesn't have a corresponding longUrl, return null
    // Else, return the corresponding longUrl
    public String getUrl(String shortUrl){
        if(LU.containsKey(shortUrl)){
            String longUrl=LU.get(shortUrl);
            count.put(longUrl,count.get(longUrl)+1);
            return LU.get(shortUrl);
            //check
        }
        else
            return null;
    }

    // Return the number of times the longUrl has been looked up using getUrl()
    public Integer getHitCount(String longUrl){
        //String shortUrl=SU.get(longUrl);
        if(count.containsKey(longUrl))
            return count.get(longUrl);
        else
            return 0;

    }
    // Delete the mapping between this longUrl and its corresponding shortUrl
    // Do not zero the Hit Count for this longUrl
    public String delete(String longUrl){
        String shortUrl=SU.get(longUrl);
        SU.remove(longUrl);
        LU.remove(shortUrl);
        return shortUrl;
    }
}