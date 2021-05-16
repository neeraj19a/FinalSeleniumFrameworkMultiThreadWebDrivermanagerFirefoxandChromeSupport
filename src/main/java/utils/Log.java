package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.File;

public class Log {

    public static Logger logger = LogManager.getLogger();

    public static synchronized void startTestCase(String sTestCaseName) {

        sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_");
        sTestCaseName = sTestCaseName.replaceAll("_+", "_");

        startLog(System.getProperty("log.directory"), sTestCaseName);
        info("\n\n************** Execution Started : " + sTestCaseName + " on Thread " + Thread.currentThread().getId()
                + "**************\n");
    }

    public static void endTestCase(String sTestCaseName) {

        info("\n\n************** Execution End : " + sTestCaseName + " on Thread " + Thread.currentThread().getId()
                + "**************\n");
    }

    private static void startLog(String dirPath, String testCaseName) {

        int noOfFiles = 0;

        File dir = new File(dirPath);
        if (dir.exists()) {
            int count = 0;
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".log") && file.getName().contains(testCaseName)) {
                    count++;
                }
            }

            noOfFiles = count;
        }

        noOfFiles++;
        String logFileName = testCaseName + "_" + noOfFiles;

        ThreadContext.put("logFilename", logFileName);
    }

    public static Logger getCurrentLog() {
        return logger;
    }

    public static String getClassName() {

        return new Throwable().getStackTrace()[3].getClassName();
    }


    public static String getCallInfo() {

        String callInfo;
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

        callInfo = "T - " + Thread.currentThread().getId() + " " + getClassName() + ":" + methodName + " " + lineNumber
                + " ";
        return callInfo;

    }

    public static void trace(Object message) {
        getCurrentLog().trace(message);
    }

    public static void trace(Object message, Throwable t) {
        getCurrentLog().trace(message, t);
    }


    public static void error(Object message) {

        getCurrentLog().error(getCallInfo() + message);
    }

    public static void error(Object message, Throwable t) {
        getCurrentLog().error(getCallInfo() + message, t);
    }

    public static void info(Object message) {

        getCurrentLog().info(getCallInfo() + message);
    }

    public static void info(Object message, Throwable t) {
        getCurrentLog().info(getCallInfo() + message, t);
    }


}