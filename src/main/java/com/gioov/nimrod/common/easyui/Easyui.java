package com.gioov.nimrod.common.easyui;

/**
 * @author godcheese
 * @date 2018/2/22
 */
public class Easyui {

    public enum State {

        /**
         * 文件夹开
         */
        OPEN("open"),

        /**
         * 文件夹关
         */
        CLOSED("closed"),
        ;
        private String value;

        State(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
