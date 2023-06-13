/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0968.heartbeat.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/*
Team members name: Kelsey & Shivam
Kelsey Change
*/

/**
 *
 * @author Owner
 */
@Entity
@XmlRootElement
public class Heart implements Serializable {

    private static final long serialVersionUID = 1L;
    // initial size of the heart
    private static final int INTIAL_SIZE = 20;
    // width of the game window
    private static final int X_MAX = 500;
    // height of the game window
    private static final int Y_MAX = 500;
    // maximum size of the heart
    private static final int SIZE_MAX = 500;
    // maximum contracted size of the heart while beating
    private static final int CONTRACTED_MAX = 510;
    // after how many beats heart shrink
    private static final int BEATS_TO_EXHAUSTION = 1000;
    // after each beat, heart should shrink using shrink decrement value
    private static final int SHRINK_DECREMENT = 1;
    // pixle at which heart should stop beating
    private static final int STOP_SIZE = 4;
    // after reaching to contracted size how many pixle heart should increament
    private static final int BEAT_INCREMENT = 10;
    // after thousand beat how many pixle heart should decrement
    private static final int CONTRACTION_DECREMENT = 1;
    
    // ID of the heart
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // Position of the heart on x axis
    @NotNull
    @Min(value = 0)
    @Max(value = X_MAX)
    private Integer x;
    // Position of the heart on y axis
    @NotNull
    @Min(value = 0)
    @Max(value = Y_MAX)
    private Integer y;
    // size of the heart
    @NotNull
    @Min(value = 0)
    @Max(value = SIZE_MAX)
    private Integer size;
    // size at which heart should contract
    @NotNull
    @Min(value = 0)
    @Max(value = CONTRACTED_MAX)
    private Integer contractedSize;
    // beat count of the heart
    @NotNull
    @Min(value = 0)
    @Max(value = BEATS_TO_EXHAUSTION)
    private Integer beatCount;
    
    // getter for position at x axis
    public Integer getX() {
        return x;
    }

    // setter for position at x axis
    public void setX(Integer x) {
        this.x = x;
    }

    // getter for position at y axis
    public Integer getY() {
        return y;
    }

    // setter for position at y axis
    public void setY(Integer y) {
        this.y = y;
    }
    
    // getter for heart size
    public Integer getSize() {
        return size;
    }

    //setter for heart size
    public void setSize(Integer size) {
        this.size = size;
    }

    // getter for contracted size
    public Integer getContractedSize() {
        return contractedSize;
    }

    // setter for contracted size
    public void setContractedSize(Integer contractedSize) {
        this.contractedSize = contractedSize;
    }

    //getter for beat count
    public Integer getBeatCount() {
        return beatCount;
    }

    // setter for beat count
    public void setBeatCount(Integer beatCount) {
        this.beatCount = beatCount;
    }
    
    // getter for ID
    public Long getId() {
        return id;
    }

    // setter for ID
    public void setId(Long id) {
        this.id = id;
    }
    
    // update method to update the size of heart while POST method using Postman
    public void updates (Heart oldHeart){
        if (x == null){         // if x equals to null then assigne x to 0
            x = 0;
        }else{                  // otherwise set the assign the value provide in the Postman body
            oldHeart.setX(this.x);
        }
        
        if (y == null){         // if y equals to null then assigne x to 0
            y = 0;
        }else{                  // otherwise set the assign the value provide in the Postman body
            oldHeart.setY(this.y);
        }
        
        if (size == null){         // if size equals to null then assigne x to 0
            size = 0;
        }else{                  // otherwise set the assign the value provide in the Postman body
            oldHeart.setSize(this.size);
        }
        
        if (contractedSize == null){         // if contracted size equals to null then assigne x to 0
            contractedSize = 0;
        }else{                  // otherwise set the assign the value provide in the Postman body
            oldHeart.setContractedSize(this.contractedSize);
        }
        
        if (beatCount == null){         // if beat count equals to null then assigne x to 0
            beatCount = 0;
        }else{                  // otherwise set the assign the value provide in the Postman body
            oldHeart.setBeatCount(this.beatCount);
        }
    }
    
    // init method to assign the default value, if values are not provide in the Postman boby while running PUT method
    public void init() {
        if (x == null){            // if x value is not provided in the post man body the assign x to 0
            x = 0;
        }
        
        if (y == null){            // if y value is not provided in the post man body the assign x to 0
            y = 0;
        }
        
        if (size == null){            // if size value is not provided in the post man body the assign x to 0
            size = 0;
        }
        
        if (contractedSize == null){            // if contracted size value is not provided in the post man body the assign x to 0
            contractedSize = 0;
        }
        
        if (beatCount == null){            // if beat count value is not provided in the post man body the assign x to 0
            beatCount = 0;
        }
    }
    
    /** 
     * Updates the properties to simulate the passing of one unit of time.
     */
    public void advanceOneTimeIncrement() {
        if (stillBeating()){                    //if still beating
            if (finishedCurrentBeat()){            //if size has decreased to contracted size
                newBeat();                             //suddenly increase size to begin new beat
                setBeatCount(getBeatCount() + 1);      //increment beat count
                if (exhausted()){                      //if beat count has reached exhausted level
                    shrink();                               //descrease contracted size
                    setBeatCount(0);                        //now not exhausted - reset beat count
                }
            } else {                               //else 
                continueContracting();                 //continue the contracting phase of a beat
            }
        }
    }
    
    /**
     * Returns true if the Heart has not yet stopped and is still beating
     */
    private boolean stillBeating(){
        return getContractedSize() > STOP_SIZE;
    }
    
    // return true if the size less than or equal to contracted size
    private boolean finishedCurrentBeat() {
        return getSize() <= getContractedSize();
    }    

    // set the size of the new beat
    private void newBeat() {
        setSize(getSize() + BEAT_INCREMENT);
    }

    // return true if the Beat count greater than or equal to beat to exhausetion size
    private boolean exhausted() {
        return getBeatCount() >= BEATS_TO_EXHAUSTION;
    }

    // set new contracted size of the heart by subtracting shrink decrement from subtracting contractedsize
    private void shrink() {
        setContractedSize(contractedSize - SHRINK_DECREMENT);
        setBeatCount(0);
    }
    
    // set the new size of heat by subtracting current size with contraction decrement
    private void continueContracting() {
        setSize(getSize() - CONTRACTION_DECREMENT);
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
        if (!(object instanceof Heart)) {
            return false;
        }
        Heart other = (Heart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.pate0968.heartbeat.entity.Heart[ id=" + id + " ]";
    }
}
