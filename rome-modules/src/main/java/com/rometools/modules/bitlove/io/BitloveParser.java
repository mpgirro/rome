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

import com.rometools.modules.bitlove.modules.BitloveModule;
import com.rometools.modules.bitlove.modules.BitloveModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

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
        BitloveModule bitlove = null;

        // in RSS 2.0 feeds, the Bitlove GUID attribute is on the enclosure elements of an item
        if (element != null && element.getName().equals("enclosure")) {
            bitlove = fromElement(element);
        }

        // in Atom feeds, the Bitlove GUID attribute is on the link element with the <enclosure> attribute of an entry
        if (element != null && element.getName().equals("link")) {
            final Attribute rel = element.getAttribute("rel");
            if (rel != null && rel.getValue() != null && rel.getValue().equals("enclosure")) {
                bitlove = fromElement(element);
            }
        }

        return bitlove;
    }

    private BitloveModule fromElement(Element element) {
        BitloveModule bitlove = null;
        final Attribute guid = element.getAttribute(BitloveAttribute.GUID, NS);
        if (guid != null && guid.getValue() != null) {
            bitlove = new BitloveModuleImpl();
            bitlove.setGuid(guid.getValue().trim());
        }
        return bitlove;
    }

}
