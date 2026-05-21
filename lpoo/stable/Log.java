package lpoo.stable;

/**
 *
 * @author // put your name(s) here
 */
public final class Log {
  
  private Log() {}

  public static void info(String message) {
    System.out.println("[INFO] " + message);
  }

  public static void error(String message) {
    System.err.println("[ERRO SEMÂNTICO] " + message);
  }
} // Log
