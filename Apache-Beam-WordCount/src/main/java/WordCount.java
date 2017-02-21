/**
 * MIT.
 * Author: wangxiaolei(王小雷).
 * Date:17-2-20.
 * Project:ApacheBeamWordCount.
 */


import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.options.Validation.Required;
import org.apache.beam.sdk.transforms.Aggregator;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;


public class WordCount {

    /**
     *1.a.通过Dofn编程Pipeline使得代码很简洁。b.对输入的文本做单词划分，输出。
     */
    static class ExtractWordsFn extends DoFn<String, String> {
        private final Aggregator<Long, Long> emptyLines =
                createAggregator("emptyLines", Sum.ofLongs());

        @ProcessElement
        public void processElement(ProcessContext c) {
            if (c.element().trim().isEmpty()) {
                emptyLines.addValue(1L);
            }

            // 将文本行划分为单词
            String[] words = c.element().split("[^a-zA-Z']+");
            // 输出PCollection中的单词
            for (String word : words) {
                if (!word.isEmpty()) {
                    c.output(word);
                }
            }
        }
    }

    /**
     *2.格式化输入的文本数据，将转换单词为并计数的打印字符串。
     */
    public static class FormatAsTextFn extends SimpleFunction<KV<String, Long>, String> {
        @Override
        public String apply(KV<String, Long> input) {
            return input.getKey() + ": " + input.getValue();
        }
    }
    /**
     *3.单词计数，PTransform(PCollection Transform)将PCollection的文本行转换成格式化的可计数单词。
     */
    public static class CountWords extends PTransform<PCollection<String>,
            PCollection<KV<String, Long>>> {
        @Override
        public PCollection<KV<String, Long>> expand(PCollection<String> lines) {

            // 将文本行转换成单个单词
            PCollection<String> words = lines.apply(
                    ParDo.of(new ExtractWordsFn()));

            // 计算每个单词次数
            PCollection<KV<String, Long>> wordCounts =
                    words.apply(Count.<String>perElement());

            return wordCounts;
        }
    }

    /**
     *4.可以自定义一些选项（Options），比如文件输入输出路径
     */
    public interface WordCountOptions extends PipelineOptions {

        /**
         * 文件输入选项，可以通过命令行传入路径参数，路径默认为gs://apache-beam-samples/shakespeare/kinglear.txt
         */
        @Description("Path of the file to read from")
        @Default.String("gs://apache-beam-samples/shakespeare/kinglear.txt")
        String getInputFile();
        void setInputFile(String value);

        /**
         * 设置结果文件输出路径,在intellij IDEA的运行设置选项中或者在命令行中指定输出文件路径，如./pom.xml
         */
        @Description("Path of the file to write to")
        @Required
        String getOutput();
        void setOutput(String value);
    }
    /**
     * 5.运行程序
     */
    public static void main(String[] args) {
        WordCountOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(WordCountOptions.class);
        Pipeline p = Pipeline.create(options);

        p.apply("ReadLines", TextIO.Read.from(options.getInputFile()))
                .apply(new CountWords())
                .apply(MapElements.via(new FormatAsTextFn()))
                .apply("WriteCounts", TextIO.Write.to(options.getOutput()));

        p.run().waitUntilFinish();
    }
}