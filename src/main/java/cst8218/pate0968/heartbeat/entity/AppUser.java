/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@XmlRootElement
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // private Long ID variable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // private String user id variable
    @Column(name = "USER_ID")
    @NotNull
    private String userId;
    
    // private String password variable
    @Column(name = "PASSWORD")
    @NotNull
    private String password;
    
    // private String group name variable
    @Column(name = "GROUPNAME")
    @NotNull
    private String groupName;

    // AppUser class constructor
    public AppUser() {
    }

    // AppUser class constructor with long id parameters
    public AppUser(Long id) {
        this.id = id;
    }

    // getter for ID variable
    public Long getId() {
        return id;
    }

    // setter for ID variable
    public void setId(Long id) {
        this.id = id;
    }

    // getter for user id variable
    public String getUserId() {
        return userId;
    }

    // setter for user id variable
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // getter for password variable
    public String getPassword() {
        return "";
    }

    // setter for password variable
    public void setPassword(String password) {
        if (password.equals("")){
            if (this.password == null) this.password = "";
            return;
        }
        // initialize a PasswordHash object which will generate password hashes
        Instance<? extends PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
        PasswordHash passwordHash = instance.get();
        passwordHash.initialize(new HashMap<String,String>());  // todo: are the defaults good enough?
        // now we can generate a password entry for a given password
        String passwordEntry = password; //pretend the user has chosen a password mySecretPassword
        passwordEntry = passwordHash.generate(passwordEntry.toCharArray());
        //at this point, passwordEntry refers to a salted/hashed password entry String corresponding to the clear text “mySecretPassword”
        this.password = passwordEntry;
    }

    // getter for group name variable
    public String getGroupName() {
        return groupName;
    }

    // setter for group name variable
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // Change "Contact" -> "AppUser"
        return "com.mycompany.appuser.entity.AppUser[ id=" + id + " ]";
    }
    
}
