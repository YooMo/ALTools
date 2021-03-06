package io.github.mslxl.azurlanetools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.mslxl.azurlanetools.util.FileUtil;
import io.github.mslxl.azurlanetools.util.MsgBox;
import io.github.mslxl.azurlanetools.util.Phone;
import io.github.mslxl.azurlanetools.util.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FastBounds extends JFrame implements MouseListener,ActionListener{
    public static volatile BufferedImage screenshot = Phone.INSTANCE.screenshot(true,true);
    private JPanel panel = new JPanel(){


        @Override
        public void paint(Graphics g) {
            super.paint(g);




            g.drawImage(screenshot,0,0,null);
            g.setColor(Color.BLACK);
            g.drawString("S",markSX,markSY);
            g.drawString("E",markEX,markEY);
            g.drawRect(markSX,markSY,markEX-markSX,markEY-markSY);
        }
    };
    private int markSX = 0;
    private int markSY = 0;
    private int markEX = 0;
    private int markEY = 0;

    private int mouseX = 0;
    private int mouseY = 0;


    private JPopupMenu menu = new JPopupMenu();
    private JMenuItem markStart = new JMenuItem("Mark Start");
    private JMenuItem markEnd = new JMenuItem("Mark End");
    private JMenuItem reScreenshot = new JMenuItem("Re screenshot");
    private JMenuItem save = new JMenuItem("Save");
    public FastBounds(){
        setSize(screenshot.getWidth(),screenshot.getHeight());
        setResizable(false);
        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.add(markStart);
        menu.add(markEnd);
        menu.add(reScreenshot);
        menu.add(save);
        markStart.addActionListener(this);
        markEnd.addActionListener(this);
        reScreenshot.addActionListener(this);
        save.addActionListener(this);
        panel.addMouseListener(this);
        setVisible(true);

    }
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new FastBounds();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX =e.getX();
        mouseY =e.getY();
        menu.show(panel,e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == reScreenshot){
            screenshot = Phone.INSTANCE.screenshot(true,true);

        }else if (src == markStart){
            markSX = mouseX;
            markSY = mouseY;
        }else if (src == markEnd){
            markEX = mouseX;
            markEY = mouseY;
        }else if (src == save){
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Json文件(*.json)", "json");
            chooser.setFileFilter(filter);
            chooser.showSaveDialog(panel);
            File file = chooser.getSelectedFile();
            if (file!=null){
                if (!file.getName().endsWith(".json")){
                    file = new File(file.getAbsolutePath()+".json");
                }
                Rectangle rect = new Rectangle(markSX,markSY,markEX-markSX,markEY - markSY);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonText = gson.toJson(rect);
                try {
                    FileUtil.write(jsonText,file);
                } catch (IOException e1) {
                    MsgBox.error(e1.toString());
                }
            }
        }
        updateJPanel();
    }

    public void updateJPanel(){



        panel.validate();
        panel.updateUI();
        panel.update(panel.getGraphics());
        panel.repaint();
        panel.revalidate();
        panel.invalidate();
        this.validate();
        this.repaint();
        this.revalidate();
        this.invalidate();

        this.remove(panel);
        this.add(panel);
    }
}
