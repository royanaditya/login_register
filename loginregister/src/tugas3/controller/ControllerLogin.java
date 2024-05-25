/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas3.controller;

import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;
import tugas3.model.Connector;
import tugas3.view.LoginView;
import tugas3.view.RegisterView;

/**
 *
 * @author user
 */
public class ControllerLogin {
    LoginView viewLogin;
    RegisterView regisView;

    public void showViewLogin() {
        viewLogin = new LoginView(this);
    }

    public void showViewRegister() {
        regisView = new RegisterView(this);
    }

    public void inputLogin(String username, String password) {
        // Cek apakah username atau password kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connector connect = new Connector(username, password);

        if (connect.checkLogin()) {
            ControllerTampilan mc = new ControllerTampilan();
            mc.showTampilPage(connect);
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal, username atau password salah", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inputRegister(String username, String password) {
        // Cek apakah username atau password kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connector user = new Connector(username, password);
        if (user.registerData()) {
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan data", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            regisView.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Data sudah ada di database", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
