package app.controller;

import app.interfaces.ServiceImpl;
import app.services.NormalService;

public class Controller {

    public Controller(String[] args) {
        int selectNumber = Integer.parseInt(args[0]);
        switch (selectNumber) {
            case 1:
                // 文字列を直接入力
                ServiceImpl serv = new NormalService();
                serv.callService(args);
                break;
            case 2:
                // ファイルで取得

                break;
            default:
                break;
        }
    }

}
