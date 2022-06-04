package app.controller;

import app.interfaces.ServiceImpl;
import app.services.MultipleService;
import app.services.NormalService;

public class Controller {

    public Controller(String[] args) {
        switch (args[0]) {
            case "1":
                // 個別取得
                ServiceImpl serv = new NormalService();
                serv.callService(args);
                break;
            case "2":
                // 複数取得
                ServiceImpl serv2 = new MultipleService();
                serv2.callService(args);
                break;
            default:
                break;
        }
    }

}
