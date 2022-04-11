package com.factory.demofactory.client.presenter;

import java.util.Map;

public interface ClientPresenter {
    void createClient(Map<String, String> dataClient);
    void success(int id);
    void error(String tittle, String message);
}
