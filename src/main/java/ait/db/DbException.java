package ait.db;

/**
 * Created by suomiy on 5/2/16.
 */
public class DbException extends Exception {

    public DbException(String s) {
        super(s);
    }

    public DbException(Throwable throwable) {
        super(throwable);
    }

    public DbException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
