package com.example.fast_service;

public class User {

        private String email;
        private  String Uid;
        private String Email;
        private String FullName;
        private String PhoneNumber;
        private String Password;
        private String UserType;
       // private int ID;
       // private static int count = 0;

        public User(){

        }

        public User(String Uid, String email, String FullName, String phoneNumber, String password, String UserType ) {
            this.Uid = Uid;
            this.Email = email;
            this.FullName = FullName;
            this.PhoneNumber = phoneNumber;
            this.Password = password;
            this.UserType = UserType;
        //    ID = ++count;
        }

    public User(String email, String password, String UserType) {
        this.Email = email;
        this.Password = password;
        this.UserType = UserType;
    }

    public String getUid() {
            return Uid;
        }

        public void setUid(String uid) {
            Uid = uid;
        }

        public String getEmail() {
        return Email;
    }

        public void setEmail(String email) {
        this.Email = email;
    }

    public String getFullName() { return FullName; }

        public void setFullName(String fullName) { FullName = fullName; }

        public String getPhoneNumber() { return PhoneNumber; }

        public void setPhoneNumber(String username) {
        this.PhoneNumber = PhoneNumber;
    }

        public String getPassword() { return Password; }

        public void setPassword(String password) { this.Password = password; }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String userType) { UserType = userType; }

        //  public int getID() { return ID; }

        //public void setID(int ID) { this.ID = ID;    }
}
