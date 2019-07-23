package Models.Soldiers;

import Enums.Field;
import Interfaces.Attacker;
import Models.Buildings.Building;
import Models.Buildings.DefensiveBuildings.AirDefense;
import Models.Buildings.DefensiveBuildings.ArcherTower;
import Models.Buildings.DefensiveBuildings.Cannon;
import Models.Buildings.DefensiveBuildings.WizardTower;
import Models.Buildings.TownBuildings.*;
import Models.Entity;
import Models.Position.Cell;
import Models.Position.Map;
import Models.Position.Point;

import java.util.ArrayList;

public class Archer extends Soldier implements Attacker{

    private ArrayList<Class> mainTargets = new ArrayList<>(1);
    public Archer() {

        setMovementField(Field.GROUND);

        setBuildingCost(75);
        setBuildingTime(10);

        setMaxHealth(100);
        setHealth(getMaxHealth());
        setEffectRadius(10);

        setDamage(10);
        setSpeed(2);

        mainTargets.add(AirDefense.class);
        mainTargets.add(ArcherTower.class);
        mainTargets.add(Cannon.class);
        mainTargets.add(WizardTower.class);

        // in unexistance of maintargets , attack to nearest building
        otherTargets.add(Barrack.class);
        otherTargets.add(Camp.class);
        otherTargets.add(Mine.class);
        otherTargets.add(Storage.class);
        otherTargets.add(TownHall.class);

    }

    @Override
    public int getAttackRange() {
        return 10;
    }

    public Cell selectTarget (Map buildingMap ) {
        Point target;
        for (int i = 1; i <= getAttackRange(); i++) {
            for (int j = 0; j <= i; j++) {
                target = buildingMap.verticalSearch(this.getPosition(), i, j, mainTargets);
                if (target == null)
                    target = buildingMap.verticalSearch(this.getPosition(), -i, j, mainTargets);
                if (target == null)
                    target = buildingMap.horizontalSearch(this.getPosition(), i, j, mainTargets);
                if (target == null)
                    target = buildingMap.horizontalSearch(this.getPosition(), -i, j, mainTargets);
                if (target != null)
                    return buildingMap.getCell(target);
            }
        }

        for (int i = 1; i <= getAttackRange(); i++) {
            for (int j = 0; j <= i; j++) {
                target = buildingMap.verticalSearch(this.getPosition(), i, j, otherTargets);
                if (target == null)
                    target = buildingMap.verticalSearch(this.getPosition(), -i, j, otherTargets);
                if (target == null)
                    target = buildingMap.horizontalSearch(this.getPosition(), i, j, otherTargets);
                if (target == null)
                    target = buildingMap.horizontalSearch(this.getPosition(), -i, j, otherTargets);
                if (target != null)
                    return buildingMap.getCell(target);
            }
        }

        return null;
    }

    @Override
    public void attack(Cell target) {

        if (target == null)
            return;
        Entity enemyEntity = null;


        for (Class targetType : mainTargets) {
            enemyEntity = ((Entity) target.find(targetType));
            if (enemyEntity != null)
                break;
        }

        if (enemyEntity.getPosition().euclideanDistanceFrom(this.getPosition()) > getAttackRange())
            return;

        enemyEntity.setHealth(enemyEntity.getHealth() - getDamage());
        if (enemyEntity.getHealth() <= 0) {
            enemyEntity.setDestroyed(true);

        }

    }


    @Override
    public void passOneTurn(Map map) {
        Cell target = selectTarget(map);
        move(map);
        attack(target);

    }



    @Override
    public void upgrade() {

        this.setDamage(this.getDamage() +1);
        this.setMaxHealth(this.getMaxHealth() +1);
        this.setHealth( this.getMaxHealth() );
    }

    @Override
    public void move(Map map) {

    }
}