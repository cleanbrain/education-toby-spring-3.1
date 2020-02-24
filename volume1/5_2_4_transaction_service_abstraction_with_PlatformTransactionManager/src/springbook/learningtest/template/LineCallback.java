package springbook.learningtest.template;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-08
 */
public interface LineCallback<T> {
    T doSomethingWithline(String line, T value);
}
