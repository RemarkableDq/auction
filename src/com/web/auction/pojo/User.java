package com.web.auction.pojo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class User {
    private Integer userid;
    
//    用户名的长度必须要5到10
    @Size(min = 5,max=10,message = "{user.register.username.error}" )
    private String username;
     
//    指定密码的长度不低于6位
    @Size(min = 6,message="{user.register.password.error}")
    private String userpassword;
    
//      指定身份证必须是18位数字,正则表达式为\d{18}
    @Pattern(regexp = "\\d{18}",message = "{user.register.usercardno.error}")
    private String usercardno;
    
//  指定电话号码必须是11位数字,正则表达式为\d{11}
    @Pattern(regexp = "\\d{11}",message = "{user.register.usertel.error}")
    private String usertel;
    
//  指定住址必须是6到18位的字符串   
//    @Length(min = 6,max = 18,message = "{user.register.useraddress.error}")
    private String useraddress;
     
//    指定邮政编码必须是邮件格式
//    @Email(message = "{user.register.userpostnumber.error}")
    private String userpostnumber;

    private Integer userisadmin;

    private String userquestion;

    private String useranswer;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUsercardno() {
        return usercardno;
    }

    public void setUsercardno(String usercardno) {
        this.usercardno = usercardno == null ? null : usercardno.trim();
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public String getUserpostnumber() {
        return userpostnumber;
    }

    public void setUserpostnumber(String userpostnumber) {
        this.userpostnumber = userpostnumber == null ? null : userpostnumber.trim();
    }

    public Integer getUserisadmin() {
        return userisadmin;
    }

    public void setUserisadmin(Integer userisadmin) {
        this.userisadmin = userisadmin;
    }

    public String getUserquestion() {
        return userquestion;
    }

    public void setUserquestion(String userquestion) {
        this.userquestion = userquestion == null ? null : userquestion.trim();
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + ", usercardno="
				+ usercardno + ", usertel=" + usertel + ", useraddress=" + useraddress + ", userpostnumber="
				+ userpostnumber + ", userisadmin=" + userisadmin + ", userquestion=" + userquestion + ", useranswer="
				+ useranswer + "]";
	}
    
    
}