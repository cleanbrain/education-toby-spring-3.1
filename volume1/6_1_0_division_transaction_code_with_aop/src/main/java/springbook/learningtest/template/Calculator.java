package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-08
 */
public class Calculator {
    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null) {
                try {br.close();}
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;

            while ((line = br.readLine()) != null ) {
                res = callback.doSomethingWithline(line, res);
            }

            return res;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null) {
                try {br.close();}
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer calcSum(String filepath) throws IOException {
        /*return fileReadTemplate(filepath, new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer sum = 0;
                String line = null;
                while ((line = br.readLine()) != null ) {
                    sum += Integer.valueOf(line);
                }

                return sum;
            }
        });*/

        LineCallback<Integer> callback = (line, value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filepath, callback, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        /*return fileReadTemplate(filepath, new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer sum = 1;
                String line = null;
                while ((line = br.readLine()) != null ) {
                    sum *= Integer.valueOf(line);
                }

                return sum;
            }
        });*/

        LineCallback<Integer> callback = (line, value) -> value * Integer.valueOf(line);
        return lineReadTemplate(filepath, callback, 1);
    }

    public String concatenate(String filepath) throws IOException {
        LineCallback<String> concatenateCallback = (line, value) -> value + line;
        return lineReadTemplate(filepath, concatenateCallback, "");
    }
}
