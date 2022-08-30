package com.marketlogic.survey.entities.enums;

public enum QuestionStatus {

    ENABLED(0),
    DISABLED(1);

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
