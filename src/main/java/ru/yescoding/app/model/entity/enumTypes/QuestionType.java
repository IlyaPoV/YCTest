package ru.yescoding.app.model.entity.enumTypes;

public enum QuestionType {
    SELECT("select", "Выбор"),
    MULTISELECT("multi-select", "Множественный выбор");

    private final String code;

    private final String ruCode;

    QuestionType(String code, String ruCode){
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
