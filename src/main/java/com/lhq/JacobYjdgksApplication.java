package com.lhq;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lhq.entity.Problem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootApplication
public class JacobYjdgksApplication {
    private static Problem currentProblem;
    //题目列表
    private static List<Problem> list = new ArrayList<>();
    //窗口
    private static JFrame win ;
    //题目及选项控件
    private static JButton title1,a1,b1,c1,d1;
    private  static SelectListener listener;
    private static Random random =new Random();
    public static void main(String[] args) {
        textToSpeech("现在进行灯光模拟考试，前准备");
        init();
        changeProblemByRandom();
        textToSpeech(currentProblem.getTitle());

    }
    /***
     *随机改变当前题目
     * @return void
     * @Author lianghanqiang
     * @Date 2022/7/8 21:30
     **/
    public static void changeProblemByRandom(){
        currentProblem = list.get(random.nextInt(list.size()-1));
        title1.setText(currentProblem.getTitle());
        a1.setText(currentProblem.getA());
        b1.setText(currentProblem.getB());
        c1.setText(currentProblem.getC());
        d1.setText(currentProblem.getD());
        win.invalidate();
    }
    public static void init(){
        win = new JFrame("考驾照:夜间灯光模拟");
        win.setResizable(false);
        win.setLocation(500,300);
        win.setSize(805,610);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        listener = new SelectListener();

        //题目区域
        title1 = new JButton("题目77777777777777777777777777777");
        title1.setBackground(new Color(8,154,154));
        title1.setLocation(0,0);
        title1.setSize(800,160);
        win.add(title1);

        //a
        a1 = new JButton("a");
        a1.setBackground(new Color(34,164,241));
        a1.setLocation(0,160);
        a1.setSize(800,100);
        a1.setName("a");
        a1.addActionListener(listener);
        win.add(a1);


        //b
        b1 = new JButton("b");
        b1.setBackground(new Color(34,164,241));
        b1.setLocation(0,260);
        b1.setName("b");
        b1.setSize(800,100);
        b1.addActionListener(listener);
        win.add(b1);

        //c
        c1 = new JButton("c");
        c1.setBackground(new Color(34,164,241));
        c1.setLocation(0,360);
        c1.setName("c");
        c1.setSize(800,100);
        c1.addActionListener(listener);
        win.add(c1);

        //d
        d1 = new JButton("d");
        d1.setBackground(new Color(34,164,241));
        d1.setLocation(0,460);
        d1.setName("d");
        d1.setSize(800,100);
        d1.addActionListener(listener);
        win.add(d1);

        JButton demo = new JButton("");
        demo.setBackground(new Color(34,164,241));
        demo.setLocation(0,560);
        demo.setName("t");
        demo.setSize(800,3);
        win.add(demo);


        win.invalidate();

        initTitle();
    }
    public static void initTitle(){
        list.add(new Problem("夜间通过急弯","远近光灯交替","近光灯","远光灯","关大灯保留小灯","a"));
        list.add(new Problem("夜间通过坡路","远近光灯交替","近光灯","远光灯","关大灯保留小灯","a"));
        list.add(new Problem("夜间通过拱桥","远近光灯交替","近光灯","远光灯","关大灯保留小灯","a"));
        list.add(new Problem("夜间通过人行横道","远近光灯交替","近光灯","远光灯","关大灯保留小灯","a"));
        list.add(new Problem("夜间超越前方车辆","远近光灯交替","近光灯","远光灯","关大灯保留小灯","a"));
        list.add(new Problem("夜间同方向近距离跟车行驶","远近光灯交替","近光灯","远光灯","关大灯保留小灯","b"));
        list.add(new Problem("夜间与机动车会车","远近光灯交替","近光灯","远光灯","关大灯保留小灯","b"));
        list.add(new Problem("夜间通过交通信号灯路口","远近光灯交替","近光灯","远光灯","关大灯保留小灯","b"));
        list.add(new Problem("前方通过路口","远近光灯交替","近光灯","远光灯","关大灯保留小灯","b"));
        list.add(new Problem("进入照明良好的道路","远近光灯交替","近光灯","远光灯","关大灯保留小灯","b"));
        list.add(new Problem("进入照明不良的道路","远近光灯交替","近光灯","远光灯","关大灯保留小灯","c"));
        list.add(new Problem("夜间在路边临时停车","远近光灯交替","近光灯","远光灯","关大灯保留小灯","d"));

    }

    /***
     *监听选择了哪个选项
     * @return
     * @Author lianghanqiang
     * @Date 2022/7/8 20:57
     **/
    public static class SelectListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // title1.setText("skaglhdfouiqwjkeghryiuftothwqqer");
            JButton source = (JButton) e.getSource();
            log.info("sourceName={}",source.getName());
            if (source.getName().equals(currentProblem.getAnswer())){
                changeProblemByRandom();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                textToSpeech(currentProblem.getTitle());
            }else {
                textToSpeech("操作失败,考试不合格,请重新选择");
                // System.exit(0);
            }
        }
    }

    /**
     * 语音转文字并播放
     *
     * @param text
     */
    public static void textToSpeech(String text) {
        ActiveXComponent ax = null;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(0));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // 下面是构建文件流把生成语音文件

            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();

            // 设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            // 设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            // 调用输出 文件流打开方法，创建一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new Variant(3), new Variant(true));
            // 设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            // 设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            // 设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            // 开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // 关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
