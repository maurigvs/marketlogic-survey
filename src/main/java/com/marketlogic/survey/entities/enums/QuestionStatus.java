package com.marketlogic.survey.entities.enums;

public enum QuestionStatus {

    ENABLED(1),
    DISABLED(2);

    private int code;

    private QuestionStatus(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static QuestionStatus valueOf(int code){
        for(QuestionStatus value : QuestionStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Question Status code!");
    }
}
