package com.msg91.sendotp.sample;

public class Cheque2 {

    private String image;
    private String status;
    private String user;
    private String prize;
    private String prize1;
    private String prize2;
    private String prize3;
    private String des;


    public Cheque2(String image, String status, String user, String prize, String des,String prize1,String prize2,String prize3) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.prize=prize;
          this.des=des;
        this.prize1=prize1;
        this.prize2=prize2;
        this.prize3=prize3;
    }

    public Cheque2() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }
    public String getUser() {
        return user;
    }
    public String getPrize() { return  prize; }
public String getDes()
{
    return des;
}
    public String getPrize1() { return  prize1; }
    public String getPrize2() { return  prize2; }
    public String getPrize3() { return  prize3; }

}
