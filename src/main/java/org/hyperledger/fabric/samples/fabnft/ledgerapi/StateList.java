package org.hyperledger.fabric.samples.fabnft.ledgerapi;

import org.hyperledger.fabric.samples.fabnft.ledgerapi.impl.StateListImpl;
import org.hyperledger.fabric.contract.Context;

import java.util.List;

public interface StateList {
    static StateList getStateList(Context ctx, String listName, StateDeserializer deserializer) {
        return new StateListImpl(ctx, listName, deserializer);
    }

    public StateList addState(State state);

    public State getState(String key);

    public List<State> getAllStates(String startKey, String endKey);

    public StateList updateState(State state);


}
