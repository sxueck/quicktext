package com.sxueck.linktext;

import java.util.LinkedList;

public class Link {
    private final LinkedList<String> Contents = new LinkedList<String>();

    public void PushContent(String content) {
        Contents.add(content);
    }

    // 显示最后几个输入
    public String[] PrintContent(int number) {
        int size = Math.min(number, Contents.size());
        int n = 0;
        String[] LastInput = Contents.toArray(new String[number]);
        for (int i = 0; i < size; i++) {
            if (i > size - number) {
                LastInput[n++] = Contents.get(i);
            }
        }
        return LastInput;
    }
}
