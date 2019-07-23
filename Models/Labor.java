package Models;

import Models.Buildings.Building;

public class Labor {

    boolean isFree = true ;
    private Building workPlace ;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Building getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Building workPlace) {
        this.workPlace = workPlace;
    }
}
