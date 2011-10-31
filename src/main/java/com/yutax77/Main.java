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
		
		outputResult(results);
	}
	
	private static void outputResult(NavigableSet<Combination> results) {
		System.out.println("Rank \tScore \t議長 \t書記 \tおやつ係");
		System.out.println("---------------------------------------------------");
		int rank = 0;
		int count = 1;
		double score = Double.MAX_VALUE;
		for(Combination combination : results) {
			StringBuilder buf = new StringBuilder();
			
			if(combination.getScore() < score) {
				rank += count - rank;
			}
			score = combination.getScore();
			buf.append(rank).append("\t");
			buf.append(String.format("%.2f", score)).append("\t");
			buf.append(combination.getChairman()).append("\t");
			buf.append(combination.getSecretary()).append("\t");
			Pair<Person, Person> snacks = combination.getSnacPair();
			buf.append(snacks.getLeft()).append(", ").append(snacks.getRight());
			System.out.println(buf.toString());
			
			count++;
		}
	}
}
