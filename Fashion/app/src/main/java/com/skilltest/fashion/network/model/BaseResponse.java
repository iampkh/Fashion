package com.skilltest.fashion.network.model;

/**
 * Every network call has error node
 * Thus this class act as a super for all network model
 */
public class BaseResponse {
    String error;

    public String getError() {
        return error;
    }
}
