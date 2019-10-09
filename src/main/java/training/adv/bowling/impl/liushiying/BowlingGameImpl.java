package training.adv.bowling.impl.liushiying;

import training.adv.bowling.api.BowlingGame;
import training.adv.bowling.api.BowlingRule;
import training.adv.bowling.api.BowlingTurn;
import training.adv.bowling.api.BowlingTurnEntity;
import training.adv.bowling.api.GameEntity;
import training.adv.bowling.api.Turn;
import training.adv.bowling.api.TurnEntity;
import training.adv.bowling.impl.AbstractGame;

public class BowlingGameImpl extends AbstractGame<BowlingTurn,BowlingRule> implements BowlingGame {

	private Integer id;
	
	private BowlingTurn[] bowlingTurns;


	public BowlingGameImpl(BowlingRule rule) {
		super(rule);
		this.rule=rule;
	}
	
	public BowlingGameImpl(BowlingRule rule,Integer id) {
		super(rule);
		this.id=id;
		this.rule=rule;
		
	}
	
	
	@Override
	public Integer getTotalScore() {
		int sum=0;
		Integer[]scores=getScores();
		if(scores==null){
			return 0;
		}
		if(!rule.isGameFinished(bowlingTurns)){
			for(Integer s:scores){
				//System.out.println(s);
				sum+=s;
			}
		}else{
			for(int i=0;i<rule.getMaxTurn();i++){
				//System.out.println(scores[i]);
				sum+=scores[i];
			}
		}
		return sum;
	}

	//每回合分数
	@Override
	public Integer[] getScores() {
		return rule.calcScores(getTurns());
		
	}

	//回合对象
	@Override
	public BowlingTurn[] getTurns() {
		return bowlingTurns;
	}

	@Override
	public Integer[] addScores(Integer... pins) {
		if(pins==null){
			return getScores();
		}
		bowlingTurns=rule.addScores(bowlingTurns, pins);
		System.out.print("当前游戏为："+id);
		//System.out.println("列数："+bowlingTurns.length);
		/*for(BowlingTurn turn:bowlingTurns){
			System.out.println(turn.getFirstPin()+" : "+turn.getSecondPin());
		}*/
		
		return getScores();
		
		
	}

	@Override
	public GameEntity getEntity() {
		BowlingGameEntityImpl bowlingGameEntityImpl=new BowlingGameEntityImpl();
		BowlingTurnEntity[] turnEntities=new BowlingTurnEntityImpl[bowlingTurns.length];
		bowlingGameEntityImpl.setId(id);
		for(int i=0;i<bowlingTurns.length;i++){
			turnEntities[i]=bowlingTurns[i].getEntity();
		}
		bowlingGameEntityImpl.setTurnEntities(turnEntities);
		return bowlingGameEntityImpl;
	}

}
