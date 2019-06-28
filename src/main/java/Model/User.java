package Model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Person {
    private String firstName;
    private String lastName;
    private String birthday;
    private Gender gender;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

public class User extends Person implements Serializable {
    private String username;//final
    private String mailAddress;
    private String password;
    private int age;
    private String phoneNumber = "";
    private String imagePath;
    private int ImageNumber;
    private Set<User> blackList = new HashSet<>();
    private transient ObjectInputStream inputStream;
    private transient ObjectOutputStream outputStream;
    private static final long serialVersionUID = 1;

    public User() {
        super();
    }

    public User(String Username) {
        super();
        this.username = Username;
    }

    public User(String username, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.username = username;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImageNumber() {
        return ImageNumber;
    }

    public void setImageNumber(int imageNumber) {
        ImageNumber = imageNumber;
    }

    public Set<User> getBlackList() {
        return blackList;
    }

    public void setBlackList(Set<User> blackList) {
        this.blackList = blackList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public String getUsername() {
        return username;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
