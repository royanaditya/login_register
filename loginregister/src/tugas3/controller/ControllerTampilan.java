package tugas3.controller;

import tugas3.model.Connector;
import tugas3.view.TampilanView;

public class ControllerTampilan {
    TampilanView view;
    Connector connect;
    
    public void showTampilPage(Connector connect){
        if(!connect.checkLogin()){
            return;
        }
        view = new TampilanView(this);
        this.connect = connect;
    }
}
