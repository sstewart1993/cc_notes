import attractions.Attraction;
import behaviours.IReviewed;
import behaviours.ISecurity;
import people.Visitor;
import stalls.Stall;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemePark {

    private ArrayList<Stall> stalls;
    private ArrayList<Attraction> attractions;

    public ThemePark() {
        stalls = new ArrayList<Stall>();
        attractions = new ArrayList<Attraction>();
    }

    public void addAttraction(Attraction attraction){
        this.attractions.add(attraction);
    }

    public void addStall(Stall stall){
        this.stalls.add(stall);
    }

    public int getAttractionCount(){
        return this.attractions.size();
    }

    public int getStallCount(){
        return this.stalls.size();
    }

    public ArrayList<IReviewed> getReviewed() {
        ArrayList<IReviewed> reviewed = new ArrayList<IReviewed>();
        reviewed.addAll(this.attractions);
        reviewed.addAll(this.stalls);
        return reviewed;
    }

    public ArrayList<IReviewed> getAllAllowed(Visitor visitor) {
        ArrayList<IReviewed> allowedAttractions = new ArrayList<IReviewed>();
        for (Attraction attraction : attractions){
            if (attraction instanceof ISecurity){
                if (((ISecurity) attraction).isAllowed(visitor)){
                    allowedAttractions.add(attraction);
                }
            } else {
                allowedAttractions.add(attraction);
            }
        }

        return allowedAttractions;
    }

    public void visit(Visitor visitor, Attraction attraction){
        attraction.incrementVisitCount();
        visitor.addVisitedAttraction(attraction);
    }

    public HashMap<String, Integer> allReviews(){
       HashMap<String, Integer> reviews = new HashMap<String, Integer>();;
        for (IReviewed reviewed: getReviewed() ){
            reviews.put(reviewed.getName(), reviewed.getRating());
        }
        return reviews;
    }
}
