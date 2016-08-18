package com.fees.processor.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fees.processor.context.FileReadContext;
import com.fees.processor.exception.FeeProcessingException;
import com.fees.processor.pojo.Transaction;

import comfees.processor.processor.FeeProcessor;
import comfees.processor.processor.FeeProcessorImpl;
import comfees.processor.util.CSVFileReader;
import comfees.processor.util.FileTypeEnum;
import comfees.processor.util.TextFileReader;
import comfees.processor.util.XLSFileReader;
import comfees.processor.util.XMLFileReader;

public class FeesControllerAPI {

	private static final Logger log = Logger.getLogger(FeesControllerAPI.class);
	private static final FileReadContext context = new FileReadContext();
	private static final FeeProcessor processor = new FeeProcessorImpl();

	public Map<String, Transaction> triggerController(String file) throws IOException, FeeProcessingException {

		//String file = "C://Users/rku140/workspace/fees-processor/inputFileContainer/transaction.txt";
		String fileType = file.substring(file.indexOf(".") + 1);

		switch (FileTypeEnum.valueOf(fileType.toUpperCase())) {
			case TXT :
				context.setFileReadStrategy(new TextFileReader());
				break;

			case CSV :
				context.setFileReadStrategy(new CSVFileReader());
				break;

			case XML :
				context.setFileReadStrategy(new XMLFileReader());
				break;

			case XLS :
				context.setFileReadStrategy(new XLSFileReader());
				break;

			default :
				break;

		}

		List<Transaction> list = context.read(file);
		if (list == null) {
			throw new FeeProcessingException("Input coming as empyt. Please check the file");
		}

		log.info("Fee Processing logic starts.....");
		Map<String, Transaction> map = processor.process(list);

		if (map == null) {
			throw new FeeProcessingException("There is issue while processing fees");
		}

		return map;
	}

}
