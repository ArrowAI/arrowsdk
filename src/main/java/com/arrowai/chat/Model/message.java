package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class message {
    attachment attachment;
    boolean callAgain;
    float confidence;
    boolean finalStep;
    String suggestion;
    String text;
    postback postback;
    boolean endExistingFlow;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public message() {
    }



    public message(com.arrowai.chat.Model.attachment attachment, boolean callAgain, float confidence, boolean finalStep, String suggestion, String text, postback postback) {
        this.attachment = attachment;
        this.callAgain = callAgain;
        this.confidence = confidence;
        this.finalStep = finalStep;
        this.suggestion = suggestion;
        this.text=text;
        this.postback=postback;
    }
    public com.arrowai.chat.Model.attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(com.arrowai.chat.Model.attachment attachment) {
        this.attachment = attachment;
    }

    public boolean isCallAgain() {
        return callAgain;
    }

    public void setCallAgain(boolean callAgain) {
        this.callAgain = callAgain;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public boolean isFinalStep() {
        return finalStep;
    }

    public void setFinalStep(boolean finalStep) {
        this.finalStep = finalStep;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public com.arrowai.chat.Model.postback getPostback() {
        return postback;
    }

    public void setPostback(com.arrowai.chat.Model.postback postback) {
        this.postback = postback;
    }

    public boolean isEndExistingFlow() {
        return endExistingFlow;
    }

    public void setEndExistingFlow(boolean endExistingFlow) {
        this.endExistingFlow = endExistingFlow;
    }
}