package org.mytests.imdb.enums;

/**
 * Created by Roman_Iovlev on 2/15/2017.
 */
public enum UserOptions {
    YourActivity("Your Activity"),
    YourLists("Your Lists"),
    YourRatings("Your Ratings"),
    RecentlyViewed("Recently Viewed");

    public String value;
    UserOptions(String value) { this.value = value; }

}
