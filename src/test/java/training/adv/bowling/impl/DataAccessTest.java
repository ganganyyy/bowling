package training.adv.bowling.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.h2.command.Prepared;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import training.adv.bowling.api.BowlingGame;
import training.adv.bowling.api.BowlingGameFactory;
import training.adv.bowling.api.BowlingService;
import training.adv.bowling.api.BowlingTurn;
import training.adv.bowling.api.BowlingTurnEntity;
import training.adv.bowling.api.GameEntity;
import training.adv.bowling.api.TurnKey;
import training.adv.bowling.impl.liushiying.BowlingGameEntityImpl;
import training.adv.bowling.impl.liushiying.BowlingGameFactoryImpl;
import training.adv.bowling.impl.liushiying.BowlingTurnEntityImpl;
import training.adv.bowling.impl.liushiying.TurnKeyImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataAccessTest {
	
	private BowlingService bowlingService = new BowlingServiceImpl();
	private BowlingGameFactory factory = BowlingGameFactoryImpl.getInstance();
	private Connection conn;

	@Before
	public void before() {
		conn=DBUtil.getConnection();
		/*//建表
		String sql1="create table game_table(game_id int primary key not null,maxTurn int not null);";
		String sql2="create table turns_table(id int primary key auto_increment,turn_id int not null,firstpin int,secondpin int,game_id int not null, foreign key (game_id) references game_table(game_id));";
		try{
			Statement pstm1 = conn.createStatement();
			Statement pstm2 = conn.createStatement();
			pstm1.executeUpdate(sql1);
			pstm2.executeUpdate(sql2);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("建表失败");
		}*/
	}



	@After
	public void after() {
		/*String sql1="drop table game_table;";
		String sql2="drop table turns_table;";
		try{
			Statement pstm1 = conn.createStatement();
			Statement pstm2 = conn.createStatement();
			pstm1.executeUpdate(sql1);
			pstm2.executeUpdate(sql2);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("删表失败");
		}*/
	}
	
	@Test
	public void testSave() {
		BowlingGame game = factory.getGame();
		game.addScores(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
		bowlingService.save(game);
		GameEntity result = query(game.getEntity().getId());
		assertEquals(game.getEntity().getId(), result.getId());
		assertEquals(game.getEntity().getMaxTurn(), result.getMaxTurn());
		
		for (BowlingTurn turn : game.getTurns()) {
			BowlingTurnEntity turnEntity = turn.getEntity();
			BowlingTurnEntity turnResult = query(turnEntity.getId());
			assertEquals(turnEntity.getId(), turnResult.getId());
			assertEquals(turnEntity.getFirstPin(), turnResult.getFirstPin());
			assertEquals(turnEntity.getSecondPin(), turnResult.getSecondPin());
		}
	}
	
	//Prepared data in db.
	@Test
	public void testLoad() {
		BowlingGame game = bowlingService.load(1001);
		GameEntity entity = game.getEntity();
		
		assertEquals(Integer.valueOf(1001), entity.getId());
		assertEquals(Integer.valueOf(10), entity.getMaxTurn());
		assertEquals(12, game.getTurns().length);
		assertEquals(Integer.valueOf(300), game.getTotalScore());
	}
	
	//Prepared data in db.
	@Test
	public void testRemove() {
		GameEntity before = query(1001);
		assertEquals(Integer.valueOf(1001), before.getId());
		
		bowlingService.remove(1001);
		
		GameEntity after = query(1001);
		assertNull(after);
	}	
	
	
	private GameEntity query(Integer id) {
		String sql="select * from game_table where game_id=?";
		BowlingGameEntityImpl bowlingGameEntity=null;
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, id);
			ResultSet rs=pstm.executeQuery();
			while(rs.next()){
				bowlingGameEntity=new BowlingGameEntityImpl(rs.getInt("maxTurn"));
				bowlingGameEntity.setId(id);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return bowlingGameEntity;
	}
	
	private BowlingTurnEntity query(TurnKey key) {
		int turn_id=key.getId();
		int game_id=key.getForeignId();
		String sql="select * from turns_table where turn_id=? and game_id=?";
		BowlingTurnEntity bowlingTurnEntity=null;
		try{
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setObject(1, turn_id);
			pstm.setObject(2, game_id);
			ResultSet rs=pstm.executeQuery();
			while(rs.next()){
				bowlingTurnEntity=new BowlingTurnEntityImpl();
				TurnKey turnKey=new TurnKeyImpl(rs.getInt("turn_id"),rs.getInt("game_id"));
				bowlingTurnEntity.setId(turnKey);
				bowlingTurnEntity.setFirstPin(rs.getInt("firstpin"));
				bowlingTurnEntity.setSecondPin(rs.getInt("secondpin"));

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return bowlingTurnEntity;
	}
	
}
