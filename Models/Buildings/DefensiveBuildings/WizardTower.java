package Models.Buildings.DefensiveBuildings;

import Functions.Functions;
import Interfaces.Attacker;
import Models.Buildings.Building;
import Models.Labor;
import Models.Position.Cell;
import Models.Position.Map;
import Models.Position.Point;
import Models.Soldiers.*;
import Models.Village;

public class WizardTower extends Building implements Attacker {



    public WizardTower( ) {

        setTypeCode( 11 );

        setMaxHealth(700);
        setHealth( getMaxHealth() );

        setLevel( 0 ) ;
        setHitScore(5) ;
        setSize(1);

        upgradeCost = 100 ;  //  gold
        upgradeTime = 120 ;  // delta T

        buildingCost = 500 ;  // gold
        buildingTime = 120 ;

        mainTargets.add(Archer.class);
        mainTargets.add(Giant.class);
        mainTargets.add(Guardian.class);
        mainTargets.add(WallBreaker.class);
        mainTargets.add(Dragon.class);
    }

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
                village.getWizardTowers().add(this);
                village. getMap(). getCell(y , x). getOnGroundThings(). add(this);
                new Functions().decreaseGold( village , this.getBuildingCost() );

                break;
            }
        }
    }

    @Override
    public int getAttackRange() {
        return 7;
    }


    @Override
    public Cell selectTarget(Map map) {
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
                    return map.getCell(target);
            }
        }

        return null;

    }

    @Override
    public void attack(Cell target) {

        if (target == null)
            return;
        Soldier enemySoldier = null;

        for (Class targetType : mainTargets) {
            enemySoldier = ((Soldier) target.find(targetType));
            if (enemySoldier != null)
                break;
        }

        enemySoldier.setHealth(enemySoldier.getHealth() - getDamage());
        if (enemySoldier.getHealth() <= 0)
            enemySoldier.setDestroyed(true);

    }

    @Override
    public void passOneTurn(Map map) {
        Cell target = selectTarget(map);
        attack(target);
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
