package ru.yescoding.app.model.entity.enumTypes;

public enum Role {
    TEACHER("ROLE_MODERATOR","Преподаватель"),
    STUDENT("ROLE_USER", "Студент"),
    ADMINISTRATOR("ROLE_ADMIN","Администратор");

    private final String code;

    private final String ruCode;

    Role(String code, String ruCode) {
        this.code = code;
        this.ruCode = ruCode;
    }

    public String getCode() {
        return code;
    }

    public String getRuCode() {
        return ruCode;
    }
}
