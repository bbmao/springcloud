package com.guigu.springcloud.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther:mao
 * @create: 2021-01-02 17:28:22
 **/
@Data
@NoArgsConstructor
public class CommonResult<T>{

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer id, String message, Object o) {
        this.code = id;
        this.message = message;
        this.data = (T) o;
    }
}
