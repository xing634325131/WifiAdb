package com.gugutian.wifiadb.util;

import java.io.DataOutputStream;
import java.io.IOException;

public class AdbUtils {

	public static int reset() throws IOException, InterruptedException {
		Process localProcess = Runtime.getRuntime().exec("su");
		DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
		localDataOutputStream.writeBytes("setprop service.adb.tcp.port -1\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("stop adbd\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("start adbd\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("exit\n");
		localDataOutputStream.flush();
		localProcess.waitFor();
		return localProcess.exitValue();
	}

	public static int set(int paramInt) throws IOException, InterruptedException {
		Process localProcess = Runtime.getRuntime().exec("su");
		DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
		localDataOutputStream.writeBytes("setprop service.adb.tcp.port " + paramInt + "\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("stop adbd\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("start adbd\n");
		localDataOutputStream.flush();
		localDataOutputStream.writeBytes("exit\n");
		localDataOutputStream.flush();
		localProcess.waitFor();
		return localProcess.exitValue();
	}
}