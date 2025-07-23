package mission.utility;

import mission.Config;

public class DebugLogging {
    static String directoryLog = "";
    static String suspiciousPoint = "";

    public static void addTrace(String step){
        directoryLog = step + " > " + directoryLog;
    }

    public static void flagSuspicious(String part) { suspiciousPoint = part; }
    public static void print(){
        if(Config.DEBUG_MODE){
            System.out.println("오류 경로 : "+directoryLog.substring(0, directoryLog.length()-4));
            System.out.println("원인 : "+suspiciousPoint);
        }
    }
}
