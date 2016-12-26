package com.ultrapower.sc.splash.modle;

/**
 * Created by Administrator on 2016/12/23.
 */

public class Res {
    public int showapi_res_code;

    public String showapi_res_error;

    public BeanBody showapi_res_body;

    @Override
    public String toString() {
        return "Res{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }
}
