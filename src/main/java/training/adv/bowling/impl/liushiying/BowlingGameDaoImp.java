package training.adv.bowling.impl.liushiying;

import training.adv.bowling.api.BowlingGame;
import training.adv.bowling.api.BowlingGameDao;
import training.adv.bowling.api.GameEntity;
import training.adv.bowling.impl.AbstractDao;

public class BowlingGameDaoImp extends AbstractDao<GameEntity, BowlingGame,Integer> implements BowlingGameDao {


    @Override
    protected void doSave(GameEntity entity) {

    }

    @Override
    protected GameEntity doLoad(Integer id) {
        return null;
    }

    @Override
    protected BowlingGame doBuildDomain(GameEntity entity) {
        return null;
    }

    @Override
    public boolean remove(Integer key) {
        return false;
    }
}
