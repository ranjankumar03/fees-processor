package com.fees.processor.context;

import java.io.IOException;
import java.util.List;

import com.fees.processor.pojo.Transaction;
import comfees.processor.util.FileReader;

public class FileReadContext {

	private FileReader fileReader;

	public void setFileReadStrategy(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public List<Transaction> read(String file) throws IOException {
		return fileReader.read(file);
	}

}
