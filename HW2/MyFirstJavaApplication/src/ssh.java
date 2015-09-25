
import com.jcraft.jsch.*;

import java.awt.*;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

public class ssh{
  public static void sshcon(String host){

    try{
      JSch jschssh=new JSch();
     
      jschssh.addIdentity("/Users/Annabelle/Documents/NYU-POLY/3/Cloud Computing/HW2/Hw2.pem");
      String user="ec2-user";
     

    
      Session session=jschssh.getSession(user, host, 22);

      UserInfo userinfo=new MyUserInfo();
      session.setUserInfo(userinfo);
      session.connect();

      Channel channel=session.openChannel("shell");

      channel.setInputStream(System.in);
      channel.setOutputStream(System.out);

      channel.connect();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }


  public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
    public String getPassword(){ return null; }
    public boolean promptYesNo(String str){
      Object[] options={ "yes", "no" };
      int foo=JOptionPane.showOptionDialog(null, 
             str,
             "Warning", 
             JOptionPane.DEFAULT_OPTION, 
             JOptionPane.WARNING_MESSAGE,
             null, options, options[0]);
       return foo==0;
    }
  
    String passphrase;
    JTextField passphraseField=(JTextField)new JPasswordField(20);

    public String getPassphrase(){ return passphrase; }
    public boolean promptPassphrase(String message){
      Object[] ob={passphraseField};
      int result=
	JOptionPane.showConfirmDialog(null, ob, message,
				      JOptionPane.OK_CANCEL_OPTION);
      if(result==JOptionPane.OK_OPTION){
        passphrase=passphraseField.getText();
        return true;
      }
      else{ return false; }
    }
    public boolean promptPassword(String message){ return true; }
    public void showMessage(String message){
      JOptionPane.showMessageDialog(null, message);
    }
    final GridBagConstraints gridbc = 
      new GridBagConstraints(0,0,1,1,1,1,
                             GridBagConstraints.NORTHWEST,
                             GridBagConstraints.NONE,
                             new Insets(0,0,0,0),0,0);
    private Container panel;
    public String[] promptKeyboardInteractive(String destination,
                                              String name,
                                              String instruction,
                                              String[] prompt,
                                              boolean[] echo){
      panel = new JPanel();
      panel.setLayout(new GridBagLayout());

      gridbc.weightx = 1.0;
      gridbc.gridwidth = GridBagConstraints.REMAINDER;
      gridbc.gridx = 0;
      panel.add(new JLabel(instruction), gridbc);
      gridbc.gridy++;

      gridbc.gridwidth = GridBagConstraints.RELATIVE;

      JTextField[] texts=new JTextField[prompt.length];
      for(int i=0; i<prompt.length; i++){
    	gridbc.fill = GridBagConstraints.NONE;
    	gridbc.gridx = 0;
    	gridbc.weightx = 1;
        panel.add(new JLabel(prompt[i]),gridbc);

        gridbc.gridx = 1;
        gridbc.fill = GridBagConstraints.HORIZONTAL;
        gridbc.weighty = 1;
        if(echo[i]){
          texts[i]=new JTextField(20);
        }
        else{
          texts[i]=new JPasswordField(20);
        }
        panel.add(texts[i], gridbc);
        gridbc.gridy++;
      }

      if(JOptionPane.showConfirmDialog(null, panel, 
                                       destination+": "+name,
                                       JOptionPane.OK_CANCEL_OPTION,
                                       JOptionPane.QUESTION_MESSAGE)
         ==JOptionPane.OK_OPTION){
        String[] response=new String[prompt.length];
        for(int i=0; i<prompt.length; i++){
          response[i]=texts[i].getText();
        }
	return response;
      }
      else{
        return null;
      }
    }
  }
}
