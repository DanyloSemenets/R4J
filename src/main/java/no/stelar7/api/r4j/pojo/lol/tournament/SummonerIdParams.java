package no.stelar7.api.r4j.pojo.lol.tournament;

import java.io.Serializable;
import java.util.*;

public class SummonerIdParams implements Serializable
{
    private static final long serialVersionUID = -4545674020366401552L;
    
    private final Set<Long> participants;
    
    public SummonerIdParams()
    {
        this.participants = new HashSet<>();
    }
    
    public SummonerIdParams(final Set<Long> set)
    {
        this.participants = new HashSet<>(set);
    }
    
    public Set<Long> getParticipants()
    {
        return this.participants;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        
        SummonerIdParams that = (SummonerIdParams) o;
        
        return (participants != null) ? participants.equals(that.participants) : (that.participants == null);
    }
    
    @Override
    public int hashCode()
    {
        return participants != null ? participants.hashCode() : 0;
    }
    
    @Override
    public String toString()
    {
        return "SummonerIdParams{" +
               "participants=" + participants +
               '}';
    }
}
