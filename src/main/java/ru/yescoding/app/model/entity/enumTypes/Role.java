package ru.yescoding.app.model.entity.enumTypes;

public enum Role {
    TEACHER("teacher","Преподаватель"),
    STUDENT("student", "Студент"),
    ADMINISTRATOR("administrator","Администратор");

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
