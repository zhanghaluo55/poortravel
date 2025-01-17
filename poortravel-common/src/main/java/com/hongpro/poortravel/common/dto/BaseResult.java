package com.hongpro.poortravel.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {
    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";


    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult ok() {
        return createResult(RESULT_OK, null, SUCCESS, null, null);
    }

    public static BaseResult ok(Object data) {
        return createResult(RESULT_OK, data, SUCCESS, null, null);
    }

    public static BaseResult notOk(List<BaseResult.Error> errors) {
        return createResult(RESULT_NOT_OK, null, "", null, errors);
    }

    public static BaseResult ok(Object data, Cursor cursor) {
        return createResult(RESULT_OK, data, SUCCESS, cursor, null);
    }

    /**
     * @param result
     * @param data
     * @param success
     * @param cursor
     * @param errors
     * @return com.hongpro.poortravel.common.dto.BaseResult
     * @author: Rainer
     * @description: TODO
     * @date: 2020/1/22 1:00
     */
    private static BaseResult createResult(String result, Object data, String success, Cursor cursor, List<Error> errors) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setCursor(cursor);
        baseResult.setSuccess(success);
        baseResult.setErrors(errors);

        return baseResult;
    }

    @Data
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error {
        private String field;
        private String message;

        public Error() {

        }

      /*  public Error(String field,String message){
            this.field=field;
            this.message=message;
        }*/
    }
}
