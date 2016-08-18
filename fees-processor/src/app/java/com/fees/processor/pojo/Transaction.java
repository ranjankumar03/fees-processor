package com.fees.processor.pojo;

public final class Transaction {
	
	private String externalTransactionId;
	private String clientId;
	private String securityId;
	private String transactionType;
	private String transactionDate;
	private double marketValue;
	private String priorityFlag;
	private double fees;
	private String lineCode;
	public Transaction(String externalTransactionId, String clientId, String securityId, String transactionType, String transactionDate, double marketValue, String priorityFlag, double fees) {
		this.externalTransactionId = externalTransactionId;
		this.clientId = clientId;
		this.securityId = securityId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.marketValue = marketValue;
		this.priorityFlag = priorityFlag;
		this.fees = fees;
		this.lineCode = (clientId + securityId + transactionType + transactionDate + priorityFlag).replaceAll("/", "");
	}
	public String getExternalTransactionId() {
		return externalTransactionId;
	}
	public String getClientId() {
		return clientId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public String getPriorityFlag() {
		return priorityFlag;
	}
	public double getFees() {
		return fees;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((externalTransactionId == null) ? 0 : externalTransactionId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((lineCode == null) ? 0 : lineCode.hashCode());
		temp = Double.doubleToLongBits(marketValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((priorityFlag == null) ? 0 : priorityFlag.hashCode());
		result = prime * result + ((securityId == null) ? 0 : securityId.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (externalTransactionId == null) {
			if (other.externalTransactionId != null)
				return false;
		} else if (!externalTransactionId.equals(other.externalTransactionId))
			return false;
		if (Double.doubleToLongBits(fees) != Double.doubleToLongBits(other.fees))
			return false;
		if (lineCode == null) {
			if (other.lineCode != null)
				return false;
		} else if (!lineCode.equals(other.lineCode))
			return false;
		if (Double.doubleToLongBits(marketValue) != Double.doubleToLongBits(other.marketValue))
			return false;
		if (priorityFlag == null) {
			if (other.priorityFlag != null)
				return false;
		} else if (!priorityFlag.equals(other.priorityFlag))
			return false;
		if (securityId == null) {
			if (other.securityId != null)
				return false;
		} else if (!securityId.equals(other.securityId))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [externalTransactionId=" + externalTransactionId + ", clientId=" + clientId + ", securityId=" + securityId + ", transactionType=" + transactionType + ", transactionDate=" + transactionDate + ", marketValue=" + marketValue
				+ ", priorityFlag=" + priorityFlag + ", fees=" + fees + ", lineCode=" + lineCode + "]";
	}

	

}
