package com.yutax77;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.err.println("Usage: java -jar CouncilLottery.jar <ユーザリストファイル名> <ログファイル名>");
			System.exit(1);
		}
		
		Set<Person> workers = new WorkerListReader(args[0]).read();
		Log log = Log.createFromFile(new File(args[1]));
		ScoreCalcurator calcurator = new ScoreCalcurator(workers);
		Map<TitleType, Scores> scores = calcurator.calc(log);
		NavigableSet<Combination> results = CombinationScoreCalculator.calc(scores);
		
		for(Combination combination : results) {
			System.out.println(combination);
		}
	}
}
