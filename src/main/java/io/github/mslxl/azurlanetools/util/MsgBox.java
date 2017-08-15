package io.github.mslxl.azurlanetools.util;

import javax.swing.*;
import java.awt.*;

public class MsgBox {
    private static Component defultComponent = null;

    public static void error(String msg){
        error(msg,"Error");
    }
    public static void error(String msg,String title){
        error(msg,title,defultComponent);
    }
    public static void error(String msg, String title, Component component){
        JOptionPane.showMessageDialog(component,msg,title,JOptionPane.ERROR_MESSAGE);
    }
    public static void error(String msg,Component component){
        error(msg,"Error", component);
    }

    public static void message(String msg){
        message(msg,"Message");
    }
    public static void message(String msg,String title){
        message(msg,title,defultComponent);
    }
    public static void message(String msg, String title, Component component){
        JOptionPane.showMessageDialog(component,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void message(String msg,Component component){
        message(msg,"Message",defultComponent);
    }

    public static void setDefultComponent(Component component){
        defultComponent = component;
    }
}
