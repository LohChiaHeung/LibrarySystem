
import java.io.Serializable;

/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

public class Member implements Serializable{
    //Instance variable
    private String userName,userPw;
    
    //Constructor
    public Member(){}
    public Member(String userName, String userPw){
        this.userName = userName;
        this.userPw = userPw;
    }
    //get Method
    public String getName(){return userName;}
    public String getPw(){return userPw;}
    
    //set Method
    public void setName(String newName){userName=newName;}
    public void setPw(String newPw){userPw=newPw;}
    
    @Override
    public String toString() {
        return "UserName:"+userName+"\nPassword:"+userPw;
    }
}
