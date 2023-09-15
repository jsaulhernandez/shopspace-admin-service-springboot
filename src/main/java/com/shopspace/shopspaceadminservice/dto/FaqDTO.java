package com.shopspace.shopspaceadminservice.dto;

public class FaqDTO {
    private Long id;
    private String question;
    private String answer;
    private Integer status;

    public FaqDTO() {
    }

    public FaqDTO(Long id, String question, String answer, Integer status) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FaqDTO{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                '}';
    }
}
