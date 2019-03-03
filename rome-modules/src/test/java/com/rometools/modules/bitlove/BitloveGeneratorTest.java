package com.rometools.modules.bitlove;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.bitlove.io.BitloveGenerator;
import com.rometools.modules.bitlove.modules.BitloveModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

public class BitloveGeneratorTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(BitloveGeneratorTest.class);

    public BitloveGeneratorTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(BitloveGeneratorTest.class);
    }

    public void testGenerateRss() throws Exception {

        log.debug("testGenerateRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("bitlove/rss.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(BitloveModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:bitlove=\"http://bitlove.org\""));
        assertTrue(xml.contains("bitlove:guid=\"6afe059f5b2ff7ba5590d5eb54be6c24\""));

        System.out.println(xml);

        log.debug("{}", writer);

    }

    public void testGenerateAtom() throws Exception {

        log.debug("testGenerateAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("bitlove/atom.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(BitloveModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:bitlove=\"http://bitlove.org\""));
        assertTrue(xml.contains("bitlove:guid=\"6afe059f5b2ff7ba5590d5eb54be6c24\""));

        System.out.println(xml);

        log.debug("{}", writer);

    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", BitloveModule.URI, new BitloveGenerator().getNamespaceUri());
    }

}
