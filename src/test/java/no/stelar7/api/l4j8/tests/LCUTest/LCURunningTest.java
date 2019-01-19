package no.stelar7.api.l4j8.tests.LCUTest;

import no.stelar7.api.l4j8.basic.calling.DataCall;
import no.stelar7.api.l4j8.basic.constants.api.LogLevel;
import no.stelar7.api.l4j8.basic.constants.types.GameQueueType;
import no.stelar7.api.l4j8.impl.lcu.*;
import org.junit.*;

public class LCURunningTest
{
    @Test
    public void testRunning()
    {
        Assert.assertNotNull("Unable to fecth connection string from league client... Is it running?", LCUConnection.getConnectionString());
    }
    
    @Test
    public void testCreateLobby()
    {
        DataCall.setLogLevel(LogLevel.NONE);
        String id = LCUApi.createLobby(GameQueueType.TEAM_BUILDER_RANKED_SOLO);
    }
}