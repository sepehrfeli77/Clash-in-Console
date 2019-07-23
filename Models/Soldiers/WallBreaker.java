package Models.Soldiers;

import Interfaces.Attacker;
import Models.Position.Cell;
import Models.Position.Map;

import java.util.ArrayList;


public class WallBreaker extends Soldier implements Attacker{

    public static final ArrayList<Class> mainTargets = new ArrayList<>(1);

    public WallBreaker() {


    }

    @Override
    public int getAttackRange() {
        return 1;
    }

    public Cell selectTarget (Map buildingMap ) { //TODO

        Cell target = new Cell( ) ;

        return null ;
    }

    @Override
    public void attack(Cell target) {

    }

    @Override
    public void upgrade() { //TODO

    }

    @Override
    public void passOneTurn(Map map) {
        move(map);
        Cell target = selectTarget(map);
        attack(target);
    }

    @Override
    public void move(Map map) {

    }
}