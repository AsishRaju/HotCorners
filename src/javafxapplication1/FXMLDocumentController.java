/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.awt.MouseInfo;
import java.awt.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.Properties;
import javafx.scene.control.Tooltip;

/**
 *
 * @author windows
 */
public class FXMLDocumentController implements Initializable, Runnable {
    @FXML
    public ComboBox<String> ChoiceBox1; 
    public ComboBox<String> ChoiceBox2; 
    public ComboBox<String> ChoiceBox3; 
    public ComboBox<String> ChoiceBox4; 
    ObservableList<String>choiceList=FXCollections.observableArrayList("Choose Action","ShutDown","Log-off","Lock","Screen Off","Task Manager","Custom App");
    
    @FXML
    //path_label
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    //on-off_label
    public Label labelon1;
    public Label labelon2;
    public Label labelon3;
    public Label labelon4;
    public Label labeloff1;
    public Label labeloff2;
    public Label labeloff3;
    public Label labeloff4;
    
    @FXML
    public Spinner<Integer> spinner1;
    public Spinner<Integer> spinner2;
    public Spinner<Integer> spinner3;
    public Spinner<Integer> spinner4;
    
    @FXML
    public   ToggleSwitch oneButton ;
    public   ToggleSwitch twoButton ;
    public   ToggleSwitch threeButton ;
    public   ToggleSwitch fourButton;
    
    @FXML
     public Button restore;
    
    //@iDefined_runenv
    List<String> commands = new ArrayList<String>(); //cmd_commands_list
    Thread th; //thread_instance
    double screenHeight=0,screenWidth=0; //screen_dimension_variables
    Process cmd; //cmd_object
    long s1,s2,s3,s4; //corner_time_Sensitivity
    int cb1,cb2,cb3,cb4; // corner_dropdown_index
    String appn1="null",appn2="null",appn3="null",appn4="null"; //custom_app_name
    String abspath1="null",abspath2="null",abspath3="null",abspath4="null"; //custom_app_path
    Boolean rc1,rc2,rc3,rc4; // corner_enable_button
    
    //@iDefined_prevenv
    Boolean pc1,pc2,pc3,pc4;// corner_enable_button
    int pcb1,pcb2,pcb3,pcb4;// corner_dropdown_index
    int ps1,ps2,ps3,ps4;// corner_time_Sensitivity
    String pappn1,pappn2,pappn3,pappn4; //custom_app_name
    String pabspath1,pabspath2,pabspath3,pabspath4; //custom_app_path
    
    
    
    
    
