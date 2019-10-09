package training.adv.bowling.impl.liushiying;

import training.adv.bowling.api.GameEntity;
import training.adv.bowling.api.TurnEntity;

public class BowlingGameEntityImpl implements GameEntity {

	private Integer id;
	private TurnEntity[] turns;
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.setId(id);
	}

	@Override
	public void setTurnEntities(TurnEntity[] turns) {
		this.turns=turns;
	}

	@Override
	public TurnEntity[] getTurnEntities() {
		return turns;
	}

	@Override
	public Integer getMaxTurn() {
		return 10;
	}

}
