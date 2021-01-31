package com.example.infinigentconsulting;

public class UserClass {
    public int Id ;
    public String Name ;
    public String Email ;
    public String MobileNo ;
    public String Password ;
    public boolean IsActive ;


    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public String getMobileNo() {
        return MobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public Boolean getIsActive() {
        return IsActive;
    }

    public void setIsActive(Boolean IsActive) {
        this.IsActive = IsActive;
    }


}
