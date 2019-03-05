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
package com.rometools.modules.bitlove.modules;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Module;

/**
 * This is a ROME module that provides support for the <a href="http://bitlove.org">http://bitlove.org</a> namespace.
 */
public interface BitloveModule extends Module, CopyFrom {

    /**
     * The URI of the namespace. (<a href="http://bitlove.org">http://bitlove.org</a>)
     */
    String URI = "http://bitlove.org";

    /**
     * Get the Bitlove GUID.
     *
     * @return The Bitlove GUID.
     */
    String getGuid();

    /**
     * Set the Bitlove GUID.
     *
     * @param guid The Bitlove GUID.
     */
    void setGuid(String guid);

}
