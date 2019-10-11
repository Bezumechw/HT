package kg.sunrise.hightime.Auth.Login;

import com.google.gson.annotations.SerializedName;

public class TokenLogin {

    public static Object User;
    @SerializedName("result")
    private
    Result result;
    String error;

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public class Result {
        @SerializedName("success")
        Success success;

        public void setSuccess(Success success) {
            this.success = success;
        }

        public Success getSuccess() {
            return success;
        }
    }

    public class Success {
        @SerializedName("user_token")
        String user_token;

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getUser_token() {
            return user_token;
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
        @SerializedName("balance")
        String balance;
        @SerializedName("account_number")
        String account_number;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number; }

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
