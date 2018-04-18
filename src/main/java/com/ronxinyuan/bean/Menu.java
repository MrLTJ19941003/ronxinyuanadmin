package com.ronxinyuan.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by 13045 on 2018/4/18.
 */
public class Menu {
        private String text;
        private List<Map<String,String>> items;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Map<String, String>> getItems() {
            return items;
        }

        public void setItems(List<Map<String, String>> items) {
            this.items = items;
        }
}
