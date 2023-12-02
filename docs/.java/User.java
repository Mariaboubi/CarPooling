

public abstract class User
{
    private final int userId;
    private EmailAddress email;
    private String username, password, surname,name, phone;
    private int age;
    public float rate;
   
  
    
    public User (int Id,String username, String name, String surname, String phone, EmailAddress email, String password, int age,int rate){
        this.username=username;
        this.name = name;
        this.surname= surname;
        this.phone=phone;
        this.email=email;
        this.password=password;
        this.age = age;
        this.rate= rate;
        this.userId = Id;
        // this.credit_card= credit_card;
    }

    //Getters
    public int getUserId(){
        return this.userId;
    }
    public float getRate(){
        return this.rate;
    }
    public String getUsername() {
        return this.username;
    }
    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public String getPhone() {
        return this.phone;
    }
    public EmailAddress getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public int getAge(){
        return this.age;
    }
    // public String getCreditCard() {
    //     return this.credit_card;
    // }

    public void changePersonalDetails(String username,String name, String surname, String phone, EmailAddress email,int age ){
        this.username=username;
        this.name = name;
        this.surname= surname;
        this.phone=phone;
        this.email=email;
        this.age=age;
    }
    // public void changeBankDetails(String credit_card){ // this method is called by the controller when the user inputs weak password and needs to change it , the controllers checks if the password is weak , not this class
    //     this.credit_card=credit_card;
    // }
    public void changePassword(String pass){ // this method is called by the controller when the user inputs weak password and needs to change it , the controllers checks if the password is weak , not this class
        this.password=pass;
    }

}