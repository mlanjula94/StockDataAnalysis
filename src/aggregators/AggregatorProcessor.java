package aggregators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import client.StockFileApplication;

import fileprocessors.StockFileData;
import fileprocessors.StockFileReader;

public class AggregatorProcessor <agg extends Aggregator> {
	
	agg aggregator;
	String file;

	public AggregatorProcessor(agg aggregator, String file) {
		super();
		this.aggregator = aggregator;
		this.file = file;
	}

	public agg getAggregator() {
		return aggregator;
	}

	public void setAggregator(agg aggregator) {
		this.aggregator = aggregator;
	}

	public double runAggregator(int colIdx) throws IOException {
		StockFileReader fr = new StockFileReader(file);
		List<String> lines = fr.readFileData();
		
		for(String line: lines) {
			String [] numbers = line.split(",");
			double value = Double.parseDouble(numbers[colIdx-1]);
			aggregator.add(value);
		}
		
		double number = aggregator.calculate();
		return number;
	} 
	
}
