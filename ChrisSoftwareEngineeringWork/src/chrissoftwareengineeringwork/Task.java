/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chrissoftwareengineeringwork;

/**
 *
 * @author Chris
 * @version(17/03/2018)
 */

public class Task 
{
    private Integer tskID, allocatedTo, priority, signedOffBy;
    private String tskDescription, dateCreated, requiredSignOffLvl;
    private Float physicalDemandsRating, expectedDuration;
    
    public Task(Integer tskID, Integer allocatedTo, Integer priority, 
                Integer signedOffBy, String tskDescription, String dateCreated,
                String dueDate, String requiredSignOffLvl, 
                Float physicalDemandsRating, Float expectedDuration)
    {
        this.tskID                 = tskID; //unique task ID
        this.allocatedTo           = allocatedTo; //staff no the tsk is allocated to
        this.priority              = priority; //priority of task (0 - 10)
        this.signedOffBy           = signedOffBy; // staff number the task was signed off by
        this.tskDescription        = tskDescription; // description of the task
        this.dateCreated           = dateCreated; // date the task was created
        this.requiredSignOffLvl    = requiredSignOffLvl; //who is required to sign off the task
        this.physicalDemandsRating = physicalDemandsRating; // physical demands rating of the task 0.00 - 100.00
        this.expectedDuration      = expectedDuration; // expected duration (hours)
    }
    
    public Integer getTskID()         { return tskID; }
    public Integer getAllocatedTo()   { return allocatedTo; }
    public Integer getPriority()      { return priority; }
    public Integer getSignedOffBy()   { return signedOffBy; }
    public String getTskDescription() { return tskDescription ; }
    public String getDateCreated()    { return dateCreated; }
    
    public String getRequiredSignOffLvl()   { return requiredSignOffLvl; }
    public Float getPhysicalDemandsRating() { return physicalDemandsRating; }
    public Float getExpectedDuration()      { return expectedDuration; }
    public String toString()
    {
        return "Task ID: " + tskID + 
               "\nDescription: " + tskDescription +
               "\nAllocated To: " + allocatedTo +
               "\nPriority: " + priority +
               "\nSigned Off By: " + signedOffBy +
               "\nDate Created: " + dateCreated +
               "\nRequired Sign Off Level: " +
               "\nPhysical Demands Rating: " +
               "\nExpected Duration: ";
    }
    
    public void setTskID(Integer tskID)
    {
        this.tskID = tskID;
    }
    
    public void setAllocatedTo(Integer allocatedTo)
    {
        this.allocatedTo = allocatedTo;
    }
    
    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }
    
    public void setSignedOffBy(Integer signedOffBy)
    {
        this.signedOffBy = signedOffBy;
    }
    
    public void setTskDescription(String description)
    {
        this.tskDescription = description;
    }
    
    public void setDateCreated(String dateCreated)
    {
        this.dateCreated           = dateCreated;
    }
    
    public void setRequiredSignOffLvl(String requiredSignOffLvl)
    {
        this.requiredSignOffLvl    = requiredSignOffLvl;
    }
    
    public void setPysicalDemandsRating(Float physicalDemandsRating)
    {
        this.physicalDemandsRating = physicalDemandsRating;
    }
    
    public void setExpectedDuration(Float expectedDuration)
    {
        this.expectedDuration      = expectedDuration;
    }

}