/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isib.apiRest.bo;

import java.io.Serializable;

/**
 *
 * @author LOIC
 */
public class Notification implements Serializable {
    
    private boolean isSuccess;
    private Object data;
    private String message;

    public Notification() {
        this.data = null;
        this.message = "success";
        this.isSuccess = true;
    }
    
    public Notification(Object data) {
        this.data = data;
        this.message = "success";
        this.isSuccess = true;
    }

    public Notification(boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.data = null;
        this.message = (isSuccess) ? "success"
                                    : "fail";
    }

    public Notification(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.data = null;
        this.message = message;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
