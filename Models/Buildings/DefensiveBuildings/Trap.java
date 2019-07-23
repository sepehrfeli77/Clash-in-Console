package Models.Buildings.DefensiveBuildings;

import Functions.Functions;
import Interfaces.Attacker;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Cell;
import Models.Position.Map;
import Models.Position.Point;
import Models.Soldiers.Archer;
import Models.Soldiers.Giant;
import Models.Soldiers.Guardian;
import Models.Soldiers.WallBreaker;
import Models.Village;

import java.util.ArrayList;

public class Trap extends Building implements Attacker {


    public Trap() {

        setTypeCode(13);

        setMaxHealth(100);
        setHealth(getMaxHealth());

        setLevel(0);
        setHitScore(1);
        setSize(1);

        upgradeCost = 100;  //  gold
        upgradeTime = 40;  // delta T

        buildingCost = 100;  // gold
        buildingTime = 40;

        mainTargets.add(Archer.class);
        mainTargets.add(Giant.class);
        mainTargets.add(Guardian.class);
        mainTargets.add(WallBreaker.class);

    }

    private ArrayList<Class> mainTargets = new ArrayList<>(1);

    private int effectRadius = 1; // delta L
    private int damage = 100; // gooli

    boolean isHide = true;
    transient boolean isActive = true;


    public int getEffectRadius() {
        return effectRadius;
    }

    public void setEffectRadius(int effectRadius) {
        this.effectRadius = effectRadius;
    }


    @Override
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void startConstructing(Village village, int x, int y) {

        for (Labor l : village.getTownhall().getLabors()) {

            if (l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this);
                village.getTraps().add(this);
                village.getMap().getCell(y, x).getOnGroundThings().add(this);
                new Functions().decreaseGold(village, this.getBuildingCost());

                break;
            }
        }
    }

    @Override
    public int getAttackRange() {
        return 1;
    }


    @Override
    public Cell selectTarget(Map map) {
        Point target;
        for (int i = 1; i <= getAttackRange(); i++) {
            for (int j = 0; j <= i; j++) {
                target = map.verticalSearch(this.getPosition(), i, j, mainTargets);
                if (target == null)
                    target = map.horizontalSearch(this.getPosition(), i, j, mainTargets);
                if (target != null)
                    return map.getCell(target);
            }
        }

        return null;
    }

    @Override
    public void attack(Cell target) {

    }

    @Override
    public void passOneTurn(Map map) {

    }

    public void startUpGrading(Village village) {

        for (Labor l : village.getTownhall().getLabors()) {

            if (l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this);
                new Functions().decreaseGold(village, this.getBuildingCost());
                setOnUpgrade(true);
                setActive(false);
                break;
            }
        }
    }

    @Override
    public void upgrade() {

        this.setLevel(this.getLevel() + 1);
        this.damage++;
        this.setMaxHealth(this.getMaxHealth() + 10);
        this.setHealth(this.getMaxHealth());
        setOnUpgrade(false);
        setActive(true);
    }
}
