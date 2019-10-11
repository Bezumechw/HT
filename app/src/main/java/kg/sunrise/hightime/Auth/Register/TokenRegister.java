package kg.sunrise.hightime.Auth.Register;

import com.google.gson.annotations.SerializedName;

public class TokenRegister {

    Result result;

    String error;

    public String getError() {
        return error;
    }

    public Result getResult() {
        return result;
    }

    public class Result{

        Success success;

        public Success getSuccess() {
            return success;
        }
    }

    public class Success{
        String token;
        String name;

        public String getName() {
            return name;
        }

        public String getToken() {
            return token;
        }
    }

    @SerializedName("user")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {

        int id;
        String fullName;
        String email;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
