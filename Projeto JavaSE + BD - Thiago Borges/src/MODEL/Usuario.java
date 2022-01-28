package MODEL;

public class Usuario {

    private String login, password;

    public Usuario() {

    }

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void imprime() {

        System.out.println("Login: " + this.getLogin());
        System.out.println("Password: " + this.getPassword());

    }

}
