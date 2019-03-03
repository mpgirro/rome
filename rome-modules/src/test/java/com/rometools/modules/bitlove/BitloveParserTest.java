package com.rometools.modules.bitlove;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.bitlove.io.BitloveParser;
import com.rometools.modules.bitlove.modules.BitloveModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class BitloveParserTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(BitloveParserTest.class);

    public BitloveParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(BitloveParserTest.class);
    }

    public void testParseRss() throws Exception {

        log.debug("testParseRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("bitlove/rss.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final BitloveModule bitlove = (BitloveModule) entry.getModule(BitloveModule.URI);

        assertNotNull(bitlove);
        assertEquals("6afe059f5b2ff7ba5590d5eb54be6c24", bitlove.getGuid());

    }

    public void testParseAtom() throws Exception {

        log.debug("testParseAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("bitlove/atom.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final BitloveModule bitlove = (BitloveModule) entry.getModule(BitloveModule.URI);

        assertNotNull(bitlove);
        assertEquals("6afe059f5b2ff7ba5590d5eb54be6c24", bitlove.getGuid());

    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", BitloveModule.URI, new BitloveParser().getNamespaceUri());
    }

}
