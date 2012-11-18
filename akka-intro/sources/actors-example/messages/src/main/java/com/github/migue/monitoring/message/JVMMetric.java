package com.github.migue.monitoring.message;

/**
 * Messages representing the JVM statistics
 * 
 * @author Miguel Pastor
 */
final public class JVMMetric extends Metric {
	public JVMMetric(String name, String jvmVersion, long heapUsage) {
		super(name);

		this.jvmVersion = jvmVersion;
		this.heapUsage = heapUsage;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("[" + this.getName() + "]");		
		stringBuffer.append("Version:");
		stringBuffer.append(this.jvmVersion);
		stringBuffer.append("Heap Usage");
		stringBuffer.append(this.heapUsage);

		return stringBuffer.toString();
	}
	
	private final String jvmVersion;	
	private final long heapUsage;
}
