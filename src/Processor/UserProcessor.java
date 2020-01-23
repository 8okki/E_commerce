package Processor;


import DB_Executor.Executor;
import UI.UserUI;


public abstract class UserProcessor {

    protected String                currentID;
    protected final Executor        executor = Executor.getExecutor();

    public void setCurrentID(String id) { currentID = id; }
}
