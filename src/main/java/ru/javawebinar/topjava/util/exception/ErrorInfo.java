package ru.javawebinar.topjava.util.exception;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class ErrorInfo {
    public final String url;
    public final String cause;
    public final String[] detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this(url,ex.getCause().getClass().getSimpleName(),ex.getLocalizedMessage());
    }

    public ErrorInfo(CharSequence url, String cause, String... details)
    {
        this.url = url.toString();
        this.cause = cause;
        this.detail = details;
    }
}
