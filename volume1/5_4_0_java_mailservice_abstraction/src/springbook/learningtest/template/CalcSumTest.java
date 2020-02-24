package springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-08
 */
public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = this.calculator.calcSum(this.numFilepath);
        assertThat(sum, is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    @Test
    public void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(this.numFilepath), is("1234"));
    }

    public static void main(String[] args) {
        JUnitCore.main("springbook.learningtest.template.CalcSumTest");
    }
}
