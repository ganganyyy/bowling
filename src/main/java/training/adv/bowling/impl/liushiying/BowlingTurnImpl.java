package training.adv.bowling.impl.liushiying;

import training.adv.bowling.api.BowlingTurn;
import training.adv.bowling.api.BowlingTurnEntity;
import training.adv.bowling.api.TurnKey;

public class BowlingTurnImpl implements BowlingTurn {

	private Integer first;
	private Integer second;
	private TurnKey turnKey;
	
	public BowlingTurnImpl(Integer first,Integer second,TurnKey turnKey) {
		this.first=first;
		this.second=second;
		this.turnKey=turnKey;
	}
	
	@Override
	public BowlingTurnEntity getEntity() {
		BowlingTurnEntityImpl entityImpl=new BowlingTurnEntityImpl();
		entityImpl.setFirstPin(first);
		entityImpl.setSecondPin(second);
		entityImpl.setId(turnKey);
		return entityImpl;
	}

	@Override
	public Integer getFirstPin() {
		return first;
	}

	@Override
	public Integer getSecondPin() {
		return second;
	}

}
