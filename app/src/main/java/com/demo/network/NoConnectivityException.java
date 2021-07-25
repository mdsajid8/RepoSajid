package com.demo.network;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "You are not connected to Internet!";
    }
}