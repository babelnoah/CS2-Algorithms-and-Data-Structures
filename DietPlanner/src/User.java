/**
 * My project Diet Planner has the purpose of giving you a detailed planner on what you should be eating based
 * on personal factors or preferences. Questions  include factors such as gender, weight, amount of exercise,
 * type of exercise,  and more. Based on this information, the program gives you the amount of macronutrients you
 * should be consuming, as well as exercise plans on this information.
 *
 * @author: Noah Babel
 * @version: 05/12/22
 */

public class User {
    private String username;
    //sets up the user, which has a username getter and setter methods
    public User() {
        this.username = null;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String name){
        this.username = name;
    }
}
