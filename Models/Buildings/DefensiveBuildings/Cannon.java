package Models.Buildings.DefensiveBuildings;

import Functions.Functions;
import Interfaces.Attacker;
import Models.Buildings.Building;
import Models.Entity;
import Models.Labor;
import Models.Position.Cell;
import Models.Position.Map;
import Models.Position.Point;
import Models.Soldiers.*;
import Models.Village;

import java.util.ArrayList;

public class Cannon extends Building implements Attacker {



    public Cannon( ) {

        setTypeCode( 9 );
        setMaxHealth(400);
        setHealth( getMaxHealth() );
        setLevel( 0 ) ;
        setHitScore(4) ;
        setSize(1);

        upgradeCost = 100 ;  //  gold
        upgradeTime = 100;  // delta T

        buildingCost = 400 ;  // gold
        buildingTime = 100 ;

        mainTargets.add(Archer.class);
        mainTargets.add(Giant.class);
        mainTargets.add(Guardian.class);
        mainTargets.add(WallBreaker.class);

    }
    private ArrayList<Class> mainTargets = new ArrayList<>(1);

    private int effectRadius = 13 ; // delta L
    private int damage = 20 ; // gooli


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

    public  void startConstructing (  Village village , int x , int y ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                village.getCannons().add(this);
                village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                new Functions().decreaseGold( village , this.getBuildingCost() );

                break;
            }
        }
    }

    @Override
    public int getAttackRange() {
        return 6;
    }

    @Override
    public Cell selectTarget(Map map) {
        return null;
    }


    public Point selectTargets(Map map) {
        Point target;
        for (int i = 1; i <= getAttackRange(); i++) {
            for (int j = 0; j <= i; j++) {
                target = map.verticalSearch(this.getPosition(), i, j, mainTargets);
                if (target == null)
                    target = map.verticalSearch(this.getPosition(), -i, j, mainTargets);
                if (target == null)
                    target = map.horizontalSearch(this.getPosition(), i, j, mainTargets);
                if (target == null)
                    target = map.horizontalSearch(this.getPosition(), -i, j, mainTargets);
                if (target != null)
                    return target;
            }
        }

        return null;
    }

    @Override
    public void attack(Cell target) {
        if (target == null)
            return;

        if (target.getOnGroundThings().size() == 0)
            return;

        for ( Entity enemy : target.getOnGroundThings()) {
            enemy.setHealth(enemy.getHealth() - getDamage());
            if (enemy.getHealth() <= 0)
                enemy.setDestroyed(true);
        }


    }


    @Override
    public void passOneTurn(Map map) {
        Point targetPoint = selectTargets(map);
        Cell target ;

        if (targetPoint == null)
            return;

        for (int i = -1; i <= 1 ; i++) {
            for (int j = -1; j <= 1; j++) {

                target = map.getCell(targetPoint.getHeight() + i,targetPoint.getWidth() + j );
                attack(target);
            }
        }


    }

    public  void startUpGrading (  Village village  ) {

        for( Labor l : village.getTownhall().getLabors() ){

            if(l.isFree() == true) {

                l.setFree(false);
                l.setWorkPlace(this);
                village.getTownhall().getInLineConstructions().add(this) ;
                new Functions().decreaseGold( village , this.getBuildingCost() );
                setOnUpgrade(true);
                setActive(false);
                break;
            }
        }
    }

    @Override
    public void upgrade() {

        this.setLevel( this.getLevel() + 1 );
        this.damage++;
        this.setMaxHealth( this.getMaxHealth() + 10 );
        this.setHealth( this.getMaxHealth() );
        setOnUpgrade(false);
        setActive(true);
    }
}
