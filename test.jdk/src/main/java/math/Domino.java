package math;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author wanggen on 14-7-27.
 * @Desc:
 */
public class Domino {

    public static void main(String[] args) throws Exception {

        File src = new File(Thread.currentThread().getContextClassLoader().getResource("data").getFile());
        File dest = new File(Thread.currentThread().getContextClassLoader().getResource("dest").getFile());

        List<String> counts = Files.readLines(src, Charset.forName("UTF-8"),
                new LineProcessor<List<String>>() {
                    Pattern numP = Pattern.compile("^\\d++$");
                    List<String> result = Lists.newArrayList();
                    @Override
                    public boolean processLine(String line) throws IOException {
                        if(line != null)
                            line = line.trim();
                        boolean toAdd = line!=null && !line.equals("") && numP.matcher(line).matches();
                        if(toAdd)
                            result.add(line);
                        return toAdd;
                    }

                    @Override
                    public List<String> getResult() {
                        return result;
                    }
                });

        List<Integer> datas = Lists.transform(counts, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input.trim());
            }
        });


        double variance = Maths.variance(datas);

        System.out.println("方差: "+variance);

        Files.append(variance+"\n"+datas+"\n\n", dest, Charset.forName("UTF-8"));

    }



}

class Maths{

    public static double avg(List<Integer> datas){

        double avg=0, sum=0;

        for(Integer num: datas){
            sum += num;
        }

        return sum/datas.size();

    }

    public static double variance(List<Integer> datas){

        double avg = avg(datas);

        double variance = 0, sqr_sum=0;

        for(Integer data: datas){
            sqr_sum += Math.pow(data-avg, 2);
        }

        variance = Math.sqrt(sqr_sum/datas.size());

        return variance;

    }

}

