package no.stelar7.api.l4j8.tests.summoner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.stelar7.api.l4j8.basic.Server;
import no.stelar7.api.l4j8.basic.URLEndpoint;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePage;
import no.stelar7.api.l4j8.pojo.summoner.runes.RunePages;
import no.stelar7.api.l4j8.pojo.summoner.runes.RuneSlot;
import no.stelar7.api.l4j8.tests.SecretFile;
import no.stelar7.api.l4j8.tests.TestBase;

public class SummonerRunesTest extends TestBase
{

    @Before
    public void initBeforeTest()
    {
        TestBase.builder.withAPIKey(SecretFile.API_KEY);
        TestBase.builder.withServer(Server.EUW);
        TestBase.builder.withEndpoint(URLEndpoint.SUMMONER_RUNES_BY_ID);
    }

    @Test
    public void doTest()
    {
        // Generate list of summoner IDs
        List<String> keys = Arrays.asList("19613950", "22291359", "33540589");

        // Add them as a parameter to the URL
        keys.forEach((String k) -> TestBase.builder.withURLData("{summonerId}", k));

        // Get the response
        Map<String, RunePages> dataCall = (Map<String, RunePages>) TestBase.builder.build();

        // Make sure all the data is returned as expected
        dataCall.forEach(doAssertions);
    }

    private BiConsumer<String, RunePages> doAssertions = (String key, RunePages value) -> {

        value.getPages().forEach((RunePage page) -> {
            Assert.assertNotNull("Rune Page does not have an id", page.getId());
            Assert.assertNotNull("Rune Page does not have a name", page.getName());
            Assert.assertNotNull("Rune Page does not contain any slots", page.getSlots());
            Assert.assertNotNull("Unable to determine current Rune page", page.isCurrent());

            page.getSlots().forEach((RuneSlot slot) -> {
                Assert.assertNotNull("Rune slot does not have a slot id", slot.getRuneSlotId());
                Assert.assertNotNull("Rune slot does not have a rune id", slot.getRuneId());

                Assert.assertNotEquals("Rune slot does not have a valid id", slot.getRuneSlotId(), (Integer) 0);
                Assert.assertNotEquals("Rune slot does not have a valid rune id", slot.getRuneId(), (Integer) 0);
            });
        });

        Assert.assertTrue("There is not exactly ONE \"current\" page", value.getPages().stream().filter((RunePage page) -> page.isCurrent()).count() == 1);
    };
}
