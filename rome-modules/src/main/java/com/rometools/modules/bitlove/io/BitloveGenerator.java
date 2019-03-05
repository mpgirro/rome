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
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The ModuleGenerator implementation for the Bitlove module.
 */
public class BitloveGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace(BitloveAttribute.PREFIX, BitloveModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return BitloveModule.URI;
    }

    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof BitloveModule) {
            final BitloveModule bitlove = (BitloveModule) module;
            if (element != null && element.getName().equals("enclosure")) {
                generateGuid(bitlove.getGuid(), element);
            }
            if (element != null && element.getName().equals("link")) {
                final Attribute rel = element.getAttribute("rel");
                if (rel != null && rel.getValue() != null && rel.getValue().equals("enclosure")) {
                    generateGuid(bitlove.getGuid(), element);
                }

            }
            /*
            if (element.getName().equals("item") || element.getName().equals("entry")) {
                generateGuid(bitlove.getGuid(), element);
            }
            */
        }
    }

    private void generateGuid(String guid, Element element) {
        if (element != null && guid != null) {
            element.setAttribute(BitloveAttribute.GUID, guid, NS);
        }
    }

}
