package sst;

public enum Operations {
    USER_CREATE("New user registration"),
    GET_USER("Retrieve user's info"),
    UPDATE_USER("Update user's info"),
    DELETE_USER("Delete user account"),
    DISPLAY_USER_TREE("Display user tree"),
    REVIEW_REVENUE("Review revenue"),
    SAVE_LOAD_IN_FILE("Save/Load from file"),
    CHANGE_FEE("Change registration fee/commission"),
    QUIT("Quit");
    
    public static Operations returnOperation(Integer i) {
        switch (i) {
            case 0:
                throw new IllegalArgumentException();
            case 1:
                return USER_CREATE;
            case 2:
                return GET_USER;
            case 3:
                return UPDATE_USER;
            case 4:
                return DELETE_USER;
            case 5:
                return DISPLAY_USER_TREE;
            case 6:
                return REVIEW_REVENUE;
            case 7:
                return SAVE_LOAD_IN_FILE;
            case 8:
                return CHANGE_FEE;
            case 9:
                return QUIT;
            default:
                throw new IllegalArgumentException();
        }
    }

    private String name;

    Operations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
