package comfees.processor.processor;

import java.util.List;
import java.util.Map;

import com.fees.processor.pojo.Transaction;

public interface FeeProcessor {
	
	public Map<String, Transaction> process(List<Transaction> list);

}
