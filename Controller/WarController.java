package Controller;

import Enums.Resource;
import Exceptions.InvalidPoint;
import Exceptions.InvalidSoldier;
import Exceptions.NotEnoughSoldier;
import GameModes.War;
import Models.Position.Map;
import Models.Position.Point;
import Models.Soldiers.Soldier;
import Models.Village;
import java.util.ArrayList;

public class WarController {

    private War war;

    private Village attackerVillage;

    private ArrayList<Soldier> calledSoldiers = new ArrayList(0);

    private ArrayList<Point> occupiedPoints = new ArrayList<>(0);

    public WarController(War war, Village attackerVillage) {
        this.war = war;
        this.attackerVillage = attackerVillage;
    }

    public WarController() {
        super();
    }

    public Village getAttackerVillage() {
        return attackerVillage;
    }

    public void setAttackerVillage(Village attackerVillage) {
        this.attackerVillage = attackerVillage;
    }

    public ArrayList<Soldier> getCalledSoldiers() {
        return calledSoldiers;
    }

    public void setCalledSoldiers(ArrayList<Soldier> calledSoldiers) {
        this.calledSoldiers = calledSoldiers;
    }

    public War getWar() {
        return war;
    }

    public void setWar(War war) {
        this.war = war;
    }

    public void callTroops(Class soldierType, int number) throws NotEnoughSoldier {

        if (attackerVillage.availableNumberOf(soldierType) < number)
            throw new NotEnoughSoldier();

        Soldier callingSoldier = null;

        for (int i = 0; i < number; i++) {
            callingSoldier = attackerVillage.callSoldier(soldierType);
            calledSoldiers.add(callingSoldier);
        }

    }
    public void deploySoldier (Class soldierType , Point position, int number) throws InvalidPoint, InvalidSoldier {

        for (int i = 0; i < number; i++) {
            war.deploySoldier(soldierType, position);
            occupiedPoints.add(position);
        }

    }

    public void returnAliveSoldiers(){
        for (Soldier soldier : war.getFreshSoldiers()){
            getAttackerVillage().hostSoldier(soldier);
        }
    }

    public void startWar(Map map){

        War newWar = new War(map ,calledSoldiers);
        this.setWar(newWar);
    }

    public void freeOccupiedCells(){

        for (Point point: occupiedPoints) {
            getWar().getWarMap().getCell(point).setSoldiersJustDeployedNumber(0);
        }

        occupiedPoints.clear();
    }

    public void claimPrize(){

        attackerVillage.collectResource(Resource.GOLD, war.getLoot().get(Resource.GOLD));
        attackerVillage.collectResource(Resource.ELIXIR, war.getLoot().get(Resource.ELIXIR));
        attackerVillage.getTownhall().setScore(attackerVillage.getTownhall().getHitScore() + war.getLoot().get(Resource.SCORE));


    }

    public boolean warIsOn(){


        if (attackerVillage.totalCapacityLeft().get(0) < war.getLoot().get(Resource.GOLD) && attackerVillage.totalCapacityLeft().get(1) < war.getLoot().get(Resource.ELIXIR)) {
            return false;
        }

        return war.isWarOn();

    }
    public void quitWar(){

        returnAliveSoldiers();
        claimPrize();
    }

}
