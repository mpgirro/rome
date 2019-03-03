/*
 * Copyright 2019 Maximilian Irro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rometools.modules.bitlove.io;

import com.rometools.modules.atom.io.AtomModuleParser;
import com.rometools.modules.bitlove.modules.BitloveModule;
import com.rometools.modules.bitlove.modules.BitloveModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.List;
import java.util.Locale;

/**
 * The ModuleParser implementation for the Bitlove module.
 */
public class BitloveParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(BitloveModule.URI);

    @Override
    public String getNamespaceUri() {
        return BitloveModule.URI;
    }

    @Override
    public Module parse(Element element, Locale locale) {

        BitloveModule mod = null;

        /*
        // in RSS 2.0 feeds, the Bitlove GUID attribute is on the enclosure elements of an item
        if (element.getName().equals("item")) {
            final Element enclosure = element.getChild("enclosure");
            if (enclosure != null) {
                mod = fromElement(enclosure);
            }
        }

        // in Atom feeds, the Bitlove GUID attribute is on the link element with the <enclosure> attribute of an entry
        if (element.getName().equals("entry")) {
            final List<Element> links = element.getChildren("link", Namespace.getNamespace(new AtomModuleParser().getNamespaceUri()));
            for (Element link : links) {
                final Attribute rel = link.getAttribute("rel");
                if (rel != null && rel.getValue() != null && rel.getValue().equals("enclosure")) {
                    mod = fromElement(link);
                }
            }
        }
        */

        // in RSS 2.0 feeds, the Bitlove GUID attribute is on the enclosure elements of an item
        if (element.getName().equals("enclosure")) {
            mod = fromElement(element);
        }

        // in Atom feeds, the Bitlove GUID attribute is on the link element with the <enclosure> attribute of an entry
        if (element.getName().equals("link")) {
            final Attribute rel = element.getAttribute("rel");
            if (rel != null && rel.getValue() != null && rel.getValue().equals("enclosure")) {
                mod = fromElement(element);
            }
        }

        return mod;
    }

    private BitloveModule fromElement(Element element) {
        BitloveModule mod = null;
        final Attribute guid = element.getAttribute(BitloveAttribute.GUID, NS);
        if (guid != null && guid.getValue() != null) {
            mod = new BitloveModuleImpl();
            mod.setGuid(guid.getValue().trim());
        }
        return mod;
    }

}
