package com.guigu.springcloud.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {

    success(0, "success"),
    error(-1, "未知错误"),
    negative_num(-2, "id不能为负数"),
    ;

    private Integer id;

    private String message;

    ErrorCode(int i, String message) {
        this.id = i;
        this.message = message;
    }


    public CommonResult response(Object data){
        return new CommonResult(success.id, success.message, data);
    }

    public CommonResult exception(){
        return new CommonResult(id, message, null);
    }
}
