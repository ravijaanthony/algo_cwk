// Reference - https://www.codespeedy.com/get-the-elapsed-time-in-seconds-in-java/

import java.util.concurrent.TimeUnit;

public class Stopwatch
{

    private long timeToStart;
    private long timeToStop;
    private long elapsedTime;

    /*
     * Start method
     * To start the stop watch
     */
    public void start()
    {
        timeToStart = System.currentTimeMillis();
    }

    /*
     * Stop method
     * To stop the stop watch
     */
    public void stop()
    {
        timeToStop = System.currentTimeMillis();
    }

    /*
     * Time method
     * Calculate elapsed time
     * 1 second = 1_000_000_000 nano seconds
     */
    public long time()
    {
        elapsedTime = timeToStop - timeToStart;
        return (elapsedTime);
    }

    public String toString(){
        return "\nelapsed time in milliseconds: " + time() + " milliseconds.\n" +
                "elapsed time in seconds: " + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.MILLISECONDS) + " seconds.\n";
    }
}
