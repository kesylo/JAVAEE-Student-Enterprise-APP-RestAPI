/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isib.war.bo;

/**
 *
 * @author LOIC
 */
public class AppSetting {
    
    private String url_web_site;
    private String app_version;
    private String app_name;

    public AppSetting() {
        this.url_web_site = "http://www.he2b.be/index.php/campus-isib";
        this.app_version = "V. 2.1.0";
        this.app_name = "Ev. management";
    }

    public String getUrl_web_site() {
        return url_web_site;
    }

    public void setUrl_web_site(String url_web_site) {
        this.url_web_site = url_web_site;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
    
    
    
}
