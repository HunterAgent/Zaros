package framework.Utils;

import simple.robot.api.ClientContext;

public class Logger {
    public static void log(String log_message)
    {
        ClientContext.instance().log(log_message);
    }
}
