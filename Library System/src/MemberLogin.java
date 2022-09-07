/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

import java.io.*;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class MemberLogin implements Serializable{
    private ArrayList<Member> memberList;
    int index=0;
    String member = "User.ser";
    
    public MemberLogin(){
        memberList = new ArrayList<>();
        memberList.add(new Member("suc","q1w2e3"));
    }
    
    public boolean checkLogin(String checkUser, String checkPw){
        readMember();
        boolean correct=false;
        for(int i=0;i<memberList.size();i++){
            if(checkUser.equals(memberList.get(i).getName()) && checkPw.equals(memberList.get(i).getPw())){
                correct=true;
            }
        }
        return correct;
    }
    public void addMember(Member newMember){
        if(userExist(newMember.getName())==true){
            Error("Error","User already exist!");
        }else{
            memberList.add(newMember);
            writeMember();
            Info("Create successful","New User has been created.");
        }
    }
    public void writeMember(){
        try{
            try (ObjectOutputStream outMem = new ObjectOutputStream(new FileOutputStream(member))) {
                outMem.writeObject(memberList);
            }
        }catch(IOException ioe){
            Error("IO Exception","File output error");
        }
    }
    
    public void readMember(){
        try{
            File f = new File(member);
            //Check file exist or not
            if(f.exists()){
                try (ObjectInputStream inMem = new ObjectInputStream(new FileInputStream(member))) {
                    memberList = (ArrayList<Member>) inMem.readObject();
                    //close when done
                }
            }else {
                Info("File not found","No member has been found.\nFirst time user, please create a new user");
            }
        }catch(IOException ioe){
            Error("IO Exception","File input error");
        }catch(ClassNotFoundException ex){
            Error("Class Not Found Exception","Class not found.");
        }
    }
    
    public boolean userExist(String uName){
        boolean found = false;
        for(int i =0; i<memberList.size(); i++){
        Member m = memberList.get(i);
            if(m.getName().equals(uName)){
                found=true;
                index = i;
            }
        }
        return found;
    }
    public void Error(String e,String em) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e);
        alert.setHeaderText(null);
        alert.setContentText(em);
        alert.show();
    }    
    public void Info(String i,String im) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(i);
        alert.setHeaderText(null);
        alert.setContentText(im);
        alert.show();
    }
}
