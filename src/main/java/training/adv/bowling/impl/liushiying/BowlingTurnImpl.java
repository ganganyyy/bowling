package training.adv.bowling.impl.liushiying;

import training.adv.bowling.api.BowlingTurn;
import training.adv.bowling.api.BowlingTurnEntity;
import training.adv.bowling.api.TurnKey;

public class BowlingTurnImpl implements BowlingTurn {

	private Integer first;
	private Integer second;
//	private TurnKey turnKey;
	private BowlingTurnEntity entity=new BowlingTurnEntityImpl();

	
	public BowlingTurnImpl(Integer first) {
		this(first,null);
	}
	
	public BowlingTurnImpl(Integer first,Integer second) {
		this.first=first;
		this.second=second;
	
	}
	
	@Override
	public BowlingTurnEntity getEntity() {
		return entity;
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
