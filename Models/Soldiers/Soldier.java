package Models.Soldiers;

import Enums.Field;
import Models.Entity;
import Models.Position.Cell;
import Models.Position.Map;

public class  Soldier extends Entity{

    private int BuildingTime ;

    private int BuildingCost ;

    private int effectRadius ;

    private Field movementField;

    private int trainTime ;

    private int speed ;

    private int hitPoint ;

    private int damage ;

    public int getEffectRadius() {
        return effectRadius;
    }

    public void setEffectRadius(int effectRadius) {
        this.effectRadius = effectRadius;
    }

    public int getBuildingTime() {
        return BuildingTime;
    }

    public void setBuildingTime(int buildingTime) {
        BuildingTime = buildingTime;
    }

    public int getBuildingCost() {
        return BuildingCost;
    }

    public void setBuildingCost(int buildingCost) {
        BuildingCost = buildingCost;
    }

    public Field getMovementField() {

        return movementField;

    }

    public void setMovementField(Field movementField) {

        this.movementField = movementField;

    }


    public int getTrainTime() {

        return trainTime;

    }

    public void setTrainTime(int trainTime) {

        this.trainTime = trainTime;

    }


    public int getSpeed() {

        return speed;

    }

    public void setSpeed(int speed) {

        this.speed = speed;

    }


    public int getHitPoint() {

        return hitPoint;

    }

    public void setHitPoint(int hitPoint) {

        this.hitPoint = hitPoint;

    }


    public int getDamage() {

        return damage;

    }

    public void setDamage(int damage) {

        this.damage = damage;

    }

    public void passOneTurn(Map map){

    }

    public void move(Map map){

    }

    public void attack(Cell target) {
//
//        ((Entity) target.getOnGroundThings().get(0)).setHealth( ((Entity) target.getOnGroundThings().get(0)).getHealth() - this.getDamage() ) ;
//
//        if( ((Entity) target.getOnGroundThings().get(0)).getHealth() <=0 ) {
//
//            ((Entity) target.getOnGroundThings().get(0)).setDestroyed( true );
//            ((Entity) target.getOnGroundThings().get(0)).setHealth(0) ;
//        }
    }


}
