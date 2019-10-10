package training.adv.bowling.impl.liushiying;

import java.util.Random;

import training.adv.bowling.api.BowlingGame;
import training.adv.bowling.api.BowlingGameFactory;

public class BowlingGameFactoryImpl implements BowlingGameFactory{

	private static BowlingGameFactoryImpl factory=new BowlingGameFactoryImpl();
	private Integer index=1002;
	
	private BowlingGameFactoryImpl(){
		
	}
	
	public static BowlingGameFactoryImpl getInstance(){
		return factory;
	}
	
	@Override
	public BowlingGame getGame() {
		//TODO:改成可扩展性
		
		BowlingGameImpl bowlingGameImpl=new BowlingGameImpl(BowlingRuleImpl.getInstance(),index);
		index++;
		return bowlingGameImpl;
	}

}
