package xdu.edu.cn.my2048;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Maps {
    private TextView score;
    private TextView best;
    private Button[][] maps = new Button[4][4];

    public void addButton(int i, int j, Button btn) {
        maps[i][j] = btn;
    }

    private void swapText(Button btn1, Button btn2) {
        CharSequence text = btn1.getText();
        btn1.setText(btn2.getText());
        btn2.setText(text);
    }

    void up_remove_blank() {
        int i, j, k;
        for (j = 0; j < 4; j++) {
            for (i = 1; i < 4; i++) {
                k = i;
                while (k - 1 >= 0
                        && maps[k - 1][j].getText().toString().length() == 0) {// 上面的那个为空
                    swapText(maps[k][j], maps[k - 1][j]);
                    k--;

                }
            }
        }
    }

    void down_remove_blank() {
        int i, j, k;
        for (j = 0; j < 4; j++) {
            for (i = 2; i >= 0; i--) {
                k = i;
                while (k + 1 <= 3
                        && maps[k + 1][j].getText().toString().length() == 0) {// 上面的那个为空
                    swapText(maps[k][j], maps[k + 1][j]);
                    k++;
                }
            }
        }
    }

    void left_remove_blank() {
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 1; j < 4; j++) {
                k = j;
                while (k - 1 >= 0
                        && maps[i][k - 1].getText().toString().length() == 0) {// 上面的那个为空
                    swapText(maps[i][k], maps[i][k - 1]);
                    k--;
                }
            }
        }
    }

    void right_remove_blank() {
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 2; j >= 0; j--) {
                k = j;
                while (k + 1 <= 3
                        && maps[i][k + 1].getText().toString().length() == 0) {// 上面的那个为空
                    swapText(maps[i][k], maps[i][k + 1]);
                    k++;
                }
            }
        }
    }

    void left() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i][j + 1].getText().toString();

                if (s1.equals(s2) && !s1.equals("")) {
                    // maps[i][j]+=maps[i][j+1];
                    Integer sum = Integer.valueOf(maps[i][j].getText()
                            .toString());
                    sum += Integer.valueOf(maps[i][j + 1].getText().toString());

                    int total = Integer.valueOf(score.getText().toString());
                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i][j + 1].setText("");
                    left_remove_blank();
                }
            }
        }
    }

    void right() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 3; j >= 1; j--) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i][j - 1].getText().toString();
                if (s1.equals(s2) && !s1.equals("")) {
                    // maps[i][j]+=maps[i][j-1];
                    // maps[i][j-1]=0;
                    Integer sum = Integer.valueOf(maps[i][j].getText()
                            .toString());
                    sum += Integer.valueOf(maps[i][j - 1].getText().toString());
                    int total = Integer.valueOf(score.getText().toString());
                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i][j - 1].setText("");
                    right_remove_blank();
                }
            }
        }
    }

    void up() {
        int i, j;
        for (j = 0; j < 4; j++) {// 每一列
            for (i = 0; i < 3; i++) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i + 1][j].getText().toString();
                if (s1.equals(s2) && !s1.equals("")) {
                    // maps[i][j]=maps[i][j]+maps[i+1][j];
                    // maps[i+1][j]=0;
                    Integer sum = Integer.valueOf(maps[i][j].getText()
                            .toString());
                    sum += Integer.valueOf(maps[i + 1][j].getText().toString());
                    int total = Integer.valueOf(score.getText().toString());
                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i + 1][j].setText("");
                    // 移除空格
                    up_remove_blank();
                }
            }
        }
    }

    void down() {
        int i, j;
        for (j = 0; j < 4; j++) {// 每一列
            for (i = 3; i >= 1; i--) {
                String s1 = maps[i][j].getText().toString();
                String s2 = maps[i - 1][j].getText().toString();
                if (s1.equals(s2) && !s1.equals("")) {
                    // maps[i][j]=maps[i][j]+maps[i-1][j];
                    // maps[i-1][j]=0;
                    Integer sum = Integer.valueOf(maps[i][j].getText()
                            .toString());
                    sum += Integer.valueOf(maps[i - 1][j].getText().toString());
                    int total = Integer.valueOf(score.getText().toString());
                    score.setText(String.valueOf(sum + total));
                    maps[i][j].setText(sum.toString());
                    maps[i - 1][j].setText("");
                    // 移除空格
                    down_remove_blank();
                }
            }
        }
    }

    private void addNumber() {
        Random random = new Random();
        int x = random.nextInt(4);
        int y = random.nextInt(4);
        int number = random.nextInt(20);//出现2的概率为95% 4的概率5%
        if(number==0) number=4;
        else number=2;
        while (maps[x][y].getText().toString().length() != 0) {
            x = random.nextInt(4);
            y = random.nextInt(4);
        }
        maps[x][y].setText(number + "");
    }

    public void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                maps[i][j].setText("");
            }
        }
        score.setText("0");
        addNumber();
        addNumber();
    }

    private boolean isFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (maps[i][j].getText().toString().length() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean Slide(int direction) {
        if (direction == Direction.LEFT) {
            left_remove_blank();
            left();
            if (isFull())
                return true;
            else {
                addNumber();
            }
        } else if (direction == Direction.RIGHT) {
            right_remove_blank();
            right();
            if (isFull())
                return true;
            else {
                addNumber();
            }
        } else if (direction == Direction.UP) {
            up_remove_blank();
            up();
            if (isFull())
                return true;
            else {
                addNumber();
            }
        } else if (direction == Direction.DOWN) {
            down_remove_blank();
            down();
            if (isFull())
                return true;
            else {
                addNumber();
            }
        }
        return false;
    }

    public void setScore(TextView score) {
        this.score = score;
    }

    public void setBest(TextView best) {
        this.best = best;
        best.setText(getBestScore()+"");
    }
    public int getScore(){
        return Integer.valueOf(score.getText().toString());
    }
    public int getBestScore(){
        SharedPreferences sp = best.getContext().getSharedPreferences("bestScore.txt", Context.MODE_PRIVATE);
        int bestScore=sp.getInt("bestScore", 0);
        return bestScore;
    }
    public void setBestScore(int score){
        SharedPreferences sp = best.getContext().getSharedPreferences("bestScore.txt", Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putInt("bestScore", score);
        edit.commit();
    }

}