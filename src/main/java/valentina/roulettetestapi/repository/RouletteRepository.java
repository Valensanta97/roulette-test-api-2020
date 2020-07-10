package valentina.roulettetestapi.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;

@Repository
public class RouletteRepository {

	public static final String KEY = "Roulette";
	private RedisTemplate<String, Roulette> redisTemplate;
	private RedisTemplate<String, Bet> betTemplate;
	private HashOperations hashOperations;

	public RouletteRepository(RedisTemplate<String, Roulette> rouletteTemplate,
			RedisTemplate<String, Bet> betTemplate) {
		this.redisTemplate = rouletteTemplate;
		this.betTemplate = betTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public String createRoulette(Roulette roulette) {
		String id = UUID.randomUUID().toString();
		hashOperations.put(KEY, id, roulette);

		return id;
	}

	public Map<String, Roulette> findAllRoulettes() {
		return hashOperations.entries(KEY);
	}

	public int openRoulette(String idRoulette) {
		Roulette r = (Roulette) hashOperations.get(KEY, idRoulette);
		r.setState(Roulette.OPEN);
		hashOperations.put(KEY, idRoulette, r);

		return r.getState();
	}

	public ArrayList<Bet> closedRoulette(String idRoulette) {
		Roulette r = (Roulette) hashOperations.get(KEY, idRoulette);
		r.setState(Roulette.CLOSED);
		hashOperations.put(KEY, idRoulette, r);
		int randomNum = (int) Math.round(Math.random() * 36);
		System.out.println(randomNum);

		return findBetsByIdRoulette(idRoulette, randomNum);
	}

	private ArrayList<Bet> findBetsByIdRoulette(String idRoulette, int randomNum) {
		ArrayList<Bet> bets = new ArrayList<Bet>();
		String match = "*";
		Cursor<Entry<Object, Object>> cursor = betTemplate.opsForHash().scan(BetRepository.KEY,
				ScanOptions.scanOptions().match(match).build());
		while (cursor.hasNext()) {
			Entry<Object, Object> key = cursor.next();
			Bet bet = (Bet) key.getValue();
			if (bet.getIdRoulette().equalsIgnoreCase(idRoulette)) {
				bet.setWinner(this.isBetWinner(bet, randomNum));
				bets.add(bet);
			}
		}

		return bets;
	}

	private boolean isBetWinner(Bet bet, Integer randomNum) {
		
		if((bet.getNumber() != null && randomNum == bet.getNumber())
				|| (randomNum != 0 && randomNum % 2 == 0 && bet.getColor().equals("red"))
				|| (randomNum % 2 != 0 && bet.getColor().equals("black"))){
			return true;
		}else {
			return false;
		}
	}
}
