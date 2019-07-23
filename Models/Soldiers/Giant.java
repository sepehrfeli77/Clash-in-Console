package Models.Soldiers;

import Enums.Field;
import Interfaces.Attacker;
import Models.Buildings.DefensiveBuildings.AirDefense;
import Models.Buildings.DefensiveBuildings.ArcherTower;
import Models.Buildings.DefensiveBuildings.Cannon;
import Models.Buildings.DefensiveBuildings.WizardTower;
import Models.Buildings.TownBuildings.*;
import Models.Position.Cell;
import Models.Position.Map;
import Models.Position.Point;

import java.util.ArrayList;

public class Giant extends Soldier implements Attacker{
    private ArrayList<Class> mainTargets = new ArrayList<>(1);
    public Giant() {
        setMovementField(Field.GROUND);
        setBuildingCost(150);
        setBuildingTime(30);
        setMaxHealth(500);
        setHealth(getMaxHealth());
        setEffectRadius(1);
        setDamage(30);
        setSpeed(1);
        mainTargets.add(Storage.class);
        mainTargets.add(Mine.class);

        // in unexistance of maintargets , attack to nearest building

        otherTargets.add(AirDefense.class);
        otherTargets.add(ArcherTower.class);
        otherTargets.add(Cannon.class);
        otherTargets.add(WizardTower.class);

        otherTargets.add(Barrack.class);
        otherTargets.add(Camp.class);
        otherTargets.add(TownHall.class);
    }

    @Override
    public int getAttackRange() {
        return 1;
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
        Soldier enemySoldier = null;

        for (Class targetType : mainTargets) {
            enemySoldier = ((Soldier) target.find(targetType));
            if (enemySoldier != null)
                break;
        }

        enemySoldier.setHealth(enemySoldier.getHealth() - getDamage());
        if (enemySoldier.getHealth() <= 0) {
            enemySoldier.setDestroyed(true);

        }

    }

    @Override
    public void passOneTurn(Map map) {
        move(map);
        Cell target = selectTarget(map);
        attack(target);
    }

    @Override
    public void upgrade() { //TODO

        this.setDamage(this.getDamage() +1);
        this.setMaxHealth(this.getMaxHealth() +1);
        this.setHealth( this.getMaxHealth() );
    }


    @Override
    public void move(Map map) {

    }
}