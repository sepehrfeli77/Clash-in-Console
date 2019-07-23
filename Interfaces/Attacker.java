package Interfaces;

import Models.Position.Cell;
import Models.Position.Map;

import java.util.ArrayList;


public interface Attacker {

    int getDamage();
    /*
    returns attacker power to damage
     */

    int getAttackRange();

    ArrayList<Class> otherTargets = new ArrayList<>(1);
    ArrayList<Class> mainTargets = new ArrayList<>(1);

    Cell selectTarget(Map map);

    void attack(Cell target);

}
