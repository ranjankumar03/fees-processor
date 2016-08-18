package comfees.processor.processor;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fees.processor.pojo.Transaction;
import comfees.processor.util.FileWriter;

public class FeeProcessorImpl implements FeeProcessor {

	private static final Map<String, Transaction> map = new HashMap<String, Transaction>();
	int lastIndex = -1;
	@Override
	public Map<String, Transaction> process(List<Transaction> inputList) {
		// TODO Auto-generated method stub
		System.out.println("Me inside Processor.........");
		// sort group by
		Collections.sort(inputList, new ClientIDComparator());

		
		for (int i = 0; i < inputList.size(); i++) {
			Transaction t1 = inputList.get(i);
			lastIndex++;
			for (int j = i + 1; j < inputList.size(); j++) {
				Transaction t2 = inputList.get(j);

				// Intraday Condition
				if (t1.getClientId().equalsIgnoreCase(t2.getClientId()) && (!t1.getTransactionType().equalsIgnoreCase(t2.getTransactionType())) && t1.getSecurityId().equalsIgnoreCase(t2.getSecurityId())
						&& t1.getTransactionDate().equalsIgnoreCase(t2.getTransactionDate())) {

					System.out.println("Tranactio Type of T1 and T2" + t1.getTransactionType() + "==" + t2.getTransactionType());
					System.out.println("External Id T1 and T2 " + t1.getExternalTransactionId() + "===" + t2.getExternalTransactionId());
					System.out.println("T1" + t1.getMarketValue() + "==" + "T2" + t2.getMarketValue() + "Clinet::" + t1.getClientId());
					t1.setFees(10);
					t2.setFees(10);
					break;

				}
			}
			//Else Normal Condition
			if (t1.getFees() == 0 && t1.getPriorityFlag().equalsIgnoreCase("Y"))
				t1.setFees(500);
			else if (t1.getFees() == 0 && t1.getPriorityFlag().equalsIgnoreCase("N") && t1.getTransactionType().equalsIgnoreCase("SELL") || t1.getTransactionType().equalsIgnoreCase("WITHDRAW"))
				t1.setFees(100);
			else if (t1.getFees() == 0 && t1.getPriorityFlag().equalsIgnoreCase("N") && t1.getTransactionType().equalsIgnoreCase("BUY") || t1.getTransactionType().equalsIgnoreCase("DEPOSIT"))
				t1.setFees(50);

		}

		// print
		for (Transaction output : inputList) {
			System.out.println("External Transaction Id:" + output.getExternalTransactionId() + "," + "Fees:" + output.getFees() + "," + "LineCode:" + output.getLineCode());
		}

		// Put into Map for grouping based on Line Code // aggregating fees
		// based on grouping crietria
		
		for (Transaction transaction : inputList) {
			if (!map.containsKey(transaction.getLineCode())) {
				map.put(transaction.getLineCode(), transaction);
			} else {
				double mapFees = map.get(transaction.getLineCode()).getFees();
				double currentFees = transaction.getFees();
				transaction.setFees(mapFees + currentFees);
				map.put(transaction.getLineCode(), transaction);
			}
		}

		// print map
		for (Entry<String, Transaction> map1 : map.entrySet())
			System.out.println("LineCode : " + map1.getKey() + "," + "Net Fees" + map1.getValue().getFees());

		// add writer utility class to write output to CSV file
		FileWriter writer = new FileWriter();
		try {
			writer.write(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;

	}
}

class ClientIDComparator implements Comparator<Transaction> {

	@Override
	public int compare(Transaction o1, Transaction o2) {
		// TODO Auto-generated method stub
		return o1.getClientId().compareTo(o2.getClientId());
	}

}