    public void loadRes()
    {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight=screenSize.getHeight()-1;
        screenWidth=screenSize.getWidth()-1;
         try {
            
            FileInputStream in = new FileInputStream("config.properties");
            Properties props = new Properties();
            props.load(in);
            rc1=Boolean.parseBoolean(props.getProperty("corner1_enable"));
            rc2=Boolean.parseBoolean(props.getProperty("corner2_enable"));
            rc3=Boolean.parseBoolean(props.getProperty("corner3_enable"));
            rc4=Boolean.parseBoolean(props.getProperty("corner4_enable"));
            cb1=Integer.parseInt(props.getProperty("corner1_index"));
            cb2=Integer.parseInt(props.getProperty("corner2_index"));
            cb3=Integer.parseInt(props.getProperty("corner3_index"));
            cb4=Integer.parseInt(props.getProperty("corner4_index"));
            s1=Integer.parseInt(props.getProperty("corner1_time"));
            s2=Integer.parseInt(props.getProperty("corner2_time"));
            s3=Integer.parseInt(props.getProperty("corner3_time"));
            s4=Integer.parseInt(props.getProperty("corner4_time"));
            appn1=props.getProperty("corner1_fname");
            appn2=props.getProperty("corner2_fname");
            appn3=props.getProperty("corner3_fname");
            appn4=props.getProperty("corner4_fname");
            abspath1=props.getProperty("corner1_runpath");
            abspath2=props.getProperty("corner2_runpath");
            abspath3=props.getProperty("corner3_runpath");
            abspath4=props.getProperty("corner4_runpath");
            in.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void prev_corners_state()
    {
        oneButton.setSelected(rc1);
        twoButton.setSelected(rc2);
        threeButton.setSelected(rc3);
        fourButton.setSelected(rc4);
        if(rc1==false)
        {
           ChoiceBox1.setDisable(true);
           spinner1.setDisable(true);
           labelon1.setVisible(false);
           labeloff1.setVisible(true);
        }
        else{
            ChoiceBox1.setDisable(false);
           spinner1.setDisable(false);
           labelon1.setVisible(true);
           labeloff1.setVisible(false);
        }
        if(rc2==false)
        {
           ChoiceBox2.setDisable(true);
           spinner2.setDisable(true);
           labelon2.setVisible(false);
           labeloff2.setVisible(true);
           
           
        }
        else{
            ChoiceBox2.setDisable(false);
           spinner2.setDisable(false);
           labelon2.setVisible(true);
           labeloff2.setVisible(false);
        }
        if(rc3==false)
        {
           ChoiceBox3.setDisable(true);
           spinner3.setDisable(true);
           labelon3.setVisible(false);
           labeloff3.setVisible(true);
           
           
        }
        else{
            ChoiceBox4.setDisable(false);
           spinner4.setDisable(false);
            labelon3.setVisible(true);
           labeloff3.setVisible(false);
        }
        if(rc4==false)
        {
           ChoiceBox4.setDisable(true);
           spinner4.setDisable(true);
           labelon4.setVisible(false);
           labeloff4.setVisible(true);
           
       
        }
        else{
            ChoiceBox4.setDisable(false);
           spinner4.setDisable(false);
           labelon4.setVisible(true);
           labeloff4.setVisible(false);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        loadRes();
        ChoiceBox1.setItems(choiceList);
        ChoiceBox2.setItems(choiceList);
        ChoiceBox3.setItems(choiceList);
        ChoiceBox4.setItems(choiceList);
        SpinnerValueFactory<Integer> spinnerValue1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
        SpinnerValueFactory<Integer> spinnerValue2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
        SpinnerValueFactory<Integer> spinnerValue3=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
        SpinnerValueFactory<Integer> spinnerValue4=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
       
        this.spinner1.setValueFactory(spinnerValue1);
        this.spinner2.setValueFactory(spinnerValue2);
        this.spinner3.setValueFactory(spinnerValue3);
        this.spinner4.setValueFactory(spinnerValue4);
        prev_corners_state();
        
        ChoiceBox1.getSelectionModel().select(cb1);
        ChoiceBox2.getSelectionModel().select(cb2);
        ChoiceBox3.getSelectionModel().select(cb3);
        ChoiceBox4.getSelectionModel().select(cb4);
        if(ChoiceBox1.getSelectionModel().getSelectedIndex()==6)
        {
            label1.setText(appn1);
        }
        if(ChoiceBox2.getSelectionModel().getSelectedIndex()==6)
        {
            label2.setText(appn2);
        }
        if(ChoiceBox3.getSelectionModel().getSelectedIndex()==6)
        {
            label3.setText(appn3);
        }
        if(ChoiceBox4.getSelectionModel().getSelectedIndex()==6)
        {
            label4.setText(appn4);
        }
        
        spinner1.getValueFactory().setValue((int)s1);
        spinner2.getValueFactory().setValue((int)s2);
        spinner3.getValueFactory().setValue((int)s3);
        spinner4.getValueFactory().setValue((int)s4);
        
        
    
        commands.add(" ");
        commands.add("shutdown /s");
        commands.add("shutdown /l");
        commands.add("rundll32.exe user32.dll, LockWorkStation");
        commands.add("cmd /c powershell (Add-Type '[DllImport(\\\"user32.dll\\\")]^public static extern int SendMessage(int hWnd, int hMsg, int wParam, int lParam);' -Name a -Pas)::SendMessage(-1,0x0112,0xF170,2)");
        commands.add("cmd /c taskmgr");    
        
        th=new Thread(this);
        th.start();

    }  
    
    public void c1bclick(MouseEvent event)
    {
        if(oneButton.isSelected()==true)
        {
           ChoiceBox1.setDisable(false);
           spinner1.setDisable(false);
           labelon1.setVisible(true);
           labeloff1.setVisible(false);
        }
        else
        {
            ChoiceBox1.setDisable(true);
           spinner1.setDisable(true);
           labelon1.setVisible(false);
           labeloff1.setVisible(true);

        }
    }
    public void c2bclick(MouseEvent event)
    {
        if(twoButton.isSelected()==true)
        {
           ChoiceBox2.setDisable(false);
           spinner2.setDisable(false);
           labelon2.setVisible(true);
           labeloff2.setVisible(false);
        }
        else
        {
            ChoiceBox2.setDisable(true);
           spinner2.setDisable(true);
           labelon2.setVisible(false);
           labeloff2.setVisible(true);

        }
    }
public void c3bclick(MouseEvent event)
    {
        if(threeButton.isSelected()==true)
        {
           ChoiceBox3.setDisable(false);
           spinner3.setDisable(false);
           labelon3.setVisible(true);
           labeloff3.setVisible(false);
        }
        else
        {
            ChoiceBox3.setDisable(true);
           spinner3.setDisable(true);
           labelon3.setVisible(false);
           labeloff3.setVisible(true);

        }
    }
public void c4bclick(MouseEvent event)
    {
        if(fourButton.isSelected()==true)
        {
           ChoiceBox4.setDisable(false);
           spinner4.setDisable(false);
           labelon4.setVisible(true);
           labeloff4.setVisible(false);
        }
        else
        {
            ChoiceBox4.setDisable(true);
           spinner4.setDisable(true);
           labelon4.setVisible(false);
           labeloff4.setVisible(true);

        }
    }
    
    public void comboSelection1(ActionEvent event)
    {
        if((ChoiceBox1.getValue()).equals("Custom App"))
            {
                 FileChooser fc=new FileChooser();
                 fc.setInitialDirectory(new File("C:\\Users\\windows\\Desktop"));
                 fc.getExtensionFilters().addAll(new ExtensionFilter("Application","*.exe"));
                 File selectedFile=fc.showOpenDialog(null);
                 if(selectedFile !=null)
                 {
                        appn1=selectedFile.getName();
                        abspath1="cmd /c"+"\"" + selectedFile.getAbsolutePath() + "\"";
                        label1.setText(appn1);
                        System.out.println(abspath1 + appn1);
                 }
                 
            }
        else
        {
            label1.setText(" ");
        }
    }
    public void comboSelection2(ActionEvent event)
    {
        if((ChoiceBox2.getValue()).equals("Custom App"))
            {
                 FileChooser fc=new FileChooser();
                 fc.setInitialDirectory(new File("C:\\Users\\windows\\Desktop"));
                 fc.getExtensionFilters().addAll(new ExtensionFilter("Application","*.exe"));
                 File selectedFile=fc.showOpenDialog(null);
                 if(selectedFile !=null)
                 {
                 appn2=selectedFile.getName();
                 abspath2="cmd /c"+"\"" + selectedFile.getAbsolutePath() + "\"";
                 label2.setText(appn2);
                 System.out.println(abspath2);
                 }
            }
        else
        {
            label2.setText(" ");
        }
    }
    public void comboSelection3(ActionEvent event)
    {
        if((ChoiceBox3.getValue()).equals("Custom App"))
            {
                 FileChooser fc=new FileChooser();
                 fc.setInitialDirectory(new File("C:\\Users\\windows\\Desktop"));
                 fc.getExtensionFilters().addAll(new ExtensionFilter("Application","*.exe"));
                 File selectedFile=fc.showOpenDialog(null);
                 if(selectedFile !=null)
                 {
                 appn3=selectedFile.getName();
                 abspath3="cmd /c"+"\"" + selectedFile.getAbsolutePath() + "\"";
                 label3.setText(appn3);
                 System.out.println(abspath3);
                 }
            }
        else
        {
            label3.setText(" ");
        }
    }
    public void comboSelection4(ActionEvent event)
    {
        if((ChoiceBox4.getValue()).equals("Custom App"))
            {
                 FileChooser fc=new FileChooser();
                 fc.setInitialDirectory(new File("C:\\Users\\windows\\Desktop"));
                 fc.getExtensionFilters().addAll(new ExtensionFilter("Application","*.exe"));
                 File selectedFile=fc.showOpenDialog(null);
                 if(selectedFile !=null)
                 {
                     appn4=selectedFile.getName();
                 abspath4="cmd /c"+"\"" + selectedFile.getAbsolutePath() + "\"";
                 label4.setText(appn4);
                 System.out.println(abspath4);
            }    }
        else
        {
            label4.setText(" ");
        }
    }  
    public void label_click_1(ActionEvent event)
    {
        comboSelection1(event);
    }
    public void label_click_2(ActionEvent event)
    {
        comboSelection2(event);
    }
    public void label_click_3(ActionEvent event)
    {
        comboSelection3(event);
    }
    public void label_click_4(ActionEvent event)
    {
        comboSelection4(event);
    }
    
    public void close(MouseEvent event)
    {
        System.exit(0);
        
    }
    public void min(MouseEvent event)
    {
        Stage s=(Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
    }
    
    public void restore_button(ActionEvent event)
    {
        try{
            
            FileInputStream in = new FileInputStream("config.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream("config.properties");
            props.setProperty("corner1_enable", "true");            
            props.setProperty("corner1_index", "1");            
            props.setProperty("corner1_time", "1");            
            props.setProperty("corner1_fname", "null"); 
            props.setProperty("corner1_runpath", "null"); 
            
            props.setProperty("corner2_enable", "true");            
            props.setProperty("corner2_index", "1");            
            props.setProperty("corner2_time", "1");            
            props.setProperty("corner2_fname", "null"); 
            props.setProperty("corner2_runpath", "null"); 

            props.setProperty("corner3_enable", "true");            
            props.setProperty("corner3_index", "1");            
            props.setProperty("corner3_time", "1");            
            props.setProperty("corner3_fname", "null"); 
            props.setProperty("corner3_runpath", "null"); 

            props.setProperty("corner4_enable", "true");            
            props.setProperty("corner4_index", "1");            
            props.setProperty("corner4_time", "1");            
            props.setProperty("corner4_fname", "null"); 
            props.setProperty("corner4_runpath", "null");
            
            props.store(out,null);
            out.close();
        }
        catch(Exception e){
            
            System.out.println(e.getMessage());
            
        }
        System.exit(0);
        
    }
    public void about(ActionEvent event)
    {
        try{
            cmd = Runtime.getRuntime().exec("cmd /c start https://github.com/AsishRaju");
        }
        catch(Exception e)
        {
            
        }
    }
    public void reload_button(ActionEvent event)
    {
        
        System.out.println(screenWidth+" "+screenHeight);
        cb1=ChoiceBox1.getSelectionModel().getSelectedIndex();
        cb2=ChoiceBox2.getSelectionModel().getSelectedIndex();
        cb3=ChoiceBox3.getSelectionModel().getSelectedIndex();
        cb4=ChoiceBox4.getSelectionModel().getSelectedIndex();
        rc1=oneButton.isSelected();
        rc2=twoButton.isSelected();
        rc3=threeButton.isSelected();
        rc4=fourButton.isSelected();
        s1=spinner1.getValue();
        s2=spinner2.getValue();
        s3=spinner3.getValue();
        s4=spinner4.getValue();
        try{
            
            FileInputStream in = new FileInputStream("config.properties");
            Properties props = new Properties();
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream("config.properties");
            props.setProperty("corner1_enable", Boolean.toString(rc1));            
            props.setProperty("corner1_index", Integer.toString(cb1));            
            props.setProperty("corner1_time", Long.toString(s1));            
            props.setProperty("corner1_fname", appn1); 
            props.setProperty("corner1_runpath", abspath1); 
            System.out.println(abspath1+appn1+" hyyy");
            
            
            props.setProperty("corner2_enable", Boolean.toString(rc2));
            props.setProperty("corner2_index", Integer.toString(cb2));
            props.setProperty("corner2_time", Long.toString(s2));
            props.setProperty("corner2_fname", appn2); 
            props.setProperty("corner2_runpath", abspath2);
            
            
            
            props.setProperty("corner3_enable", Boolean.toString(rc3));
            props.setProperty("corner3_index", Integer.toString(cb3));
            props.setProperty("corner3_time", Long.toString(s3));
            props.setProperty("corner3_fname", appn3); 
            props.setProperty("corner3_runpath", abspath3);
            
            
            
            props.setProperty("corner4_enable", Boolean.toString(rc4));
            props.setProperty("corner4_index", Integer.toString(cb4));
            props.setProperty("corner4_time", Long.toString(s4));
            props.setProperty("corner4_fname", appn4); 
            props.setProperty("corner4_runpath", abspath4);
            props.store(out,null);
            out.close();
        }
        catch(Exception e){
            
            System.out.println(e.getMessage());
            
        }
    }
    
    
    public void runcmd(int choice)
    {
        try{
            cmd = Runtime.getRuntime().exec(commands.get(choice));
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    public void runapp(String commd)
    {
        System.out.println(commd + "here in runapp");
        try{
            cmd = Runtime.getRuntime().exec(commd);
        }
        catch(Exception e)
        {
            
        }
        
    }
    
    
    @Override
    public void run() {
        s1*=1000;
        s2*=1000;
        s3*=1000;
        s4*=1000;
        
        while(true == true)
        {
            try{
                Thread.sleep(1000);
//                System.out.println(spinner1.getValue());
                System.out.println(cb1);
                System.out.println(cb2);
                System.out.println(cb3);
                System.out.println(cb4);
                double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                System.out.println("X:" + mouseX);
                System.out.println("Y:" + mouseY);
                
                    try {
                        if(mouseX==0 && mouseY==0 && rc1 && cb1!=0)
                        { 
                            Thread.sleep(s1) ;
                           if(cb1!=6)
                           {
                            runcmd(cb1);   
                           }
                           else
                           {
                               runapp(abspath1);
                           }
                        }
                        else if(mouseX==screenWidth && mouseY==0 && rc2 && cb2!=0)
                        { 
                            Thread.sleep(s2) ;
                            if(cb2!=6)
                           {
                            runcmd(cb2);   
                           }
                           else
                           {
                               runapp(abspath2);
                           }
                        }
                        else if(mouseX==screenWidth && mouseY==screenHeight && rc3 && cb3!=0)
                        { 
                            Thread.sleep(s3) ;
                            if(cb3!=6)
                           {
                            runcmd(cb3);   
                           }
                           else
                           {
                               runapp(abspath3);
                           }
                           
                        }
                        else if(mouseX==0 && mouseY==screenHeight && rc4 && cb4!=0)
                        { 
                            Thread.sleep(s4) ;
                            if(cb4!=6)
                           {
                            runcmd(cb4);   
                           }
                           else
                           {
                               runapp(abspath4);
                           }
                        }
                        else{
                            System.out.println("not working");
                        }

                    } catch (Exception e) {
                                    //TODO: handle exception
                    }
            
        }
        catch(InterruptedException e){

            System.out.println(e);
        } 
 
        }
    }
    
    }








    
